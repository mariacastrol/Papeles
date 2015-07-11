/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author ADOLFO
 */
public class CMysql {
    private String strCadenaConexion = "";
    
    private String strBaseDatos; 
    private String strServidor;
    private String strUsuario;
    private String strPassword;
    private String mensajesError;
    private final String strDriverMySQL = "com.mysql.jdbc.Driver";
    private boolean alertaMensajesError;
    
    private Connection con = null; 
    
    public CMysql() {
        this.strBaseDatos = "";
        this.strServidor = "";
        this.strUsuario = "";
        this.strPassword = "";
        this.mensajesError = "";
        this.alertaMensajesError = false;
    }
    
    private boolean conectarSoloServidor(String servidor,String usuario,String pw,String driver,String metodoQueInvoca) {
        try {
            Class.forName(driver);
            strCadenaConexion = "jdbc:mysql://"+servidor+"/";
            con = DriverManager.getConnection(strCadenaConexion,usuario,pw);
            return con != null;
        } catch (Exception e) {
            mensajesError = "CLASE: ConexionMsql\nINVOCADO EN: " + metodoQueInvoca;
            mensajesError += "\nMETODO: conectarSoloServidor\nERROR: ";
            mensajesError += e.getMessage();
            return false;
        } 
    }
    
    public boolean conectarSV(String servidor, String usuario, String password) {
        strServidor = servidor;
        strUsuario = usuario;
        strPassword = password;
        return conectarSoloServidor(strServidor,strUsuario,strPassword,strDriverMySQL,"conectarSV");  
    }
    
    public String getMensajesError() {
        return mensajesError;
    }
    
    public boolean ejecutarModificacionStatement(String instruccion) {
        try {
            Statement instruccionSt = con.createStatement();
            instruccionSt.executeUpdate(instruccion);
            return true;
        } catch (Exception e) {
            String error = e.getMessage();
            mensajesError = "CLASE: ConexionMsql\n";
            mensajesError += "\nMETODO: ejecutarModificacionStatement\nERROR: ";
            mensajesError += error;
            return false;
        }
    }
}
