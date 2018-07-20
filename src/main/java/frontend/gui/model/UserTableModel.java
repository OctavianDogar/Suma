package frontend.gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import  backend.model.User;
import  frontend.gui.TablePanel;
import  frontend.utils.LabelProvider;

/**
 * Created by dogaro on 13/07/2016.
 */

/**
 * @author Dogar Octavian
 * {@link TablePanel}'s table model
 */
public class UserTableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> users;
    private String [] colNames = {"ID",
            "UUID",
            LabelProvider.getLabel("UserTableModel.firstName"),
            LabelProvider.getLabel("UserTableModel.lastName"),
            LabelProvider.getLabel("UserTableModel.username"),
            LabelProvider.getLabel("UserTableModel.usertype")
    };

    public UserTableModel() {
    }


    public void setData(List<User> data){
        users= data;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user= users.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getUuid();
            case 2:
                return user.getFirstName();
            case 3:
                return user.getLastName();
            case 4:
                return user.getUsername();
            case 5:
                return user.getUserType();


        }
        return null;
    }

}
