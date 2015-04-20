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
        if (longitud >= 7 && longitud < 9) {
            jLabelTipoLicencia.setText("EXTRANJERA");
        } else if (longitud == 9) {
            jLabelTipoLicencia.setText("NACIONAL");
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
    
    public static void modificarFila (JTable tabla, int numeroFila, int posicionInicial, String [] informacionNueva) {
        int posicion = 0;
        for (int i = posicionInicial; i < (posicionInicial + informacionNueva.length); i++) {
            tabla.setValueAt(informacionNueva[posicion],numeroFila, i);
            posicion++;
        }
    }
    
    public static String integerFormat (int numeroDosDigitos) {
        DecimalFormat df = new DecimalFormat("00");
        String numeroConFormato = df.format(numeroDosDigitos);
        return numeroConFormato;
    }
    
    public static void ponerEnCeros(JTextField campoTexto, JLabel etiquetaCampo, String leyendaEtiqueta) {
        if (campoTexto.getText() == null || "".equals(campoTexto.getText())) {
            campoTexto.setText("0");
            etiquetaCampo.setForeground(new java.awt.Color(0,0,0));
            etiquetaCampo.setText(leyendaEtiqueta);
        }
    }
    
    public static int sumarSiEstaVacioColor(JTextField campoTexto, JLabel etiquetaCampo, String leyendaEtiqueta, int cantidad, int r, int g, int b) {
        if (campoTexto.getText() == null || "".equals(campoTexto.getText())) {
            etiquetaCampo.setForeground(new java.awt.Color(r, g, b));
            etiquetaCampo.setText(leyendaEtiqueta);
            return cantidad;
        }
        return 0;
    }
    
    public static int sumarSiEstaVacio(JTextField campoTexto, int cantidad) {
        if (campoTexto.getText() == null || "".equals(campoTexto.getText())) {
            return cantidad;
        }
        return 0;
    }
    //!Character.isDigit(caracterValidar) && !Character.isAlphabetic(caracterValidar) && !Character.isISOControl(caracterValidar)) || cajaTextoAValidar.getText().length() == maxCaracteres
    //JTextField cajaTextoAValidar, int maxCaracteres, char caracterValidar
}
