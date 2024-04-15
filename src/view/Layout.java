package view;

import core.Helper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Layout extends JFrame{
    public Layout() {
    }

    public void guiInitilaze(int width, int height) {
        this.setDefaultCloseOperation(2);
        this.setTitle("Rent a Car");
        this.setSize(width, height);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);
        DefaultTableModel clearModel = (DefaultTableModel)table.getModel();
        clearModel.setRowCount(0);
        if (rows == null) {
            rows = new ArrayList();
        }

        Iterator var6 = rows.iterator();

        while(var6.hasNext()) {
            Object[] row = (Object[])var6.next();
            model.addRow(row);
        }

    }

    public int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
    }

    public void tableRowSelect(final JTable table) {
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
}
