/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.JTextField;

/**
 *
 * @author ADOLFO
 */
public class FuncionesGenerales {
    
    public static boolean soloNumerosJTextField(JTextField cajaTextoAValidar, int maxCaracteres, char caracterValidar) {
        return (Character.isDigit(caracterValidar) || Character.isISOControl(caracterValidar)) && cajaTextoAValidar.getText().length() < maxCaracteres;
    }
    
    public static boolean soloLetrasJTextField(JTextField cajaTextoAValidar, int maxCaracteres, char caracterValidar) {
        return (Character.isAlphabetic(caracterValidar) || Character.isISOControl(caracterValidar)) && cajaTextoAValidar.getText().length() < maxCaracteres;
    }
    
    public static boolean valoresAlfanumericosJTextField(JTextField cajaTextoAValidar, int maxCaracteres, char caracterValidar) {
        return (Character.isLetterOrDigit(caracterValidar) || Character.isISOControl(caracterValidar)) && cajaTextoAValidar.getText().length() < maxCaracteres;
    }
    
    public static boolean estaVacioJTextField(JTextField cajaTextoAValidar) {
        return (cajaTextoAValidar.getText() == null || cajaTextoAValidar.getText().equals(""));
    }
    
    
    //!Character.isDigit(caracterValidar) && !Character.isAlphabetic(caracterValidar) && !Character.isISOControl(caracterValidar)) || cajaTextoAValidar.getText().length() == maxCaracteres
    //JTextField cajaTextoAValidar, int maxCaracteres, char caracterValidar
}
