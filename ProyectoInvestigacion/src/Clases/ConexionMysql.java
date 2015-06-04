/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author ADOLFO
 */
public class ConexionMysql {
    
    private String strCadenaConexion = "";
    
    private String strBaseDatos; 
    private String strServidor; //localhost
    private String strUsuario;
    private String strPassword;
    private String mensajesError;
    private final String strDriverMySQL = "com.mysql.jdbc.Driver";
    private boolean alertaMensajesError;
    
    private Connection con = null; //maneja la conexion a mysql
    ///*******************************************
    public ConexionMysql() {
        this.strBaseDatos = "";
        this.strServidor = "";
        this.strUsuario = "";
        this.strPassword = "";
        this.mensajesError = "";
        this.alertaMensajesError = false;
    }
    ///*******************************************
    private boolean conectarServidor(String servidor,String usuario,String pw,String bd, String driver, String metodoQueInvoca) {
        try {
            Class.forName(driver);
            strCadenaConexion = "jdbc:mysql://"+servidor+"/"+bd;
            con = DriverManager.getConnection(strCadenaConexion,usuario,pw);
            return con != null;
        } catch (Exception e) {
            mensajesError = "CLASE: ConexionMsql\nINVOCADO EN: " + metodoQueInvoca;
            mensajesError += "\nMETODO: conectarServidor\nERROR: ";
            mensajesError += e.getMessage();
            return false;
        } 
    }
    ///*******************************************
    public boolean conectarBD(String servidor, String usuario, String password, String BD) {
        strServidor = servidor;
        strUsuario = usuario;
        strPassword = password;
        strBaseDatos = BD;
        return conectarServidor(strServidor,strUsuario,strPassword,strBaseDatos,strDriverMySQL,"conectarBD");  
    }
    ///*******************************************
    public Connection conexion() {
        return con;
    } 
    ///*******************************************
    public String getCadenaConexion() {
        return strCadenaConexion;
    }
    ///*******************************************
    public String getBaseDato() {
        return strBaseDatos;
    }
    ///*******************************************
    public String getServidor() {
        return strServidor;
    }
    ///*******************************************
    public String getUsuario() {
        return strUsuario;
    }
    ///*******************************************
    public String getDriverMySQL() {
        return strDriverMySQL;
    }
    ///*******************************************
    public String getMensajesError() {
        return mensajesError;
    }
    ///*******************************************
    public void setMensajesError(String mensajesError) {
        this.mensajesError = mensajesError;
    }
    ///*******************************************
    private boolean ejecutarModificacionStatement(String instruccion, String metodoQueInvoca, String mensajeSiDuplicado) {
        try {
            Statement instruccionSt = con.createStatement();
            instruccionSt.executeUpdate(instruccion);
            return true;
        } catch (Exception e) {
            String error = e.getMessage();
            if (error.startsWith("Duplicate entry")) {
                mensajesError = mensajeSiDuplicado;
            } else {
                mensajesError = "CLASE: ConexionMsql\nINVOCADO EN: " + metodoQueInvoca;
                mensajesError += "\nMETODO: ejecutarModificacionStatement\nERROR: ";
                mensajesError += error;
            }
            return false;
        }
    }
    ///*******************************************
    public boolean insertarFilaEnTabla(String tablaMysql, String [] nombreColumnasTablaMysql, String [] datosAInsertar, String mensajeDuplicado) {
        String instruccionInsert = "INSERT INTO " + tablaMysql + "(";
        for (int i = 0; i < nombreColumnasTablaMysql.length; i++) {
            instruccionInsert += nombreColumnasTablaMysql[i];
            if (i < nombreColumnasTablaMysql.length - 1) {
                instruccionInsert += ", ";
            } else {
                instruccionInsert += ") VALUES ('";
            }
        }
        for (int j = 0; j < datosAInsertar.length; j++) {
            instruccionInsert += datosAInsertar[j];
            if (j < nombreColumnasTablaMysql.length - 1) {
                instruccionInsert += "', '";
            } else {
                instruccionInsert += "')";
            }
        }
        return ejecutarModificacionStatement(instruccionInsert,"insertarFilaEnTabla", mensajeDuplicado);
    }
    ///*******************************************//
    public boolean insertarTablaEnTabla(String tablaMysql, String [] nombreColumnasTablaMysql, String [] [] datosAInsertar, String mensajeDuplicado) {
        String instruccionInsert = "INSERT INTO " + tablaMysql + "(";
        for (int i = 0; i < nombreColumnasTablaMysql.length; i++) {
            instruccionInsert += nombreColumnasTablaMysql[i];
            if (i < nombreColumnasTablaMysql.length - 1) {
                instruccionInsert += ", ";
            } else {
                instruccionInsert += ") VALUES";
            }
        }
        for (int i = 0; i < datosAInsertar.length; i++) {
            for (int j = 0; j < datosAInsertar[0].length; j++) {
                if (j == 0) {
                    instruccionInsert += " ('";
                }
                instruccionInsert += datosAInsertar[i][j];
                if (j < nombreColumnasTablaMysql.length - 1) {
                    instruccionInsert += "', '";
                } else {
                    instruccionInsert += "')";
                }
            }
            if (i < datosAInsertar.length - 1) {
                instruccionInsert += ",";
            } 
        }
        return ejecutarModificacionStatement(instruccionInsert,"insertarTablaEnTabla", mensajeDuplicado);
    }
    ///*******************************************
    public boolean eliminarFilaEnTabla(String tablaMysql, String columnaDondeBuscar, String campoBuscado) {
        String instruccionDelete = "DELETE FROM " + tablaMysql + " WHERE " + columnaDondeBuscar + " = '" + campoBuscado + "'";
        return ejecutarModificacionStatement(instruccionDelete,"eliminarFilaEnTabla","?");
    }
    ///*******************************************
    public boolean modificarFilaEnTabla(String tablaMysql, String [] nombreColumnasTablaMysql, String [] datosActualizados, String columnaDondeBuscar, String campoBuscado) {
        String instruccionUpdate = "UPDATE " + tablaMysql + " SET";
        for (int i = 0; i < nombreColumnasTablaMysql.length; i++) {
            instruccionUpdate += " " + nombreColumnasTablaMysql[i] + " = " + "'" + datosActualizados[i];
            if (i < nombreColumnasTablaMysql.length - 1) {
                instruccionUpdate += "',";
            } else {
                instruccionUpdate += "' ";
            }
        }
        instruccionUpdate += "WHERE " + columnaDondeBuscar + " = '" + campoBuscado + "'";    
        return ejecutarModificacionStatement(instruccionUpdate,"modificarFilaEnTabla","?");
    }
    ///*******************************************
    public boolean mostrarColumnasTablaMysqlSimple(JTable tabla, String tablaAConsultarMysql, String [] columnasAConsultarTablaMysql) {
        limpiarTabla(tabla);
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        String consulta = "SELECT ";
        for (int i = 0; i < columnasAConsultarTablaMysql.length; i++) {
            if (i < columnasAConsultarTablaMysql.length - 1) {
                consulta += columnasAConsultarTablaMysql[i] + ", ";
            } else {
                consulta += columnasAConsultarTablaMysql[i] + " ";
            }
        }
        consulta += "FROM " + tablaAConsultarMysql;
        String [] datosFila = new String [columnasAConsultarTablaMysql.length];
        try {
            Statement instruccionSt = con.createStatement();
            ResultSet conjuntoResultados = instruccionSt.executeQuery(consulta);
            while (conjuntoResultados.next()) {
                for (int j = 0; j < columnasAConsultarTablaMysql.length; j++) {
                    datosFila[j]= conjuntoResultados.getString(columnasAConsultarTablaMysql[j]);
                }
                modelo.addRow(datosFila); 
            }
            return true;
        } catch (Exception e) {
            mensajesError = "CLASE: ConexionMsql";
            mensajesError += "\nMETODO: mostrarColumnasTablaMysqlSimple\nERROR: ";
            mensajesError += e.getMessage();
            return false;
        }
    }
    ///*******************************************
    private void limpiarTabla(JTable tablaALimpiar) {
        DefaultTableModel modelo = (DefaultTableModel) tablaALimpiar.getModel();
        int filas = modelo.getRowCount();
        for (int k = 0; k < filas; k ++) {
            modelo.removeRow(0);
        }
    }
    ///*******************************************
    public boolean mostrarRegistroEspecifico(JTable tabla, String tablaAConsultarMysql, String [] columnasAConsultarTablaMysql, String columnaDondeBuscar, String campoBuscado){
        limpiarTabla (tabla);
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        String consulta = "SELECT ";
        for (int i = 0; i < columnasAConsultarTablaMysql.length; i++) {
            if (i < columnasAConsultarTablaMysql.length - 1) {
                consulta += columnasAConsultarTablaMysql[i] + ", ";
            } else {
                consulta += columnasAConsultarTablaMysql[i] + " ";
            }
        }
        consulta += "FROM " + tablaAConsultarMysql + " WHERE " + columnaDondeBuscar + "= '" + campoBuscado + "'";
        String [] datosFila = new String [columnasAConsultarTablaMysql.length];
        try {
            Statement instruccionSt = con.createStatement();
            ResultSet conjuntoResultados = instruccionSt.executeQuery(consulta);
            while (conjuntoResultados.next()) {
                for (int j = 0; j < columnasAConsultarTablaMysql.length; j++) {
                    datosFila[j]= conjuntoResultados.getString(columnasAConsultarTablaMysql[j]);
                }
                modelo.addRow(datosFila); 
            }
            return true;
        } catch (Exception e) {
            mensajesError = "CLASE: ConexionMsql";
            mensajesError += "\nMETODO: mostrarRegistroEspecifico\nERROR: ";
            mensajesError += e.getMessage();
            return false;
        }
    }
    ///*******************************************
    public boolean mostrarColumnasTablaMysqlCompuesta(JTable tabla, String consulta, String [] [] columnasTablas, int numeroColumnas, int ventana) {
        limpiarTablaCompletamente(tabla);
        Clases.ModeloTablas miModelo = new Clases.ModeloTablas();
        tabla.setModel(miModelo);
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < numeroColumnas; i++) {
            modelo.addColumn(columnasTablas[i][0]);
        }
        int columnaFechaPrincipal = -1;
        int columnaHorarioItinerario = -1;
        int columnaHorarioReal = -1;
        int columnaOtros = -1;
        int columnaNombrePasajero = -1;
        int columnaAPaternoPasajero = -1;
        int columnaAMaternoPasajero = -1;
        int columnaNacionalidadPasajero = -1;
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("fechaPrincipal")) {
               columnaFechaPrincipal = i; 
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("hi")) {
               columnaHorarioItinerario = i; 
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("hr")) {
               columnaHorarioReal = i; 
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("variosRenglones")) {
               columnaOtros = i; 
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("pasajeroN")) {
               columnaNombrePasajero = i; 
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("pasajeroAP")) {
               columnaAPaternoPasajero = i; 
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("pasajeroAM")) {
               columnaAMaternoPasajero = i; 
            }
        }
        for (int i = 0; i < numeroColumnas; i++) {
            if (columnasTablas[i][1].equals("pasajeroNa")) {
               columnaNacionalidadPasajero = i; 
            }
        }
        String [] datosFila = new String [numeroColumnas];
        try {
            Statement instruccionSt = con.createStatement();
            ResultSet conjuntoResultados = instruccionSt.executeQuery(consulta);
            String fechaGuia = "";
            
            String [] renglonesObsOtros = null;
            int totalRenglonesObstros = 0;
            int filaDondeIniciaRegistro = 0;
            int registrosFechas = 0;
            int totalPasajeros = 0;
            while (conjuntoResultados.next()) {
                String fechaTemporal = ""; //***************
                String cambioFecha = "";
                if (columnaFechaPrincipal != -1) {
                    if (columnaHorarioItinerario != -1) {
                        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
                        fechaTemporal = fecha.format(conjuntoResultados.getTimestamp(columnaFechaPrincipal));//***************
                    } else {
                        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                        fechaTemporal = fecha.format(conjuntoResultados.getTimestamp(columnaFechaPrincipal));//***************
                    }
                    SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                    cambioFecha = fecha.format(conjuntoResultados.getTimestamp(columnaFechaPrincipal));
                }
                
                if (!cambioFecha.equals(fechaGuia)) { //***************
                    if (registrosFechas > 0 && columnaOtros != -1) {
                        if (totalRenglonesObstros > totalPasajeros) {
                            String [] filasEnBlanco = new String [numeroColumnas];
                            for (int j = 0; j < (totalRenglonesObstros - totalPasajeros); j++) {
                                modelo.addRow(filasEnBlanco);
                            }
                        }
                        for (int k = 0; k < totalRenglonesObstros; k++) {
                            modelo.setValueAt(renglonesObsOtros[k],filaDondeIniciaRegistro++,columnaOtros);
                        }
                    }
                    for (int j = 1; j < numeroColumnas; j++) {
                        if (j == columnaFechaPrincipal) {
                            datosFila[j] = fechaTemporal;   //***************
                            fechaGuia = cambioFecha;//***************
                        } else if (j == columnaOtros) {
                            String obstros = conjuntoResultados.getString(columnaOtros);
                            if (obstros.contains("\n")) {
                                renglonesObsOtros = obstros.split("\n");
                                totalRenglonesObstros = renglonesObsOtros.length;
                                datosFila[j] = null;
                            } else {
                                renglonesObsOtros = new String [1];
                                renglonesObsOtros[0] = obstros;
                                totalRenglonesObstros = 1; 
                            }
                        } else if (j == columnaHorarioItinerario ) {
                            SimpleDateFormat fecha = new SimpleDateFormat("HH:mm");
                            datosFila[j] = fecha.format(conjuntoResultados.getTimestamp(columnaFechaPrincipal));
                        } else if (j == columnaHorarioReal ) {
                            SimpleDateFormat fecha = new SimpleDateFormat("HH:mm");
                            datosFila[j] = fecha.format(conjuntoResultados.getTimestamp(j-1));
                        } else {
                            if (columnaHorarioItinerario != -1) {
                                datosFila[j] = conjuntoResultados.getString(j-1);
                            } else {
                                datosFila[j] = conjuntoResultados.getString(j);
                            }
                        }
                    }
                    modelo.addRow(datosFila);
                    filaDondeIniciaRegistro = modelo.getRowCount() - 1;
                    registrosFechas++;
                    totalPasajeros = 1;
                } else {
                    for (int j = 1; j < numeroColumnas; j++) {
                        if (j == columnaNombrePasajero) {
                            datosFila[j] = conjuntoResultados.getString(j);
                        } else if (j == columnaAPaternoPasajero) {
                            datosFila[j] = conjuntoResultados.getString(j);
                        } else if (j == columnaAMaternoPasajero) {
                            datosFila[j] = conjuntoResultados.getString(j);
                        } else if (j == columnaNacionalidadPasajero) {
                            datosFila[j] = conjuntoResultados.getString(j);
                        } else {
                            datosFila[j] = null;
                        }
                    }
                    modelo.addRow(datosFila);
                    totalPasajeros++;
                }
            }
            if (totalRenglonesObstros > totalPasajeros) {
                String [] filasEnBlanco = new String [numeroColumnas];
                for (int j = 0; j < (totalRenglonesObstros - totalPasajeros); j++) {
                    modelo.addRow(filasEnBlanco);
                }
            }
            for (int k = 0; k < totalRenglonesObstros; k++) {
                modelo.setValueAt(renglonesObsOtros[k],filaDondeIniciaRegistro++,columnaOtros);
            }
            int grupo = 1;
            modelo.setValueAt(1,0,0);
            for (int i = 1; i < modelo.getRowCount(); i++) {
                String fecha = (String) modelo.getValueAt(i,columnaFechaPrincipal);
                if (!(fecha == null)) {
                    grupo++;
                }
                modelo.setValueAt(grupo,i,0);
            }
            /*tabla.getColumnModel().getColumn(0).setMinWidth(Integer.parseInt(columnasTablas[0][2]));
            tabla.getColumnModel().getColumn(0).setPreferredWidth(Integer.parseInt(columnasTablas[0][2]));
            tabla.getColumnModel().getColumn(0).setMaxWidth(Integer.parseInt(columnasTablas[0][2]));
            for (int i = 1; i < numeroColumnas; i++) {
                tabla.getColumnModel().getColumn(i).setMinWidth(Integer.parseInt(columnasTablas[i][2]));
                tabla.getColumnModel().getColumn(i).setPreferredWidth(Integer.parseInt(columnasTablas[i][2]));
                tabla.getColumnModel().getColumn(i).setMaxWidth(Integer.parseInt(columnasTablas[i][2]));
            }*/
            packColumns(tabla,1,ventana);
            FormatoFilasTabla ft = new FormatoFilasTabla();
            tabla.setDefaultRenderer (Object.class,ft);
            return true;
        } catch (Exception e) {
            mensajesError = "CLASE: ConexionMsql";
            mensajesError += "\nMETODO: mostrarColumnasTablaMysqlCompuesta\nERROR: ";
            mensajesError += e.getMessage();
            return false;
        }
    }  
    ///*******************************************
    private void limpiarTablaCompletamente(JTable tablaALimpiar) {
        DefaultTableModel modelo = (DefaultTableModel) tablaALimpiar.getModel();
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
    }
    
    
    
    
    
    
    
    
    
    ///*********************BETA
     public boolean getAlertaMensajesError() {
        return alertaMensajesError;
    }
    ///*******************************************
    public void setAlertaMensajesError(boolean alertaMensajesError) {
        this.alertaMensajesError = alertaMensajesError;
    }
    ///*******************************************
    public String [] obtenerInformacionRegistroEspecifico(String tablaAConsultar, String [] columnasAConsultar,String campoLlave, String campoConsulta){
        String consulta = "SELECT ";
        for (int i = 0; i < columnasAConsultar.length; i++) {
            if (i < columnasAConsultar.length - 1) {
                consulta += columnasAConsultar [i] + ", ";
            } else {
                consulta += columnasAConsultar [i] + " ";
            }
        }
        consulta += "FROM " + tablaAConsultar + " WHERE " + campoLlave + "= '" + campoConsulta + "'";
        
        String [] datosFila = new String [columnasAConsultar.length];
        
        try {
            Statement instruccionSt = con.createStatement();
            ResultSet conjuntoResultados = instruccionSt.executeQuery(consulta);
            
            while (conjuntoResultados.next()) {
                for (int j = 0; j < columnasAConsultar.length; j++) {
                    datosFila [j]= conjuntoResultados.getString(columnasAConsultar [j]);
                }     
            }
        return datosFila;
        
        } catch (Exception e) {
            mensajesError = "CLASE: ConexionMsql";
            mensajesError += "\nMETODO: obtenerInformacionRegistroEspecifico\nERROR: ";
            mensajesError += e.getMessage();
            alertaMensajesError = true;
            return null;
        }
    }
    
    
    //////////////////////////////////
    
    private void packColumns(JTable table, int margin, int ventana) {
        for (int c=0; c<table.getColumnCount(); c++) {
            String nombreColumna = table.getColumnName(c);
            if (ventana == 1 && (nombreColumna.equals("") || nombreColumna.equals("PDF - RUTA"))) {
                packColumn(table,c,margin,true);
            } else if (ventana == 2 && nombreColumna.equals("")) {
                packColumn(table,c,margin,true);
            } else {
                packColumn(table,c,margin,false);
            }
        }
    }
    
    private void packColumn(JTable table, int vColIndex, int margin, boolean noVisible) {
        if (noVisible) {
            table.getColumnModel().getColumn(vColIndex).setMinWidth(0);
            table.getColumnModel().getColumn(vColIndex).setPreferredWidth(0);
            table.getColumnModel().getColumn(vColIndex).setMaxWidth(0);
        } else {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
            TableColumn col = colModel.getColumn(vColIndex);
            int width = 0;
            TableCellRenderer renderer = col.getHeaderRenderer();
            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
            width = comp.getPreferredSize().width;
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, vColIndex);
                comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false, r, vColIndex);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            width += 2*margin;
            col.setPreferredWidth(width);
            col.setMaxWidth(width);
            col.setMinWidth(width);
        }
    }

 
    //////////////////
        
    
}
