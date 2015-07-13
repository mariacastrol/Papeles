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
                                    /////
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
                                    ") ENGINE=InnoDB\n";
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
            need_jProgressBar.setValue(33);
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
