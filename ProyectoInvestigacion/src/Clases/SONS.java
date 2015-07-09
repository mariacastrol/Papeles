/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ADOLFO
 */
public class SONS {
    private static String c;

    public static String getC() {
        return c;
    }

    public static void setC(String cp) {
        c = cp;
    }
    
    private static String ordenar(String cadena, int clave) {
        String ret = "";
        char[] cc = cadena.toCharArray();
        for (int i = 0; i < cc.length; i++) {
            char c = (char) (cc[i] ^ clave);
            ret += c;
        }
        return ret;
    }
    
    public static boolean crearCaos(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            c = ordenar(br.readLine(),9);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
}
