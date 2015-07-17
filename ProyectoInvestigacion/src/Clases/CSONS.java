/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author ADOLFO
 */
public class CSONS {
    private static String ordenar(String cadena, int clave) {
        String ret = "";
        char[] cc = cadena.toCharArray();
        for (int i = 0; i < cc.length; i++) {
            char c = (char) (cc[i] ^ clave);
            ret += c;
        }
        return ret;
    }
    
    public static boolean crearOrden(String palabra, int clave, String archivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            String ordenado = ordenar(palabra,clave);
            bw.write(ordenado);
            bw.flush();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
}
