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
        if((int) table.getValueAt(row,0) % 2 == 0) {
            comp.setBackground(new java.awt.Color(204,204,255));
        }else{
            comp.setBackground(new java.awt.Color(255,255,255));
        }
        if(isSelected){
            comp.setBackground(new java.awt.Color(78,190,231));
            comp.setForeground(Color.BLACK);
        }
        return comp;
    }
    
}
