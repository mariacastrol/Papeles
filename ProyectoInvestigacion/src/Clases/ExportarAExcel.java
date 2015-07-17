package Clases;

import java.io.File;

import javax.swing.*;
import javax.swing.table.*;

import jxl.*;
import jxl.write.*;

public class ExportarAExcel {
    public boolean fillData(JTable table, File file) {
        try {
            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            TableModel model = table.getModel();
            DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
            int cuentaColumnas = 0;
            for (int i = 0; i < model.getColumnCount(); i++) {
                TableColumn col = colModel.getColumn(i);
                if (col.getPreferredWidth() != 0) {
                    Label column = new Label(cuentaColumnas, 0, model.getColumnName(i));
                    sheet1.addCell(column);
                    cuentaColumnas++;
                }
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                cuentaColumnas = 0;
                for (int j = 0; j < model.getColumnCount(); j++) {
                    TableColumn col = colModel.getColumn(j);
                    if (col.getPreferredWidth() != 0) {
                        String valor = (String) model.getValueAt(i, j);
                        if (valor != null) {
                            Label row = new Label(cuentaColumnas++, i + 1, valor);
                            sheet1.addCell(row);
                        } else {
                            Label row = new Label(cuentaColumnas++, i + 1, "");
                            sheet1.addCell(row);
                        }
                    }
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error creando documento de Excel:\n" + ex.getMessage());
            return false;
        }
        return true;
    }
    
}