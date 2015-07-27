/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author ADOLFO
 */
public class Requisitos extends SwingWorker<Void, String> {
    Icon bien = new ImageIcon(getClass().getResource("/Necesarios/OKIcon.png"));
    
    private final JButton need_jButton;
    private final CMysql ping;
    private final JFrame need_ventana;
    private final JProgressBar need_jProgressBar;
    private final JPasswordField need_jPasswordField;
    private int tareasRealizadas;
    boolean continuar;
    
    public Requisitos(JButton boton, CMysql c, JFrame ventanaPadre, JProgressBar jProgressBar, JPasswordField palabra) {
        need_jButton = boton;
        ping = c;
        need_ventana = ventanaPadre;
        need_jProgressBar = jProgressBar;
        need_jPasswordField = palabra;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        tareasRealizadas = 0;
        publish("false",Integer.toString(tareasRealizadas));
        continuar = true;
        if (!ping.ejecutarModificacionStatement("CREATE DATABASE BASEAEROPUERTO DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci;")) {
            if (ping.getMensajesError().endsWith("database exists")) {
                if (!ping.ejecutarModificacionStatement("DROP DATABASE BASEAEROPUERTO")) {
                    JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"ERROR AL ELIMINAR BASE DE DATOS EXISTENTE",JOptionPane.ERROR_MESSAGE);
                    continuar = false;
                } else {
                    if (!ping.ejecutarModificacionStatement("CREATE DATABASE BASEAEROPUERTO DEFAULT CHARACTER SET UTF8 COLLATE utf8_general_ci;")) {
                        JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"ERROR AL CREAR BASE DE DATOS",JOptionPane.ERROR_MESSAGE);
                        continuar = false;
                    } else {
                        publish("false",Integer.toString(++tareasRealizadas));
                    }   
                }
            } else {
                JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"ERROR AL CREAR BASE DE DATOS",JOptionPane.ERROR_MESSAGE);
                continuar = false;
            }
        } else {
            publish("false",Integer.toString(++tareasRealizadas));
        }
        if (continuar) {
            String crearTablas = "USE BASEAEROPUERTO;\n";
            crearTablas += "CREATE TABLE PILOTOS_PLANES\n" +
                                    "(\n" +
                                    "no_licencia		VARCHAR (9)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "tipo_licencia	VARCHAR (10),\n" +
                                    "PRIMARY KEY (no_licencia)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE AEROPUERTOS_PLANES\n" +
                                    "(\n" +
                                    "codigo_oaci		VARCHAR (4)		NOT NULL,\n" +
                                    "codigo_iata		VARCHAR (3),\n" +
                                    "nombre		VARCHAR (100),\n" +
                                    "PRIMARY KEY (codigo_oaci)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE AERONAVES_PLANES\n" +
                                    "(\n" +
                                    "identificacion_aeronave		VARCHAR (7)		NOT NULL,\n" +
                                    "tipo					VARCHAR (4),\n" +
                                    "PRIMARY KEY (identificacion_aeronave)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PDFS_APERTURA\n" +
                                    "(\n" +
                                    "ruta_pdf		VARCHAR (250)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "PRIMARY KEY (ruta_pdf)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PDFS_CIERRE\n" +
                                    "(\n" +
                                    "ruta_pdf		VARCHAR (250)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "PRIMARY KEY (ruta_pdf)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE COPILOTOS_PLANES\n" +
                                    "(\n" +
                                    "no_licencia		VARCHAR (9)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "tipo_licencia	VARCHAR (10),\n" +
                                    "PRIMARY KEY (no_licencia)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE CIERRES_DE_PLAN\n" +
                                    "(\n" +
                                    "fecha_hora			DATETIME		NOT NULL,\n" +
                                    "piloto			VARCHAR (9),\n" +
                                    "aeropuerto_origen		VARCHAR (4),\n" +
                                    "aeronave			VARCHAR (7),\n" +
                                    "observaciones		VARCHAR (1000),\n" +
                                    "no_pasajeros		INTEGER,\n" +
                                    "PRIMARY KEY (fecha_hora),\n" +
                                    "INDEX (piloto),\n" +
                                    "FOREIGN KEY (piloto) REFERENCES PILOTOS_PLANES (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (aeropuerto_origen),\n" +
                                    "FOREIGN KEY (aeropuerto_origen) REFERENCES AEROPUERTOS_PLANES (codigo_oaci) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (aeronave),\n" +
                                    "FOREIGN KEY (aeronave) REFERENCES AERONAVES_PLANES (identificacion_aeronave) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE APERTURAS_DE_VUELO\n" +
                                    "(\n" +
                                    "fecha_hora			DATETIME		NOT NULL,\n" +
                                    "piloto			VARCHAR (9),\n" +
                                    "aeropuerto_destino	VARCHAR (4),\n" +
                                    "aeronave			VARCHAR (7),\n" +
                                    "otros_datos			VARCHAR (1000),\n" +
                                    "no_personas_a_bordo	INTEGER,\n" +
                                    "PRIMARY KEY (fecha_hora),\n" +
                                    "INDEX (piloto),\n" +
                                    "FOREIGN KEY (piloto) REFERENCES PILOTOS_PLANES (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (aeropuerto_destino),\n" +
                                    "FOREIGN KEY (aeropuerto_destino) REFERENCES AEROPUERTOS_PLANES (codigo_oaci) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (aeronave),\n" +
                                    "FOREIGN KEY (aeronave) REFERENCES AERONAVES_PLANES (identificacion_aeronave) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE RELACION_PDF_APERTURAS\n" +
                                    "(\n" +
                                    "fecha		DATETIME		NOT NULL,\n" +
                                    "pdf		VARCHAR (250) 	NOT NULL,\n" +
                                    "PRIMARY KEY (fecha),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES APERTURAS_DE_VUELO (fecha_hora) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (pdf),\n" +
                                    "FOREIGN KEY (pdf) REFERENCES PDFS_APERTURA (ruta_pdf) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE RELACION_PDF_CIERRES\n" +
                                    "(\n" +
                                    "fecha		DATETIME		NOT NULL,\n" +
                                    "pdf		VARCHAR (250) 	NOT NULL,\n" +
                                    "PRIMARY KEY (fecha),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES CIERRES_DE_PLAN (fecha_hora) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (pdf),\n" +
                                    "FOREIGN KEY (pdf) REFERENCES PDFS_CIERRE (ruta_pdf) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE COPILOTOS_APERTURA\n" +
                                    "(\n" +
                                    "fecha		DATETIME		NOT NULL,\n" +
                                    "copiloto	VARCHAR (9) 	NOT NULL,\n" +
                                    "PRIMARY KEY (fecha),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES APERTURAS_DE_VUELO (fecha_hora) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (copiloto),\n" +
                                    "FOREIGN KEY (copiloto) REFERENCES COPILOTOS_PLANES (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE COPILOTOS_CIERRE\n" +
                                    "(\n" +
                                    "fecha		DATETIME		NOT NULL,\n" +
                                    "copiloto	VARCHAR (9) 	NOT NULL,\n" +
                                    "PRIMARY KEY (fecha),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES CIERRES_DE_PLAN (fecha_hora) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (copiloto),\n" +
                                    "FOREIGN KEY (copiloto) REFERENCES COPILOTOS_PLANES (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PASAJEROS_APERTURA\n" +
                                    "(\n" +
                                    "id_pasajero		VARCHAR (14)	NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "nacionalidad	VARCHAR (60),\n" +
                                    "fecha			DATETIME,\n" +
                                    "PRIMARY KEY (id_pasajero),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES APERTURAS_DE_VUELO (fecha_hora) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PASAJEROS_CIERRE\n" +
                                    "(\n" +
                                    "id_pasajero		VARCHAR (14)	NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "nacionalidad	VARCHAR (60),\n" +
                                    "fecha			DATETIME,\n" +
                                    "PRIMARY KEY (id_pasajero),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES CIERRES_DE_PLAN (fecha_hora) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n";
            crearTablas += "CREATE TABLE SEGUNDO_OFICIAL\n" +
                                    "(\n" +
                                    "no_licencia		VARCHAR (9)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "tipo_licencia	VARCHAR (10),\n" +
                                    "PRIMARY KEY (no_licencia)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE TERCER_OFICIAL\n" +
                                    "(\n" +
                                    "no_licencia		VARCHAR (9)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "tipo_licencia	VARCHAR (10),\n" +
                                    "PRIMARY KEY (no_licencia)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE COMPANIAS\n" +
                                    "(\n" +
                                    "siglas	VARCHAR (3)		NOT NULL,\n" +
                                    "nombre	VARCHAR (100),\n" +
                                    "PRIMARY KEY (siglas)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE AERONAVES_MANIFIESTOS\n" +
                                    "(\n" +
                                    "matricula	VARCHAR (7)		NOT NULL,\n" +
                                    "equipo	VARCHAR (15),\n" +
                                    "PRIMARY KEY (matricula)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE COMANDANTE\n" +
                                    "(\n" +
                                    "no_licencia		VARCHAR (9)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "tipo_licencia	VARCHAR (10),\n" +
                                    "PRIMARY KEY (no_licencia)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PRIMER_OFICIAL\n" +
                                    "(\n" +
                                    "no_licencia		VARCHAR (9)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "apellido_paterno	VARCHAR (20),\n" +
                                    "apellido_materno	VARCHAR (20),\n" +
                                    "tipo_licencia	VARCHAR (10),\n" +
                                    "PRIMARY KEY (no_licencia)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE AEROPUERTOS_MANIFIESTOS\n" +
                                    "(\n" +
                                    "codigo_iata		VARCHAR (3)		NOT NULL,\n" +
                                    "codigo_oaci		VARCHAR (4),\n" +
                                    "nombre		VARCHAR (100),\n" +
                                    "PRIMARY KEY (codigo_iata)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PDFS_MANIFIESTOS\n" +
                                    "(\n" +
                                    "ruta_pdf		VARCHAR (250)		NOT NULL,\n" +
                                    "nombre		VARCHAR (50),\n" +
                                    "PRIMARY KEY (ruta_pdf)\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE MANIFIESTOS_SALIDA\n" +
                                    "(\n" +
                                    "fecha_hora_itinerario		DATETIME		NOT NULL,\n" +
                                    "hora_real				TIME,\n" +
                                    "no_de_vuelo				VARCHAR (7),\n" +
                                    "empresa 				VARCHAR (3),\n" +
                                    "aeronave 				VARCHAR (7),\n" +
                                    "comandante				VARCHAR (9),\n" +
                                    "primer				VARCHAR (9),\n" +
                                    "sobrecargos				VARCHAR (600),\n" +
                                    "destino				VARCHAR (3),\n" +
                                    "tramo_interior			INTEGER,\n" +
                                    "exentos_nacionales		INTEGER,\n" +
                                    "internacionales			INTEGER,\n" +
                                    "exentos_internacionales		INTEGER,\n" +
                                    "infantes				INTEGER,\n" +
                                    "transito				INTEGER,\n" +
                                    "total					INTEGER,\n" +
                                    "ttl_pasajeros			VARCHAR (15),\n" +
                                    "ttl_equipaje			VARCHAR (15),\n" +
                                    "ttl_carga				VARCHAR (15),\n" +
                                    "ttl_correo				VARCHAR (15),\n" +
                                    "PRIMARY KEY (fecha_hora_itinerario),\n" +
                                    "INDEX (empresa),\n" +
                                    "FOREIGN KEY (empresa) REFERENCES COMPANIAS (siglas) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (aeronave),\n" +
                                    "FOREIGN KEY (aeronave) REFERENCES AERONAVES_MANIFIESTOS (matricula) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (comandante),\n" +
                                    "FOREIGN KEY (comandante) REFERENCES COMANDANTE (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (primer),\n" +
                                    "FOREIGN KEY (primer) REFERENCES PRIMER_OFICIAL (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (destino),\n" +
                                    "FOREIGN KEY (destino) REFERENCES AEROPUERTOS_MANIFIESTOS (codigo_iata) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE MANIFIESTOS_LLEGADA\n" +
                                    "(\n" +
                                    "fecha_hora_itinerario		DATETIME		NOT NULL,\n" +
                                    "hora_real				TIME,\n" +
                                    "no_de_vuelo				VARCHAR (7),\n" +
                                    "empresa 				VARCHAR (3),\n" +
                                    "aeronave 				VARCHAR (7),\n" +
                                    "comandante				VARCHAR (9),\n" +
                                    "primer				VARCHAR (9),\n" +
                                    "sobrecargos				VARCHAR (600),\n" +
                                    "origen				VARCHAR (3),\n" +
                                    "total_pasajeros			INTEGER,\n" +
                                    "ttl_pasajeros			VARCHAR (15),\n" +
                                    "ttl_equipaje			VARCHAR (15),\n" +
                                    "ttl_carga				VARCHAR (15),\n" +
                                    "ttl_correo				VARCHAR (15),\n" +
                                    "PRIMARY KEY (fecha_hora_itinerario),\n" +
                                    "INDEX (empresa),\n" +
                                    "FOREIGN KEY (empresa) REFERENCES COMPANIAS (siglas) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (aeronave),\n" +
                                    "FOREIGN KEY (aeronave) REFERENCES AERONAVES_MANIFIESTOS (matricula) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (comandante),\n" +
                                    "FOREIGN KEY (comandante) REFERENCES COMANDANTE (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (primer),\n" +
                                    "FOREIGN KEY (primer) REFERENCES PRIMER_OFICIAL (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (origen),\n" +
                                    "FOREIGN KEY (origen) REFERENCES AEROPUERTOS_MANIFIESTOS (codigo_iata) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE SEGUNDO_SALIDA\n" +
                                    "(\n" +
                                    "licencia_ss		VARCHAR (9)		NOT NULL,\n" +
                                    "fecha_ss		DATETIME		NOT NULL,\n" +
                                    "PRIMARY KEY (fecha_ss),\n" +
                                    "INDEX (licencia_ss),\n" +
                                    "FOREIGN KEY (licencia_ss) REFERENCES SEGUNDO_OFICIAL (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (fecha_ss),\n" +
                                    "FOREIGN KEY (fecha_ss) REFERENCES MANIFIESTOS_SALIDA (fecha_hora_itinerario) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE TERCERO_SALIDA\n" +
                                    "(\n" +
                                    "licencia_ts		VARCHAR (9)		NOT NULL,\n" +
                                    "fecha_ts		DATETIME		NOT NULL,\n" +
                                    "PRIMARY KEY (fecha_ts),\n" +
                                    "INDEX (licencia_ts),\n" +
                                    "FOREIGN KEY (licencia_ts) REFERENCES TERCER_OFICIAL (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (fecha_ts),\n" +
                                    "FOREIGN KEY (fecha_ts) REFERENCES MANIFIESTOS_SALIDA (fecha_hora_itinerario) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE SEGUNDO_LLEGADA\n" +
                                    "(\n" +
                                    "licencia_sl		VARCHAR (9)		NOT NULL,\n" +
                                    "fecha_sl		DATETIME		NOT NULL,\n" +
                                    "PRIMARY KEY (fecha_sl),\n" +
                                    "INDEX (licencia_sl),\n" +
                                    "FOREIGN KEY (licencia_sl) REFERENCES SEGUNDO_OFICIAL (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (fecha_sl),\n" +
                                    "FOREIGN KEY (fecha_sl) REFERENCES MANIFIESTOS_LLEGADA (fecha_hora_itinerario) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE TERCERO_LLEGADA\n" +
                                    "(\n" +
                                    "licencia_tl		VARCHAR (9)		NOT NULL,\n" +
                                    "fecha_tl		DATETIME		NOT NULL,\n" +
                                    "PRIMARY KEY (fecha_tl),\n" +
                                    "INDEX (licencia_tl),\n" +
                                    "FOREIGN KEY (licencia_tl) REFERENCES TERCER_OFICIAL (no_licencia) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (fecha_tl),\n" +
                                    "FOREIGN KEY (fecha_tl) REFERENCES MANIFIESTOS_LLEGADA (fecha_hora_itinerario) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PDFS_SALIDA\n" +
                                    "(\n" +
                                    "fecha		DATETIME		NOT NULL,\n" +
                                    "pdf		VARCHAR (250) 	NOT NULL,\n" +
                                    "PRIMARY KEY (fecha),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES MANIFIESTOS_SALIDA (fecha_hora_itinerario) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (pdf),\n" +
                                    "FOREIGN KEY (pdf) REFERENCES PDFS_MANIFIESTOS (ruta_pdf) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n" +
                                    "CREATE TABLE PDFS_LLEGADA\n" +
                                    "(\n" +
                                    "fecha		DATETIME		NOT NULL,\n" +
                                    "pdf		VARCHAR (250) 	NOT NULL,\n" +
                                    "PRIMARY KEY (fecha),\n" +
                                    "INDEX (fecha),\n" +
                                    "FOREIGN KEY (fecha) REFERENCES MANIFIESTOS_LLEGADA (fecha_hora_itinerario) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                                    "INDEX (pdf),\n" +
                                    "FOREIGN KEY (pdf) REFERENCES PDFS_MANIFIESTOS (ruta_pdf) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                                    ") ENGINE=InnoDB;\n";
            crearTablas += "INSERT INTO AEROPUERTOS_PLANES (codigo_oaci, codigo_iata, nombre) VALUES\n" +
                                    "('MMAA', 'ACA', 'AEROPUERTO INTERNACIONAL GENERAL JUAN N. ALVAREZ'),\n" +
                                    "('MMAS', 'AGU', 'AEROPUERTO INTERNACIONAL LICENCIADO JESUS TERAN PEREDO'),\n" +
                                    "('MMCP', 'CPE', 'AEROPUERTO INTERNACIONAL ING. ALBERTO ACUÑA ONGAY'),\n" +
                                    "('MMUN', 'CUN', 'AEROPUERTO INTERNACIONAL DE CANCUN'),\n" +
                                    "('MMCM', 'CTM', 'AEROPUERTO INTERNACIONAL DE CHETUMAL'),\n" +
                                    "('MMCT', 'CZA', 'AEROPUERTO INTERNACIONAL DE KAUA'),\n" +
                                    "('MMCU', 'CUU', 'AEROPUERTO INTERNACIONAL GENERAL ROBERTO FIERRO VILLALOBOS'),\n" +
                                    "('MMCC', 'ACN', 'AEROPUERTO INTERNACIONAL DE CIUDAD ACUÑA'),\n" +
                                    "('MMMX', 'MEX', 'AEROPUERTO INTERNACIONAL DE LA CIUDAD DE MEXICO'),\n" +
                                    "('MMCE', 'CME', 'AEROPUERTO INTERNACIONAL DE CIUDAD DEL CARMEN'),\n" +
                                    "('MMCS', 'CJS', 'AEROPUERTO INTERNACIONAL ABRAHAM GONZALEZ'),\n" +
                                    "('MMCN', 'CEN', 'AEROPUERTO INTERNACIONAL DE CIUDAD OBREGON'),\n" +
                                    "('MMCV', 'CVM', 'AEROPUERTO INTERNACIONAL GENERAL PEDRO JOSE MENDEZ'),\n" +
                                    "('MMCZ', 'CZM', 'AEROPUERTO INTERNACIONAL DE COZUMEL'),\n" +
                                    "('MMCB', 'CVJ', 'AEROPUERTO INTERNACIONAL GENERAL MARIANO MATAMOROS'),\n" +
                                    "('MMCL', 'CUL', 'AEROPUERTO INTERNACIONAL FEDERAL DE CULIACAN'),\n" +
                                    "('MMDO', 'DGO', 'AEROPUERTO INTERNACIONAL GENERAL GUADALUPE VICTORIA'),\n" +
                                    "('MMES', 'ESE', 'AEROPUERTO INTERNACIONAL DE ENSENADA'),\n" +
                                    "('MMMV', 'LOV', 'AEROPUERTO INTERNACIONAL VENUSTIANO CARRANZA'),\n" +
                                    "('MMGL', 'GDL', 'AEROPUERTO INTERNACIONAL DE GUADALAJARA'),\n" +
                                    "('MMGM', 'GYM', 'AEROPUERTO INTERNACIONAL GENERAL JOSE MARIA YAÑEZ'),\n" +
                                    "('MMHO', 'HMO', 'AEROPUERTO INTERNACIONAL GENERAL IGNACIO PESQUEIRA GARCIA'),\n" +
                                    "('MMBT', 'HUX', 'AEROPUERTO INTERNACIONAL DE BAHIAS DE HUATULCO'),\n" +
                                    "('MMZH', 'ZIH', 'AEROPUERTO INTERNACIONAL DE IXTAPA-ZIHUATANEJO'),\n" +
                                    "('MMLP', 'LAP', 'AEROPUERTO INTERNACIONAL MANUEL MARQUEZ DE LEON'),\n" +
                                    "('MMLO', 'BJX', 'AEROPUERTO INTERNACIONAL DEL BAJIO'),\n" +
                                    "('MMLT', 'LTO', 'AEROPUERTO INTERNACIONAL DE LORETO'),\n" +
                                    "('MMSD', 'SJD', 'AEROPUERTO INTERNACIONAL DE LOS CABOS'),\n" +
                                    "('MMLM', 'LMM', 'AEROPUERTO INTERNACIONAL FEDERAL DEL VALLE DEL FUERTE'),\n" +
                                    "('MMZO', 'ZLO', 'AEROPUERTO INTERNACIONAL PLAYA DE ORO'),\n" +
                                    "('MMMA', 'MAM', 'AEROPUERTO INTERNACIONAL GENERAL SERVANDO CANALES'),\n" +
                                    "('MMMZ', 'MZT', 'AEROPUERTO INTERNACIONAL GENERAL RAFAEL BUELNA'),\n" +
                                    "('MMMD', 'MID', 'AEROPUERTO INTERNACIONAL DE MERIDA'),\n" +
                                    "('MMML', 'MXL', 'AEROPUERTO INTERNACIONAL GENERAL RODOLFO SANCHEZ TABOADA'),\n" +
                                    "('MMMT', 'MTT', 'AEROPUERTO INTERNACIONAL DE MINATITLAN'),\n" +
                                    "('MMMY', 'MTY', 'AEROPUERTO INTERNACIONAL DE MONTERREY'),\n" +
                                    "('MMAN', 'NTR', 'AEROPUERTO INTERNACIONAL DEL NORTE'),\n" +
                                    "('MMMM', 'MLM', 'AEROPUERTO INTERNACIONAL GENERAL FRANCISCO J. MUJICA'),\n" +
                                    "('MMNG', 'NOG', 'AEROPUERTO INTERNACIONAL DE NOGALES'),\n" +
                                    "('MMNL', 'NLD', 'AEROPUERTO INTERNACIONAL QUETZALCOATL'),\n" +
                                    "('MMOX', 'OAX', 'AEROPUERTO INTERNACIONAL XOXOCOTLAN'),\n" +
                                    "('MMPQ', 'PQM', 'AEROPUERTO INTERNACIONAL DE PALENQUE'),\n" +
                                    "('MMPG', 'PDS', 'AEROPUERTO INTERNACIONAL DE PIEDRAS NEGRAS'),\n" +
                                    "('MMPB', 'PBC', 'AEROPUERTO INTERNACIONAL DE PUEBLA'),\n" +
                                    "('MMPS', 'PXM', 'AEROPUERTO INTERNACIONAL DE PUERTO ESCONDIDO'),\n" +
                                    "('MMPE', 'PPE', 'AEROPUERTO INTERNACIONAL DE MAR DE CORTES'),\n" +
                                    "('MMPR', 'PVR', 'AEROPUERTO INTERNACIONAL LICENCIADO GUSTAVO DIAZ ORDAZ'),\n" +
                                    "('MMQT', 'QRO', 'AEROPUERTO INTERCONTINENTAL DE QUERETARO'),\n" +
                                    "('MMRX', 'REX', 'AEROPUERTO INTERNACIONAL GENERAL LUCIO BLANCO'),\n" +
                                    "('MMIO', 'SLW', 'AEROPUERTO INTERNACIONAL PLAN DE GUADALUPE'),\n" +
                                    "('MMSF', 'SFH', 'AEROPUERTO INTERNACIONAL DE SAN FELIPE'),\n" +
                                    "('MMSP', 'SLP', 'AEROPUERTO INTERNACIONAL PONCIANO ARRIAGA'),\n" +
                                    "('MM76', 'UAC', 'AEROPUERTO INTERNACIONAL DE SAN LUIS RIO COLORADO'),\n" +
                                    "('MMTM', 'TAM', 'AEROPUERTO INTERNACIONAL DE TAMPICO'),\n" +
                                    "('MMTP', 'TAP', 'AEROPUERTO INTERNACIONAL DE TAPACHULA'),\n" +
                                    "('MMEP', 'TNY', 'AEROPUERTO INTERNACIONAL AMADO NERVO'),\n" +
                                    "('MMTJ', 'TIJ', 'AEROPUERTO INTERNACIONAL DE TIJUANA'),\n" +
                                    "('MMTO', 'TLC', 'AEROPUERTO INTERNACIONAL LIC. ADOLFO LOPEZ MATEOS'),\n" +
                                    "('MMTC', 'TRC', 'AEROPUERTO INTERNACIONAL DE TORREON FRANCISCO SARABIA'),\n" +
                                    "('MMTG', 'TGZ', 'AEROPUERTO INTERNACIONAL DE TUXTLA'),\n" +
                                    "('MMPN', 'UPN', 'AEROPUERTO INTERNACIONAL DE URUAPAN'),\n" +
                                    "('MMVR', 'VER', 'AEROPUERTO INTERNACIONAL GENERAL HERIBERTO JARA'),\n" +
                                    "('MMVA', 'VSA', 'AEROPUERTO INTERNACIONAL CARLOS ROVIROSA PEREZ'),\n" +
                                    "('MMZC', 'ZCL', 'AEROPUERTO INTERNACIONAL GENERAL LEOBARDO C. RUIZ'),\n" +
                                    "('KBHM', 'BHM', 'BIRMINGHAM–SHUTTLESWORTH INTERNATIONAL AIRPORT'),\n" +
                                    "('KHSV', 'HSV', 'HUNTSVILLE INTERNATIONAL AIRPORT (CARL T. JONES FIELD)'),\n" +
                                    "('PANC', 'ANC', 'TED STEVENS ANCHORAGE INTERNATIONAL AIRPORT'),\n" +
                                    "('PAFA', 'FAI', 'FAIRBANKS INTERNATIONAL AIRPORT'),\n" +
                                    "('PAJN', 'JNU', 'JUNEAU INTERNATIONAL AIRPORT'),\n" +
                                    "('PAKT', 'KTN', 'KETCHIKAN INTERNATIONAL AIRPORT'),\n" +
                                    "('KIFP', 'IFP', 'LAUGHLIN/BULLHEAD INTERNATIONAL AIRPORT'),\n" +
                                    "('KPHX', 'PHX', 'PHOENIX SKY HARBOR INTERNATIONAL AIRPORT'),\n" +
                                    "('KTUS', 'TUS', 'TUCSON INTERNATIONAL AIRPORT'),\n" +
                                    "('KNYL', 'YUM', 'YUMA INTERNATIONAL AIRPORT / MCAS YUMA'),\n" +
                                    "('KFAT', 'FAT', 'FRESNO YOSEMITE INTERNATIONAL AIRPORT'),\n" +
                                    "('KLAX', 'LAX', 'LOS ANGELES INTERNATIONAL AIRPORT'),\n" +
                                    "('KOAK', 'OAK', 'OAKLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('KONT', 'ONT', 'ONTARIO INTERNATIONAL AIRPORT'),\n" +
                                    "('KPSP', 'PSP', 'PALM SPRINGS INTERNATIONAL AIRPORT'),\n" +
                                    "('KSMF', 'SMF', 'SACRAMENTO INTERNATIONAL AIRPORT'),\n" +
                                    "('KSAN', 'SAN', 'SAN DIEGO INTERNATIONAL AIRPORT'),\n" +
                                    "('KSFO', 'SFO', 'SAN FRANCISCO INTERNATIONAL AIRPORT'),\n" +
                                    "('KSJC', 'SJC', 'NORMAN Y. MINETA SAN JOSE INTERNATIONAL AIRPORT'),\n" +
                                    "('KDEN', 'DEN', 'DENVER INTERNATIONAL AIRPORT'),\n" +
                                    "('KBDL', 'BDL', 'BRADLEY INTERNATIONAL AIRPORT'),\n" +
                                    "('KDAB', 'DAB', 'DAYTONA BEACH INTERNATIONAL AIRPORT'),\n" +
                                    "('KFLL', 'FLL', 'FORT LAUDERDALE–HOLLYWOOD INTERNATIONAL AIRPORT'),\n" +
                                    "('KRSW', 'RSW', 'SOUTHWEST FLORIDA INTERNATIONAL AIRPORT'),\n" +
                                    "('KJAX', 'JAX', 'JACKSONVILLE INTERNATIONAL AIRPORT'),\n" +
                                    "('KEYW', 'EYW', 'KEY WEST INTERNATIONAL AIRPORT'),\n" +
                                    "('KMLB', 'MLB', 'MELBOURNE INTERNATIONAL AIRPORT'),\n" +
                                    "('KMIA', 'MIA', 'MIAMI INTERNATIONAL AIRPORT'),\n" +
                                    "('KMCO', 'MCO', 'ORLANDO INTERNATIONAL AIRPORT'),\n" +
                                    "('KSFB', 'SFB', 'ORLANDO SANFORD INTERNATIONAL AIRPORT'),\n" +
                                    "('KECP', 'ECP', 'NORTHWEST FLORIDA BEACHES INTERNATIONAL AIRPORT [NB 5]'),\n" +
                                    "('KSRQ', 'SRQ', 'SARASOTA–BRADENTON INTERNATIONAL AIRPORT'),\n" +
                                    "('KPIE', 'PIE', 'ST. PETERSBURG–CLEARWATER INTERNATIONAL AIRPORT'),\n" +
                                    "('KTPA', 'TPA', 'TAMPA INTERNATIONAL AIRPORT'),\n" +
                                    "('KPBI', 'PBI', 'PALM BEACH INTERNATIONAL AIRPORT'),\n" +
                                    "('KATL', 'ATL', 'HARTSFIELD-JACKSON ATLANTA INTERNATIONAL AIRPORT'),\n" +
                                    "('KSAV', 'SAV', 'SAVANNAH/HILTON HEAD INTERNATIONAL AIRPORT'),\n" +
                                    "('PHTO', 'ITO', 'HILO INTERNATIONAL AIRPORT'),\n" +
                                    "('PHNL', 'HNL', 'HONOLULU INTERNATIONAL AIRPORT / HICKAM AFB'),\n" +
                                    "('PHKO', 'KOA', 'KONA INTERNATIONAL AIRPORT AT KEAHOLE'),\n" +
                                    "('KORD', 'ORD', 'CHICAGO O''HARE INTERNATIONAL AIRPORT'),\n" +
                                    "('KMDW', 'MDW', 'CHICAGO MIDWAY INTERNATIONAL AIRPORT'),\n" +
                                    "('KMLI', 'MLI', 'QUAD CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('KPIA', 'PIA', 'GENERAL DOWNING - PEORIA INTERNATIONAL AIRPORT'),\n" +
                                    "('KRFD', 'RFD', 'CHICAGO ROCKFORD INTERNATIONAL AIRPORT'),\n" +
                                    "('KFWA', 'FWA', 'FORT WAYNE INTERNATIONAL AIRPORT'),\n" +
                                    "('KIND', 'IND', 'INDIANAPOLIS INTERNATIONAL AIRPORT'),\n" +
                                    "('KSBN', 'SBN', 'SOUTH BEND INTERNATIONAL AIRPORT '),\n" +
                                    "('KDSM', 'DSM', 'DES MOINES INTERNATIONAL AIRPORT'),\n" +
                                    "('KCVG', 'CVG', 'CINCINNATI/NORTHERN KENTUCKY INTERNATIONAL AIRPORT'),\n" +
                                    "('KSDF', 'SDF', 'LOUISVILLE INTERNATIONAL AIRPORT (STANDIFORD FIELD)'),\n" +
                                    "('KAEX', 'AEX', 'ALEXANDRIA INTERNATIONAL AIRPORT'),\n" +
                                    "('KMSY', 'MSY', 'LOUIS ARMSTRONG NEW ORLEANS INTERNATIONAL AIRPORT'),\n" +
                                    "('KBGR', 'BGR', 'BANGOR INTERNATIONAL AIRPORT'),\n" +
                                    "('KPWM', 'PWM', 'PORTLAND INTERNATIONAL JETPORT'),\n" +
                                    "('KBWI', 'BWI', 'BALTIMORE/WASHINGTON INTERNATIONAL THURGOOD MARSHALL AIRPORT'),\n" +
                                    "('KBOS', 'BOS', 'GEN. EDWARD LAWRENCE LOGAN INTERNATIONAL AIRPORT'),\n" +
                                    "('KFNT', 'FNT', 'BISHOP INTERNATIONAL AIRPORT'),\n" +
                                    "('KGRR', 'GRR', 'GERALD R. FORD INTERNATIONAL AIRPORT'),\n" +
                                    "('KAZO', 'AZO', 'KALAMAZOO/BATTLE CREEK INTERNATIONAL AIRPORT'),\n" +
                                    "('KLAN', 'LAN', 'CAPITAL REGION INTERNATIONAL AIRPORT'),\n" +
                                    "('KSAW', 'MQT', 'SAWYER INTERNATIONAL AIRPORT'),\n" +
                                    "('KMBS', 'MBS', 'MBS INTERNATIONAL AIRPORT'),\n" +
                                    "('KCIU', 'CIU', 'CHIPPEWA COUNTY INTERNATIONAL AIRPORT'),\n" +
                                    "('KDLH', 'DLH', 'DULUTH INTERNATIONAL AIRPORT'),\n" +
                                    "('KINL', 'INL', 'FALLS INTERNATIONAL AIRPORT'),\n" +
                                    "('KMSP', 'MSP', 'MINNEAPOLIS–ST. PAUL INTERNATIONAL AIRPORT (WOLD–CHAMBERLAIN FIELD)'),\n" +
                                    "('KRST', 'RST', 'ROCHESTER INTERNATIONAL AIRPORT'),\n" +
                                    "('KGPT', 'GPT', 'GULFPORT-BILOXI INTERNATIONAL AIRPORT'),\n" +
                                    "('KJAN', 'JAN', 'JACKSON-EVERS INTERNATIONAL AIRPORT'),\n" +
                                    "('KMCI', 'MCI', 'KANSAS CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('KSTL', 'STL', 'LAMBERT-ST. LOUIS INTERNATIONAL AIRPORT'),\n" +
                                    "('KBIL', 'BIL', 'BILLINGS LOGAN INTERNATIONAL AIRPORT'),\n" +
                                    "('KBZN', 'BZN', 'BOZEMAN YELLOWSTONE INTERNATIONAL AIRPORT '),\n" +
                                    "('KGTF', 'GTF', 'GREAT FALLS INTERNATIONAL AIRPORT'),\n" +
                                    "('KGPI', 'FCA', 'GLACIER PARK INTERNATIONAL AIRPORT'),\n" +
                                    "('KMSO', 'MSO', 'MISSOULA INTERNATIONAL AIRPORT'),\n" +
                                    "('KLAS', 'LAS', 'MCCARRAN INTERNATIONAL AIRPORT'),\n" +
                                    "('KRNO', 'RNO', 'RENO/TAHOE INTERNATIONAL AIRPORT'),\n" +
                                    "('KACY', 'ACY', 'ATLANTIC CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('KEWR', 'EWR', 'NEWARK LIBERTY INTERNATIONAL AIRPORT'),\n" +
                                    "('KABQ', 'ABQ', 'ALBUQUERQUE INTERNATIONAL SUNPORT'),\n" +
                                    "('KROW', 'ROW', 'ROSWELL INTERNATIONAL AIR CENTER'),\n" +
                                    "('KALB', 'ALB', 'ALBANY INTERNATIONAL AIRPORT'),\n" +
                                    "('KBUF', 'BUF', 'BUFFALO NIAGARA INTERNATIONAL AIRPORT'),\n" +
                                    "('KJFK', 'JFK', 'JOHN F. KENNEDY INTERNATIONAL AIRPORT'),\n" +
                                    "('KSWF', 'SWF', 'STEWART INTERNATIONAL AIRPORT'),\n" +
                                    "('KIAG', 'IAG', 'NIAGARA FALLS INTERNATIONAL AIRPORT'),\n" +
                                    "('KPBG', 'PBG', 'PLATTSBURGH INTERNATIONAL AIRPORT'),\n" +
                                    "('KROC', 'ROC', 'GREATER ROCHESTER INTERNATIONAL AIRPORT'),\n" +
                                    "('KSYR', 'SYR', 'SYRACUSE HANCOCK INTERNATIONAL AIRPORT'),\n" +
                                    "('KART', 'ART', 'WATERTOWN INTERNATIONAL AIRPORT'),\n" +
                                    "('KCLT', 'CLT', 'CHARLOTTE/DOUGLAS INTERNATIONAL AIRPORT'),\n" +
                                    "('KGSO', 'GSO', 'PIEDMONT TRIAD INTERNATIONAL AIRPORT'),\n" +
                                    "('KRDU', 'RDU', 'RALEIGH-DURHAM INTERNATIONAL AIRPORT'),\n" +
                                    "('KILM', 'ILM', 'WILMINGTON INTERNATIONAL AIRPORT'),\n" +
                                    "('KFAR', 'FAR', 'HECTOR INTERNATIONAL AIRPORT'),\n" +
                                    "('KGFK', 'GFK', 'GRAND FORKS INTERNATIONAL AIRPORT'),\n" +
                                    "('KMOT', 'MOT', 'MINOT INTERNATIONAL AIRPORT'),\n" +
                                    "('KISN', 'ISN', 'SLOULIN FIELD INTERNATIONAL AIRPORT'),\n" +
                                    "('KCLE', 'CLE', 'CLEVELAND-HOPKINS INTERNATIONAL AIRPORT'),\n" +
                                    "('KCMH', 'CMH', 'PORT COLUMBUS INTERNATIONAL AIRPORT'),\n" +
                                    "('KLCK', 'LCK', 'RICKENBACKER INTERNATIONAL AIRPORT'),\n" +
                                    "('KDAY', 'DAY', 'JAMES M. COX DAYTON INTERNATIONAL AIRPORT'),\n" +
                                    "('KTUL', 'TUL', 'TULSA INTERNATIONAL AIRPORT'),\n" +
                                    "('KMFR', 'MFR', 'ROGUE VALLEY INTERNATIONAL-MEDFORD AIRPORT'),\n" +
                                    "('KPDX', 'PDX', 'PORTLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('KABE', 'ABE', 'LEHIGH VALLEY INTERNATIONAL AIRPORT'),\n" +
                                    "('KERI', 'ERI', 'ERIE INTERNATIONAL AIRPORT (TOM RIDGE FIELD)'),\n" +
                                    "('KMDT', 'MDT', 'HARRISBURG INTERNATIONAL AIRPORT'),\n" +
                                    "('KPHL', 'PHL', 'PHILADELPHIA INTERNATIONAL AIRPORT'),\n" +
                                    "('KPIT', 'PIT', 'PITTSBURGH INTERNATIONAL AIRPORT'),\n" +
                                    "('KAVP', 'AVP', 'WILKES-BARRE/SCRANTON INTERNATIONAL AIRPORT'),\n" +
                                    "('KCHS', 'CHS', 'CHARLESTON INTERNATIONAL AIRPORT / CHARLESTON AFB'),\n" +
                                    "('KGSP', 'GSP', 'GREENVILLE-SPARTANBURG INTERNATIONAL AIRPORT (ROGER MILLIKEN FIELD)'),\n" +
                                    "('KMYR', 'MYR', 'MYRTLE BEACH INTERNATIONAL AIRPORT'),\n" +
                                    "('KMEM', 'MEM', 'MEMPHIS INTERNATIONAL AIRPORT'),\n" +
                                    "('KBNA', 'BNA', 'NASHVILLE INTERNATIONAL AIRPORT (BERRY FIELD)'),\n" +
                                    "('KAMA', 'AMA', 'RICK HUSBAND AMARILLO INTERNATIONAL AIRPORT'),\n" +
                                    "('KAUS', 'AUS', 'AUSTIN-BERGSTROM INTERNATIONAL AIRPORT'),\n" +
                                    "('KBRO', 'BRO', 'BROWNSVILLE/SOUTH PADRE ISLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('KCRP', 'CRP', 'CORPUS CHRISTI INTERNATIONAL AIRPORT'),\n" +
                                    "('KDFW', 'DFW', 'DALLAS/FORT WORTH INTERNATIONAL AIRPORT'),\n" +
                                    "('KDRT', 'DRT', 'DEL RIO INTERNATIONAL AIRPORT'),\n" +
                                    "('KELP', 'ELP', 'EL PASO INTERNATIONAL AIRPORT'),\n" +
                                    "('KHRL', 'HRL', 'VALLEY INTERNATIONAL AIRPORT'),\n" +
                                    "('KLRD', 'LRD', 'LAREDO INTERNATIONAL AIRPORT'),\n" +
                                    "('KLBB', 'LBB', 'LUBBOCK PRESTON SMITH INTERNATIONAL AIRPORT'),\n" +
                                    "('KMFE', 'MFE', 'MCALLEN-MILLER INTERNATIONAL AIRPORT (MCALLEN MILLER INTERNATIONAL)'),\n" +
                                    "('KMAF', 'MAF', 'MIDLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('KSAT', 'SAT', 'SAN ANTONIO INTERNATIONAL AIRPORT'),\n" +
                                    "('KSLC', 'SLC', 'SALT LAKE CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('KBTV', 'BTV', 'BURLINGTON INTERNATIONAL AIRPORT'),\n" +
                                    "('KPHF', 'PHF', 'NEWPORT NEWS/WILLIAMSBURG INTERNATIONAL AIRPORT'),\n" +
                                    "('KORF', 'ORF', 'NORFOLK INTERNATIONAL AIRPORT'),\n" +
                                    "('KRIC', 'RIC', 'RICHMOND INTERNATIONAL AIRPORT (BYRD FIELD)'),\n" +
                                    "('KIAD', 'IAD', 'WASHINGTON DULLES INTERNATIONAL AIRPORT'),\n" +
                                    "('KBLI', 'BLI', 'BELLINGHAM INTERNATIONAL AIRPORT'),\n" +
                                    "('KCLM', 'CLM', 'WILLIAM R. FAIRCHILD INTERNATIONAL AIRPORT'),\n" +
                                    "('KBFI', 'BFI', 'KING COUNTY INTERNATIONAL AIRPORT (BOEING FIELD)'),\n" +
                                    "('KSEA', 'SEA', 'SEATTLE–TACOMA INTERNATIONAL AIRPORT'),\n" +
                                    "('KGEG', 'GEG', 'SPOKANE INTERNATIONAL AIRPORT (GEIGER FIELD)'),\n" +
                                    "('KGRB', 'GRB', 'AUSTIN STRAUBEL INTERNATIONAL AIRPORT'),\n" +
                                    "('KMKE', 'MKE', 'GENERAL MITCHELL INTERNATIONAL AIRPORT'),\n" +
                                    "('KCPR', 'CPR', 'CASPER/NATRONA COUNTY INTERNATIONAL AIRPORT');";
            crearTablas += "INSERT INTO AEROPUERTOS_MANIFIESTOS (codigo_iata, codigo_oaci, nombre) VALUES\n" +
                                    "('ACA', 'MMAA', 'AEROPUERTO INTERNACIONAL GENERAL JUAN N. ALVAREZ'),\n" +
                                    "('AGU', 'MMAS', 'AEROPUERTO INTERNACIONAL LICENCIADO JESUS TERAN PEREDO'),\n" +
                                    "('CPE', 'MMCP', 'AEROPUERTO INTERNACIONAL ING. ALBERTO ACUÑA ONGAY'),\n" +
                                    "('CUN', 'MMUN', 'AEROPUERTO INTERNACIONAL DE CANCUN'),\n" +
                                    "('CTM', 'MMCM', 'AEROPUERTO INTERNACIONAL DE CHETUMAL'),\n" +
                                    "('CZA', 'MMCT', 'AEROPUERTO INTERNACIONAL DE KAUA'),\n" +
                                    "('CUU', 'MMCU', 'AEROPUERTO INTERNACIONAL GENERAL ROBERTO FIERRO VILLALOBOS'),\n" +
                                    "('ACN', 'MMCC', 'AEROPUERTO INTERNACIONAL DE CIUDAD ACUÑA'),\n" +
                                    "('MEX', 'MMMX', 'AEROPUERTO INTERNACIONAL DE LA CIUDAD DE MEXICO'),\n" +
                                    "('CME', 'MMCE', 'AEROPUERTO INTERNACIONAL DE CIUDAD DEL CARMEN'),\n" +
                                    "('CJS', 'MMCS', 'AEROPUERTO INTERNACIONAL ABRAHAM GONZALEZ'),\n" +
                                    "('CEN', 'MMCN', 'AEROPUERTO INTERNACIONAL DE CIUDAD OBREGON'),\n" +
                                    "('CVM', 'MMCV', 'AEROPUERTO INTERNACIONAL GENERAL PEDRO JOSE MENDEZ'),\n" +
                                    "('CZM', 'MMCZ', 'AEROPUERTO INTERNACIONAL DE COZUMEL'),\n" +
                                    "('CVJ', 'MMCB', 'AEROPUERTO INTERNACIONAL GENERAL MARIANO MATAMOROS'),\n" +
                                    "('CUL', 'MMCL', 'AEROPUERTO INTERNACIONAL FEDERAL DE CULIACAN'),\n" +
                                    "('DGO', 'MMDO', 'AEROPUERTO INTERNACIONAL GENERAL GUADALUPE VICTORIA'),\n" +
                                    "('ESE', 'MMES', 'AEROPUERTO INTERNACIONAL DE ENSENADA'),\n" +
                                    "('LOV', 'MMMV', 'AEROPUERTO INTERNACIONAL VENUSTIANO CARRANZA'),\n" +
                                    "('GDL', 'MMGL', 'AEROPUERTO INTERNACIONAL DE GUADALAJARA'),\n" +
                                    "('GYM', 'MMGM', 'AEROPUERTO INTERNACIONAL GENERAL JOSE MARIA YAÑEZ'),\n" +
                                    "('HMO', 'MMHO', 'AEROPUERTO INTERNACIONAL GENERAL IGNACIO PESQUEIRA GARCIA'),\n" +
                                    "('HUX', 'MMBT', 'AEROPUERTO INTERNACIONAL DE BAHIAS DE HUATULCO'),\n" +
                                    "('ZIH', 'MMZH', 'AEROPUERTO INTERNACIONAL DE IXTAPA-ZIHUATANEJO'),\n" +
                                    "('LAP', 'MMLP', 'AEROPUERTO INTERNACIONAL MANUEL MARQUEZ DE LEON'),\n" +
                                    "('BJX', 'MMLO', 'AEROPUERTO INTERNACIONAL DEL BAJIO'),\n" +
                                    "('LTO', 'MMLT', 'AEROPUERTO INTERNACIONAL DE LORETO'),\n" +
                                    "('SJD', 'MMSD', 'AEROPUERTO INTERNACIONAL DE LOS CABOS'),\n" +
                                    "('LMM', 'MMLM', 'AEROPUERTO INTERNACIONAL FEDERAL DEL VALLE DEL FUERTE'),\n" +
                                    "('ZLO', 'MMZO', 'AEROPUERTO INTERNACIONAL PLAYA DE ORO'),\n" +
                                    "('MAM', 'MMMA', 'AEROPUERTO INTERNACIONAL GENERAL SERVANDO CANALES'),\n" +
                                    "('MZT', 'MMMZ', 'AEROPUERTO INTERNACIONAL GENERAL RAFAEL BUELNA'),\n" +
                                    "('MID', 'MMMD', 'AEROPUERTO INTERNACIONAL DE MERIDA'),\n" +
                                    "('MXL', 'MMML', 'AEROPUERTO INTERNACIONAL GENERAL RODOLFO SANCHEZ TABOADA'),\n" +
                                    "('MTT', 'MMMT', 'AEROPUERTO INTERNACIONAL DE MINATITLAN'),\n" +
                                    "('MTY', 'MMMY', 'AEROPUERTO INTERNACIONAL DE MONTERREY'),\n" +
                                    "('NTR', 'MMAN', 'AEROPUERTO INTERNACIONAL DEL NORTE'),\n" +
                                    "('MLM', 'MMMM', 'AEROPUERTO INTERNACIONAL GENERAL FRANCISCO J. MUJICA'),\n" +
                                    "('NOG', 'MMNG', 'AEROPUERTO INTERNACIONAL DE NOGALES'),\n" +
                                    "('NLD', 'MMNL', 'AEROPUERTO INTERNACIONAL QUETZALCOATL'),\n" +
                                    "('OAX', 'MMOX', 'AEROPUERTO INTERNACIONAL XOXOCOTLAN'),\n" +
                                    "('PQM', 'MMPQ', 'AEROPUERTO INTERNACIONAL DE PALENQUE'),\n" +
                                    "('PDS', 'MMPG', 'AEROPUERTO INTERNACIONAL DE PIEDRAS NEGRAS'),\n" +
                                    "('PBC', 'MMPB', 'AEROPUERTO INTERNACIONAL DE PUEBLA'),\n" +
                                    "('PXM', 'MMPS', 'AEROPUERTO INTERNACIONAL DE PUERTO ESCONDIDO'),\n" +
                                    "('PPE', 'MMPE', 'AEROPUERTO INTERNACIONAL DE MAR DE CORTES'),\n" +
                                    "('PVR', 'MMPR', 'AEROPUERTO INTERNACIONAL LICENCIADO GUSTAVO DIAZ ORDAZ'),\n" +
                                    "('QRO', 'MMQT', 'AEROPUERTO INTERCONTINENTAL DE QUERETARO'),\n" +
                                    "('REX', 'MMRX', 'AEROPUERTO INTERNACIONAL GENERAL LUCIO BLANCO'),\n" +
                                    "('SLW', 'MMIO', 'AEROPUERTO INTERNACIONAL PLAN DE GUADALUPE'),\n" +
                                    "('SFH', 'MMSF', 'AEROPUERTO INTERNACIONAL DE SAN FELIPE'),\n" +
                                    "('SLP', 'MMSP', 'AEROPUERTO INTERNACIONAL PONCIANO ARRIAGA'),\n" +
                                    "('UAC', 'MM76', 'AEROPUERTO INTERNACIONAL DE SAN LUIS RIO COLORADO'),\n" +
                                    "('TAM', 'MMTM', 'AEROPUERTO INTERNACIONAL DE TAMPICO'),\n" +
                                    "('TAP', 'MMTP', 'AEROPUERTO INTERNACIONAL DE TAPACHULA'),\n" +
                                    "('TNY', 'MMEP', 'AEROPUERTO INTERNACIONAL AMADO NERVO'),\n" +
                                    "('TIJ', 'MMTJ', 'AEROPUERTO INTERNACIONAL DE TIJUANA'),\n" +
                                    "('TLC', 'MMTO', 'AEROPUERTO INTERNACIONAL LIC. ADOLFO LOPEZ MATEOS'),\n" +
                                    "('TRC', 'MMTC', 'AEROPUERTO INTERNACIONAL DE TORREON FRANCISCO SARABIA'),\n" +
                                    "('TGZ', 'MMTG', 'AEROPUERTO INTERNACIONAL DE TUXTLA'),\n" +
                                    "('UPN', 'MMPN', 'AEROPUERTO INTERNACIONAL DE URUAPAN'),\n" +
                                    "('VER', 'MMVR', 'AEROPUERTO INTERNACIONAL GENERAL HERIBERTO JARA'),\n" +
                                    "('VSA', 'MMVA', 'AEROPUERTO INTERNACIONAL CARLOS ROVIROSA PEREZ'),\n" +
                                    "('ZCL', 'MMZC', 'AEROPUERTO INTERNACIONAL GENERAL LEOBARDO C. RUIZ'),\n" +
                                    "('BHM', 'KBHM', 'BIRMINGHAM–SHUTTLESWORTH INTERNATIONAL AIRPORT'),\n" +
                                    "('HSV', 'KHSV', 'HUNTSVILLE INTERNATIONAL AIRPORT (CARL T. JONES FIELD)'),\n" +
                                    "('ANC', 'PANC', 'TED STEVENS ANCHORAGE INTERNATIONAL AIRPORT'),\n" +
                                    "('FAI', 'PAFA', 'FAIRBANKS INTERNATIONAL AIRPORT'),\n" +
                                    "('JNU', 'PAJN', 'JUNEAU INTERNATIONAL AIRPORT'),\n" +
                                    "('KTN', 'PAKT', 'KETCHIKAN INTERNATIONAL AIRPORT'),\n" +
                                    "('IFP', 'KIFP', 'LAUGHLIN/BULLHEAD INTERNATIONAL AIRPORT'),\n" +
                                    "('PHX', 'KPHX', 'PHOENIX SKY HARBOR INTERNATIONAL AIRPORT'),\n" +
                                    "('TUS', 'KTUS', 'TUCSON INTERNATIONAL AIRPORT'),\n" +
                                    "('YUM', 'KNYL', 'YUMA INTERNATIONAL AIRPORT / MCAS YUMA'),\n" +
                                    "('FAT', 'KFAT', 'FRESNO YOSEMITE INTERNATIONAL AIRPORT'),\n" +
                                    "('LAX', 'KLAX', 'LOS ANGELES INTERNATIONAL AIRPORT'),\n" +
                                    "('OAK', 'KOAK', 'OAKLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('ONT', 'KONT', 'ONTARIO INTERNATIONAL AIRPORT'),\n" +
                                    "('PSP', 'KPSP', 'PALM SPRINGS INTERNATIONAL AIRPORT'),\n" +
                                    "('SMF', 'KSMF', 'SACRAMENTO INTERNATIONAL AIRPORT'),\n" +
                                    "('SAN', 'KSAN', 'SAN DIEGO INTERNATIONAL AIRPORT'),\n" +
                                    "('SFO', 'KSFO', 'SAN FRANCISCO INTERNATIONAL AIRPORT'),\n" +
                                    "('SJC', 'KSJC', 'NORMAN Y. MINETA SAN JOSE INTERNATIONAL AIRPORT'),\n" +
                                    "('DEN', 'KDEN', 'DENVER INTERNATIONAL AIRPORT'),\n" +
                                    "('BDL', 'KBDL', 'BRADLEY INTERNATIONAL AIRPORT'),\n" +
                                    "('DAB', 'KDAB', 'DAYTONA BEACH INTERNATIONAL AIRPORT'),\n" +
                                    "('FLL', 'KFLL', 'FORT LAUDERDALE–HOLLYWOOD INTERNATIONAL AIRPORT'),\n" +
                                    "('RSW', 'KRSW', 'SOUTHWEST FLORIDA INTERNATIONAL AIRPORT'),\n" +
                                    "('JAX', 'KJAX', 'JACKSONVILLE INTERNATIONAL AIRPORT'),\n" +
                                    "('EYW', 'KEYW', 'KEY WEST INTERNATIONAL AIRPORT'),\n" +
                                    "('MLB', 'KMLB', 'MELBOURNE INTERNATIONAL AIRPORT'),\n" +
                                    "('MIA', 'KMIA', 'MIAMI INTERNATIONAL AIRPORT'),\n" +
                                    "('MCO', 'KMCO', 'ORLANDO INTERNATIONAL AIRPORT'),\n" +
                                    "('SFB', 'KSFB', 'ORLANDO SANFORD INTERNATIONAL AIRPORT'),\n" +
                                    "('ECP', 'KECP', 'NORTHWEST FLORIDA BEACHES INTERNATIONAL AIRPORT [NB 5]'),\n" +
                                    "('SRQ', 'KSRQ', 'SARASOTA–BRADENTON INTERNATIONAL AIRPORT'),\n" +
                                    "('PIE', 'KPIE', 'ST. PETERSBURG–CLEARWATER INTERNATIONAL AIRPORT'),\n" +
                                    "('TPA', 'KTPA', 'TAMPA INTERNATIONAL AIRPORT'),\n" +
                                    "('PBI', 'KPBI', 'PALM BEACH INTERNATIONAL AIRPORT'),\n" +
                                    "('ATL', 'KATL', 'HARTSFIELD-JACKSON ATLANTA INTERNATIONAL AIRPORT'),\n" +
                                    "('SAV', 'KSAV', 'SAVANNAH/HILTON HEAD INTERNATIONAL AIRPORT'),\n" +
                                    "('ITO', 'PHTO', 'HILO INTERNATIONAL AIRPORT'),\n" +
                                    "('HNL', 'PHNL', 'HONOLULU INTERNATIONAL AIRPORT / HICKAM AFB'),\n" +
                                    "('KOA', 'PHKO', 'KONA INTERNATIONAL AIRPORT AT KEAHOLE'),\n" +
                                    "('ORD', 'KORD', 'CHICAGO O''HARE INTERNATIONAL AIRPORT'),\n" +
                                    "('MDW', 'KMDW', 'CHICAGO MIDWAY INTERNATIONAL AIRPORT'),\n" +
                                    "('MLI', 'KMLI', 'QUAD CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('PIA', 'KPIA', 'GENERAL DOWNING - PEORIA INTERNATIONAL AIRPORT'),\n" +
                                    "('RFD', 'KRFD', 'CHICAGO ROCKFORD INTERNATIONAL AIRPORT'),\n" +
                                    "('FWA', 'KFWA', 'FORT WAYNE INTERNATIONAL AIRPORT'),\n" +
                                    "('IND', 'KIND', 'INDIANAPOLIS INTERNATIONAL AIRPORT'),\n" +
                                    "('SBN', 'KSBN', 'SOUTH BEND INTERNATIONAL AIRPORT '),\n" +
                                    "('DSM', 'KDSM', 'DES MOINES INTERNATIONAL AIRPORT'),\n" +
                                    "('CVG', 'KCVG', 'CINCINNATI/NORTHERN KENTUCKY INTERNATIONAL AIRPORT'),\n" +
                                    "('SDF', 'KSDF', 'LOUISVILLE INTERNATIONAL AIRPORT (STANDIFORD FIELD)'),\n" +
                                    "('AEX', 'KAEX', 'ALEXANDRIA INTERNATIONAL AIRPORT'),\n" +
                                    "('MSY', 'KMSY', 'LOUIS ARMSTRONG NEW ORLEANS INTERNATIONAL AIRPORT'),\n" +
                                    "('BGR', 'KBGR', 'BANGOR INTERNATIONAL AIRPORT'),\n" +
                                    "('PWM', 'KPWM', 'PORTLAND INTERNATIONAL JETPORT'),\n" +
                                    "('BWI', 'KBWI', 'BALTIMORE/WASHINGTON INTERNATIONAL THURGOOD MARSHALL AIRPORT'),\n" +
                                    "('BOS', 'KBOS', 'GEN. EDWARD LAWRENCE LOGAN INTERNATIONAL AIRPORT'),\n" +
                                    "('FNT', 'KFNT', 'BISHOP INTERNATIONAL AIRPORT'),\n" +
                                    "('GRR', 'KGRR', 'GERALD R. FORD INTERNATIONAL AIRPORT'),\n" +
                                    "('AZO', 'KAZO', 'KALAMAZOO/BATTLE CREEK INTERNATIONAL AIRPORT'),\n" +
                                    "('LAN', 'KLAN', 'CAPITAL REGION INTERNATIONAL AIRPORT'),\n" +
                                    "('MQT', 'KSAW', 'SAWYER INTERNATIONAL AIRPORT'),\n" +
                                    "('MBS', 'KMBS', 'MBS INTERNATIONAL AIRPORT'),\n" +
                                    "('CIU', 'KCIU', 'CHIPPEWA COUNTY INTERNATIONAL AIRPORT'),\n" +
                                    "('DLH', 'KDLH', 'DULUTH INTERNATIONAL AIRPORT'),\n" +
                                    "('INL', 'KINL', 'FALLS INTERNATIONAL AIRPORT'),\n" +
                                    "('MSP', 'KMSP', 'MINNEAPOLIS–ST. PAUL INTERNATIONAL AIRPORT (WOLD–CHAMBERLAIN FIELD)'),\n" +
                                    "('RST', 'KRST', 'ROCHESTER INTERNATIONAL AIRPORT'),\n" +
                                    "('GPT', 'KGPT', 'GULFPORT-BILOXI INTERNATIONAL AIRPORT'),\n" +
                                    "('JAN', 'KJAN', 'JACKSON-EVERS INTERNATIONAL AIRPORT'),\n" +
                                    "('MCI', 'KMCI', 'KANSAS CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('STL', 'KSTL', 'LAMBERT-ST. LOUIS INTERNATIONAL AIRPORT'),\n" +
                                    "('BIL', 'KBIL', 'BILLINGS LOGAN INTERNATIONAL AIRPORT'),\n" +
                                    "('BZN', 'KBZN', 'BOZEMAN YELLOWSTONE INTERNATIONAL AIRPORT '),\n" +
                                    "('GTF', 'KGTF', 'GREAT FALLS INTERNATIONAL AIRPORT'),\n" +
                                    "('FCA', 'KGPI', 'GLACIER PARK INTERNATIONAL AIRPORT'),\n" +
                                    "('MSO', 'KMSO', 'MISSOULA INTERNATIONAL AIRPORT'),\n" +
                                    "('LAS', 'KLAS', 'MCCARRAN INTERNATIONAL AIRPORT'),\n" +
                                    "('RNO', 'KRNO', 'RENO/TAHOE INTERNATIONAL AIRPORT'),\n" +
                                    "('ACY', 'KACY', 'ATLANTIC CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('EWR', 'KEWR', 'NEWARK LIBERTY INTERNATIONAL AIRPORT'),\n" +
                                    "('ABQ', 'KABQ', 'ALBUQUERQUE INTERNATIONAL SUNPORT'),\n" +
                                    "('ROW', 'KROW', 'ROSWELL INTERNATIONAL AIR CENTER'),\n" +
                                    "('ALB', 'KALB', 'ALBANY INTERNATIONAL AIRPORT'),\n" +
                                    "('BUF', 'KBUF', 'BUFFALO NIAGARA INTERNATIONAL AIRPORT'),\n" +
                                    "('JFK', 'KJFK', 'JOHN F. KENNEDY INTERNATIONAL AIRPORT'),\n" +
                                    "('SWF', 'KSWF', 'STEWART INTERNATIONAL AIRPORT'),\n" +
                                    "('IAG', 'KIAG', 'NIAGARA FALLS INTERNATIONAL AIRPORT'),\n" +
                                    "('PBG', 'KPBG', 'PLATTSBURGH INTERNATIONAL AIRPORT'),\n" +
                                    "('ROC', 'KROC', 'GREATER ROCHESTER INTERNATIONAL AIRPORT'),\n" +
                                    "('SYR', 'KSYR', 'SYRACUSE HANCOCK INTERNATIONAL AIRPORT'),\n" +
                                    "('ART', 'KART', 'WATERTOWN INTERNATIONAL AIRPORT'),\n" +
                                    "('CLT', 'KCLT', 'CHARLOTTE/DOUGLAS INTERNATIONAL AIRPORT'),\n" +
                                    "('GSO', 'KGSO', 'PIEDMONT TRIAD INTERNATIONAL AIRPORT'),\n" +
                                    "('RDU', 'KRDU', 'RALEIGH-DURHAM INTERNATIONAL AIRPORT'),\n" +
                                    "('ILM', 'KILM', 'WILMINGTON INTERNATIONAL AIRPORT'),\n" +
                                    "('FAR', 'KFAR', 'HECTOR INTERNATIONAL AIRPORT'),\n" +
                                    "('GFK', 'KGFK', 'GRAND FORKS INTERNATIONAL AIRPORT'),\n" +
                                    "('MOT', 'KMOT', 'MINOT INTERNATIONAL AIRPORT'),\n" +
                                    "('ISN', 'KISN', 'SLOULIN FIELD INTERNATIONAL AIRPORT'),\n" +
                                    "('CLE', 'KCLE', 'CLEVELAND-HOPKINS INTERNATIONAL AIRPORT'),\n" +
                                    "('CMH', 'KCMH', 'PORT COLUMBUS INTERNATIONAL AIRPORT'),\n" +
                                    "('LCK', 'KLCK', 'RICKENBACKER INTERNATIONAL AIRPORT'),\n" +
                                    "('DAY', 'KDAY', 'JAMES M. COX DAYTON INTERNATIONAL AIRPORT'),\n" +
                                    "('TUL', 'KTUL', 'TULSA INTERNATIONAL AIRPORT'),\n" +
                                    "('MFR', 'KMFR', 'ROGUE VALLEY INTERNATIONAL-MEDFORD AIRPORT'),\n" +
                                    "('PDX', 'KPDX', 'PORTLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('ABE', 'KABE', 'LEHIGH VALLEY INTERNATIONAL AIRPORT'),\n" +
                                    "('ERI', 'KERI', 'ERIE INTERNATIONAL AIRPORT (TOM RIDGE FIELD)'),\n" +
                                    "('MDT', 'KMDT', 'HARRISBURG INTERNATIONAL AIRPORT'),\n" +
                                    "('PHL', 'KPHL', 'PHILADELPHIA INTERNATIONAL AIRPORT'),\n" +
                                    "('PIT', 'KPIT', 'PITTSBURGH INTERNATIONAL AIRPORT'),\n" +
                                    "('AVP', 'KAVP', 'WILKES-BARRE/SCRANTON INTERNATIONAL AIRPORT'),\n" +
                                    "('CHS', 'KCHS', 'CHARLESTON INTERNATIONAL AIRPORT / CHARLESTON AFB'),\n" +
                                    "('GSP', 'KGSP', 'GREENVILLE-SPARTANBURG INTERNATIONAL AIRPORT (ROGER MILLIKEN FIELD)'),\n" +
                                    "('MYR', 'KMYR', 'MYRTLE BEACH INTERNATIONAL AIRPORT'),\n" +
                                    "('MEM', 'KMEM', 'MEMPHIS INTERNATIONAL AIRPORT'),\n" +
                                    "('BNA', 'KBNA', 'NASHVILLE INTERNATIONAL AIRPORT (BERRY FIELD)'),\n" +
                                    "('AMA', 'KAMA', 'RICK HUSBAND AMARILLO INTERNATIONAL AIRPORT'),\n" +
                                    "('AUS', 'KAUS', 'AUSTIN-BERGSTROM INTERNATIONAL AIRPORT'),\n" +
                                    "('BRO', 'KBRO', 'BROWNSVILLE/SOUTH PADRE ISLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('CRP', 'KCRP', 'CORPUS CHRISTI INTERNATIONAL AIRPORT'),\n" +
                                    "('DFW', 'KDFW', 'DALLAS/FORT WORTH INTERNATIONAL AIRPORT'),\n" +
                                    "('DRT', 'KDRT', 'DEL RIO INTERNATIONAL AIRPORT'),\n" +
                                    "('ELP', 'KELP', 'EL PASO INTERNATIONAL AIRPORT'),\n" +
                                    "('HRL', 'KHRL', 'VALLEY INTERNATIONAL AIRPORT'),\n" +
                                    "('LRD', 'KLRD', 'LAREDO INTERNATIONAL AIRPORT'),\n" +
                                    "('LBB', 'KLBB', 'LUBBOCK PRESTON SMITH INTERNATIONAL AIRPORT'),\n" +
                                    "('MFE', 'KMFE', 'MCALLEN-MILLER INTERNATIONAL AIRPORT (MCALLEN MILLER INTERNATIONAL)'),\n" +
                                    "('MAF', 'KMAF', 'MIDLAND INTERNATIONAL AIRPORT'),\n" +
                                    "('SAT', 'KSAT', 'SAN ANTONIO INTERNATIONAL AIRPORT'),\n" +
                                    "('SLC', 'KSLC', 'SALT LAKE CITY INTERNATIONAL AIRPORT'),\n" +
                                    "('BTV', 'KBTV', 'BURLINGTON INTERNATIONAL AIRPORT'),\n" +
                                    "('PHF', 'KPHF', 'NEWPORT NEWS/WILLIAMSBURG INTERNATIONAL AIRPORT'),\n" +
                                    "('ORF', 'KORF', 'NORFOLK INTERNATIONAL AIRPORT'),\n" +
                                    "('RIC', 'KRIC', 'RICHMOND INTERNATIONAL AIRPORT (BYRD FIELD)'),\n" +
                                    "('IAD', 'KIAD', 'WASHINGTON DULLES INTERNATIONAL AIRPORT'),\n" +
                                    "('BLI', 'KBLI', 'BELLINGHAM INTERNATIONAL AIRPORT'),\n" +
                                    "('CLM', 'KCLM', 'WILLIAM R. FAIRCHILD INTERNATIONAL AIRPORT'),\n" +
                                    "('BFI', 'KBFI', 'KING COUNTY INTERNATIONAL AIRPORT (BOEING FIELD)'),\n" +
                                    "('SEA', 'KSEA', 'SEATTLE–TACOMA INTERNATIONAL AIRPORT'),\n" +
                                    "('GEG', 'KGEG', 'SPOKANE INTERNATIONAL AIRPORT (GEIGER FIELD)'),\n" +
                                    "('GRB', 'KGRB', 'AUSTIN STRAUBEL INTERNATIONAL AIRPORT'),\n" +
                                    "('MKE', 'KMKE', 'GENERAL MITCHELL INTERNATIONAL AIRPORT'),\n" +
                                    "('CPR', 'KCPR', 'CASPER/NATRONA COUNTY INTERNATIONAL AIRPORT');";
            String [] instrucciones = crearTablas.split(";");
            for (String instruccion : instrucciones) {
                if (continuar) {
                    instruccion += ";";
                    if (!ping.ejecutarModificacionStatement(instruccion)) {
                        JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"ERROR AL INSERTAR TABLAS",JOptionPane.ERROR_MESSAGE);
                        if (!ping.ejecutarModificacionStatement("DROP DATABASE BASEAEROPUERTO")) {
                            JOptionPane.showMessageDialog(need_ventana,ping.getMensajesError(),"ERROR AL ELIMINAR TABLAS",JOptionPane.ERROR_MESSAGE);
                        }
                        continuar = false;
                    } else {
                        publish("false",Integer.toString(++tareasRealizadas));
                    }
                }
            }
        }
        if (continuar) {
            String directorio = "C:\\Sicacomp";
            File raiz = new File(directorio);
            if (!raiz.exists()) {
                if (!raiz.mkdirs()){
                    continuar = false;
                }
            }
            if (continuar) {
                String archivo = "C:\\Sicacomp\\sicacomp.txt";
                if (CSONS.crearOrden(String.valueOf(need_jPasswordField.getPassword()),9,archivo)) {
                    publish("false",Integer.toString(++tareasRealizadas));
                } else {
                    continuar = false;
                }
            } 
        }
        return null; 
    }
    
    @Override
    protected void process(List<String> chunks) {
        need_jButton.setEnabled(Boolean.valueOf(chunks.get(0)));
        need_jProgressBar.setValue(Integer.parseInt(chunks.get(1)));
        need_jProgressBar.repaint();
    }

    @Override
    protected void done() {
        if (continuar) {
            need_jProgressBar.setValue(35);
            need_jProgressBar.repaint();
            JOptionPane.showMessageDialog(need_ventana,"LA BASE DE DATOS SE HA CREADO EXITOSAMENTE","",JOptionPane.INFORMATION_MESSAGE,bien);
            need_jButton.setEnabled(false);
            need_jButton.setText("HECHO");
        } else {
            need_jButton.setEnabled(true);
            need_jButton.setText("INTENTAR NUEVAMENTE");
        }   
    }
    
}
