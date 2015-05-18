/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADOLFO
 */
public class FormatoFilasTabla extends DefaultTableCellRenderer {
    private Component comp;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if((int) table.getValueAt(row,0) % 2 == 0) {                   //Checamos el número de la fila para aplicar un color
            comp.setBackground(new java.awt.Color(204,204,255));
        }else{                              //En caso de que la condición resulte diferente aplicara otro color a la fila
            comp.setBackground(Color.white);
        }
        return comp;
    }
    
}
