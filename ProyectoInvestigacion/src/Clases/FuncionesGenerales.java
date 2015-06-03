/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
    
    public static void eliminarColumnaTemporal (JTable tablaBusqueda, int columnaAEliminar){   
        tablaBusqueda.getColumnModel().getColumn(columnaAEliminar).setMinWidth(0);
        tablaBusqueda.getColumnModel().getColumn(columnaAEliminar).setPreferredWidth(0);
        tablaBusqueda.getColumnModel().getColumn(columnaAEliminar).setMaxWidth(0);   
    }
    
    public static void mostrarColumnaEliminada (JTable tablaBusqueda, int columnaAMostrar, int anchoColumna){   
        for (int i = columnaAMostrar; i <= tablaBusqueda.getColumnCount(); i++) {
            tablaBusqueda.getColumnModel().getColumn(columnaAMostrar).setMinWidth(anchoColumna);
            tablaBusqueda.getColumnModel().getColumn(columnaAMostrar).setPreferredWidth(anchoColumna);
            tablaBusqueda.getColumnModel().getColumn(columnaAMostrar).setMaxWidth(anchoColumna);   
        }  
    }
    
    public static void limpiarTablaCompletamente(JTable tablaALimpiar) {
        DefaultTableModel modelo = (DefaultTableModel) tablaALimpiar.getModel();
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
    }
    
    public static int [] celdasTabla(JTable tabla, int filaActual) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int filas = modelo.getRowCount();
        Object [] InicioFin = new Object [filas];
        int [] [] separador = new int [filas] [2];
        for (int i = 0; filas > i; i++) {
            InicioFin [i] = modelo.getValueAt(i, 1);
            System.out.println("---" + InicioFin [i]);
        }
        int inicio = 0;
        int fin = 0;
        for (int j = 0; filas > j; j++) {
            if (InicioFin [j] != null ) {
                fin = inicio;
                separador [inicio++] [0]  = j;
                separador [fin] [1] = j;
            } else {
                separador [fin] [1]  = j;
            }
        }
        int limites [] = new int [2];
        for (int k = 0; inicio > k; k++) {
            if (separador [k] [1] >= filaActual && filaActual >= separador [k] [0]) {
                limites [1] = separador [k] [1];
                limites [0] = separador [k] [0];
            }
        }
        return limites;
    }
    
    public static boolean celdaPDFSeleccionada(JTable tabla, String nombreColumnaPDF) {
        String columnaSeleccionada = tabla.getColumnName(tabla.getSelectedColumn());
        return columnaSeleccionada.equals(nombreColumnaPDF);
    }
    
    public static String abrirPDFSeleccionada(JTable tabla, String nombreColumnaRuta, int filaPDF, int columnaNPDF) {
        String nombrePDF = (String) tabla.getValueAt(filaPDF,columnaNPDF);
        System.out.println("--" + nombrePDF);
        if (nombrePDF != null) {
            int totalColumnas = tabla.getColumnCount();
            int columnaRuta = 1;
            for (int k = 1; k < totalColumnas; k++) {
                String columnaSeleccionada = tabla.getColumnName(k);
                if (columnaSeleccionada.equals(nombreColumnaRuta)) {
                    columnaRuta = k;
                }
            }
            String ruta = (String) tabla.getValueAt(filaPDF,columnaRuta); 
            try {
                File path = new File (ruta);
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                ex.printStackTrace();
                return "ERROR AL ABRIR EL DOCUMENTO";
            }
            return "OK";
        }
        return "NO HA DECLARADO UN ARCHIVO PDF";    
    }
    //!Character.isDigit(caracterValidar) && !Character.isAlphabetic(caracterValidar) && !Character.isISOControl(caracterValidar)) || cajaTextoAValidar.getText().length() == maxCaracteres
    //JTextField cajaTextoAValidar, int maxCaracteres, char caracterValidar
}
