/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

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
        } else {
            jLabelTipoLicencia.setText("");
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
    
    public static void mostrarColumnaEliminada (JTable tablaBusqueda, int columnaAMostrar){   
        String nombreColumna = tablaBusqueda.getColumnName(columnaAMostrar);
        if (nombreColumna.equals("") || nombreColumna.equals("PDF - RUTA")) {
            ajustar(tablaBusqueda,columnaAMostrar,2,true);
        } else {
            ajustar(tablaBusqueda,columnaAMostrar,2,false);
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
            } catch (Exception ex) {
                return "ERROR AL ABRIR EL DOCUMENTO";
            }
            return "OK";
        }
        return "NO HA DECLARADO UN ARCHIVO PDF";    
    }
    
    private static void ajustar(JTable table, int vColIndex, int margin, boolean noVisible) {
        if (noVisible) {
            table.getColumnModel().getColumn(vColIndex).setMinWidth(0);
            table.getColumnModel().getColumn(vColIndex).setPreferredWidth(0);
            table.getColumnModel().getColumn(vColIndex).setMaxWidth(0);
        } else {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
            TableColumn col = colModel.getColumn(vColIndex);
            int width;
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
    
    public static boolean equal(final Object[][] array1, final Object[][] array2) {
        if (array1 == null) {
            return (array2 == null);
        }
        if (array2 == null) {
            return false;
        }
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (!Arrays.equals(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

}
