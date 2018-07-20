package frontend.gui;

import backend.model.User;
import frontend.gui.model.UserTableModel;
import frontend.utils.LabelProvider;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by dogaro on 13/07/2016.
 */

/**
 * @author Dogar Octavian
 * Panel class that holds the entries data table
 */
public class TablePanel extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private UserTableModel tableModel;
    @SuppressWarnings("unused")
	private FormPanel formPanel;

    public void setLabels(){
        table.getColumnModel().getColumn(2).setHeaderValue(LabelProvider.getLabel("UserTableModel.firstName"));
        table.getColumnModel().getColumn(3).setHeaderValue(LabelProvider.getLabel("UserTableModel.lastName"));
        table.getColumnModel().getColumn(4).setHeaderValue(LabelProvider.getLabel("UserTableModel.username"));
        table.getColumnModel().getColumn(5).setHeaderValue(LabelProvider.getLabel("UserTableModel.usertype"));

    }

    public TablePanel(FormPanel formPanel){
        this.formPanel = formPanel;
        tableModel = new UserTableModel();
        table = new JTable(tableModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(table),BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() > -1) {
                    // print first column value from selected row
                    //uuid firstName lastName username userType
                    String id = table.getValueAt(table.getSelectedRow(), 0).toString();
                    String uuid =  table.getValueAt(table.getSelectedRow(), 1).toString();
                    String firstname =  table.getValueAt(table.getSelectedRow(), 2).toString();
                    String lastname =  table.getValueAt(table.getSelectedRow(), 3).toString();
                    String username =  table.getValueAt(table.getSelectedRow(), 4).toString();
                    String usertype =  table.getValueAt(table.getSelectedRow(), 5).toString();

                    formPanel.fillFields(id,uuid,firstname,lastname,username,usertype);
                    
                }
            }
        });

        setVisible(false);
    }

    public void setData(List<User> users){
        tableModel.setData(users);
        tableModel.fireTableDataChanged();
    }

}
