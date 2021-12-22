/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Untils;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Duy
 */
public class Tool {
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
		double... percentages) {
	double total = 0;
	for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		total += percentages[i];
	}

	for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		TableColumn column = table.getColumnModel().getColumn(i);
		column.setPreferredWidth((int)
				(tablePreferredWidth * (percentages[i] / total)));
	}
    }
    public static void TableFilter(JTable tbl, String query){
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        tbl.setRowSorter(tr); 
        tr.setRowFilter(RowFilter.regexFilter(query));
    }   
}
