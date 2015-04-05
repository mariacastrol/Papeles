/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
    
    public static void nacionalExtranjera(JTextField cajaTextoLicencia, JLabel jLabelTipoLicencia) {
        int longitud = cajaTextoLicencia.getText().length();
        if (longitud == 9) {
            jLabelTipoLicencia.setText("NACIONAL");
        } else {
            jLabelTipoLicencia.setText("EXTRANJERA");
        }
    }
    
    public static void spinnerNumericoCiclico(int valorMinimo, int valorMaximo, int valorActual, JSpinner spinnerNumerico) {
        if (valorActual == (valorMinimo - 1) || valorActual > (valorMaximo + 1)) {
            spinnerNumerico.setValue(valorMaximo);
        } else if (valorActual == (valorMaximo + 1) || valorActual < (valorMinimo - 1)) {
            spinnerNumerico.setValue(valorMinimo);
        }
    }
    
    public static void agregarFila(JTable tabla, String[] datosFila) {
        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
        modelo.addRow(datosFila);
    }
    
    public static void eliminarFila(JTable tabla, int numeroFila) {
        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
        modelo.removeRow(numeroFila);
    }
    
    public static void modificarFila (JTable tabla, int numeroFila, String [] informacionNueva) {
        for (int i = 0; i < informacionNueva.length; i++) {
            tabla.setValueAt(informacionNueva[i],numeroFila, i);
        }
    }
    
    public static String integerFormat (int numeroDosDigitos) {
        DecimalFormat df = new DecimalFormat("00");
        String numeroConFormato = df.format(numeroDosDigitos);
        return numeroConFormato;
    }
    //!Character.isDigit(caracterValidar) && !Character.isAlphabetic(caracterValidar) && !Character.isISOControl(caracterValidar)) || cajaTextoAValidar.getText().length() == maxCaracteres
    //JTextField cajaTextoAValidar, int maxCaracteres, char caracterValidar
}
