/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    public boolean mostrarColumnasTablaMysqlCompuesta(JTable tabla, String consulta, String [] columnasTablas, int numeroColumnas, int longColumna) {
        limpiarTablaCompletamente(tabla);
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < numeroColumnas; i++) {
            modelo.addColumn(columnasTablas[i]);
            tabla.getColumnModel().getColumn(i).setMinWidth(longColumna);
            //tabla.getColumnModel().getColumn(i).setPreferredWidth(200);
            //tabla.getColumnModel().getColumn(i).setMaxWidth(200);
        }
        String [] datosFila = new String [numeroColumnas];
        try {
            Statement instruccionSt = con.createStatement();
            ResultSet conjuntoResultados = instruccionSt.executeQuery(consulta);
            while (conjuntoResultados.next()) {
                for (int j = 0; j < numeroColumnas; j++) {
                    datosFila[j] = conjuntoResultados.getString(j + 1);
                }
                modelo.addRow(datosFila);
            }
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
        
    
}
