package frontend.gui;

import frontend.gui.model.FormEvent;
import frontend.utils.LabelProvider;
import org.apache.commons.lang3.StringUtils;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


/**
 * Created by dogaro on 12/07/2016.
 */
/**
 * @author Dogar Octavian
 */
public class FormPanel extends JPanel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private JButton saveBtn;
    private JButton updateBtn;
    private JButton deleteBtn;

    private JLabel IDLbl;
    private JLabel UUIDLbl;
    private JLabel usernameLbl;
    private JLabel firstNameLbl;
    private JLabel lastNameLbl;
    private JLabel passwordLbl;
    private JLabel userTypeLbl;

    private JTextField IDField;
    private JTextField UUIDField;
    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    @SuppressWarnings("rawtypes")
	private JComboBox userTypeCombo;

    private FormListener formListener;

    private String innerBorderS;
    private Border innerB;

    @SuppressWarnings("unchecked")
	public void setLabels(){
        innerBorderS = LabelProvider.getLabel("FormPanel.innerBorder");
        innerB = BorderFactory.createTitledBorder(innerBorderS);
        Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder,innerB));

        IDLbl.setText(LabelProvider.getLabel("FormPanel.IdLabel"));
        usernameLbl.setText(LabelProvider.getLabel("FormPanel.usernameLbl"));
        firstNameLbl.setText(LabelProvider.getLabel("FormPanel.firstNameLbl"));
        lastNameLbl.setText(LabelProvider.getLabel("FormPanel.lastNameLbl"));
        passwordLbl.setText(LabelProvider.getLabel("FormPanel.passwordLbl"));
        userTypeLbl.setText(LabelProvider.getLabel("FormPanel.usertypeLbl"));

        saveBtn.setText(LabelProvider.getLabel("FormPanel.saveButton"));
        updateBtn.setText(LabelProvider.getLabel("FormPanel.updateButton"));
        deleteBtn.setText(LabelProvider.getLabel("FormPanel.deleteButton"));

        @SuppressWarnings("rawtypes")
		DefaultComboBoxModel userTypeComboModel = new DefaultComboBoxModel();
        userTypeComboModel.addElement(LabelProvider.getLabel("FormPanel.usertypeAdmin"));
        userTypeComboModel.addElement(LabelProvider.getLabel("FormPanel.usertypeUser"));
        userTypeComboModel.addElement(LabelProvider.getLabel("FormPanel.usertypeGuest"));
        userTypeCombo.setModel(userTypeComboModel);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public FormPanel(){
        innerBorderS = LabelProvider.getLabel("LoginPanel.innerBorder");
        innerB = BorderFactory.createTitledBorder(innerBorderS);
        Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder,innerB));

        saveBtn = new JButton(LabelProvider.getLabel("FormPanel.saveButton"));
        updateBtn = new JButton(LabelProvider.getLabel("FormPanel.updateButton"));
        deleteBtn = new JButton(LabelProvider.getLabel("FormPanel.deleteButton"));

        IDLbl = new JLabel(LabelProvider.getLabel("FormPanel.IdLabel"));
        UUIDLbl = new JLabel("UUID: ");
        usernameLbl = new JLabel(LabelProvider.getLabel("FormPanel.usernameLbl"));
        firstNameLbl = new JLabel(LabelProvider.getLabel("FormPanel.firstNameLbl"));
        lastNameLbl = new JLabel(LabelProvider.getLabel("FormPanel.lastNameLbl"));
        passwordLbl = new JLabel(LabelProvider.getLabel("FormPanel.passwordLbl"));
        userTypeLbl = new JLabel(LabelProvider.getLabel("FormPanel.usertypeLbl"));

        IDField = new JTextField(5);
        UUIDField = new JTextField(10);
        usernameField = new JTextField(10);
        firstNameField = new JTextField(10);
        UUIDField = new JTextField(10);
        lastNameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        userTypeCombo = new JComboBox();
        DefaultComboBoxModel userTypeComboModel = new DefaultComboBoxModel();
        userTypeComboModel.addElement(LabelProvider.getLabel("FormPanel.usertypeAdmin"));
        userTypeComboModel.addElement(LabelProvider.getLabel("FormPanel.usertypeUser"));
        userTypeComboModel.addElement(LabelProvider.getLabel("FormPanel.usertypeGuest"));
        userTypeCombo.setModel(userTypeComboModel);
        userTypeCombo.setSelectedIndex(0);

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String)userTypeCombo.getSelectedItem();
                
                boolean isBlank= StringUtils.isBlank(password);
                
                if(isBlank) {
                    JOptionPane.showConfirmDialog(FormPanel.this,
                            LabelProvider.getLabel("FormPanel.blankPassword"), "Warning", JOptionPane.DEFAULT_OPTION);
                }else{

                    FormEvent formEvent = new FormEvent(this,null,firstName,lastName,password,userType,username);

                    if(formListener !=null) {
                        formListener.formEventOccured(formEvent,"save");
                    }
                }


            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDField.getText();
                String username = usernameField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String)userTypeCombo.getSelectedItem();



                FormEvent formEvent = new FormEvent(this,ID,firstName,lastName,password,userType,username);

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(FormPanel.this, 
                		"Are you sure you want to update the selected user?", "Confirm", dialogButton);
                if(dialogResult == 0) {
                    if(formListener !=null) {
                        formListener.formEventOccured(formEvent,"update");
                    }
                }
            }
        });
                
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = IDField.getText();
                String username = usernameField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String)userTypeCombo.getSelectedItem();

                FormEvent formEvent = new FormEvent(this,ID,firstName,lastName,password,userType,username);
                
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(FormPanel.this, 
                		"Are you sure you want to delete user \""+username+"\" ?", "Confirm", dialogButton);
                if(dialogResult == 0) {
                    if(formListener !=null) {
                        formListener.formEventOccured(formEvent,"delete");
                    }
                }
                
            }
        });

        layoutComponents();
        setVisible(false);

    }

    public boolean validate(String firstname, String lastname, String username){

        Matcher m;

        Pattern pfn = Pattern.compile("^[A-z]+$");
        m = pfn.matcher(firstname);
        boolean a = m.matches();
        if(!a){
            JOptionPane.showConfirmDialog(FormPanel.this,
                    LabelProvider.getLabel("FormPanel.invalidfirstname"), "Warning", JOptionPane.DEFAULT_OPTION);
            return false;
        }

        Pattern pln = Pattern.compile("^[A-z]+$");
        m = pln.matcher(lastname);
        boolean b = m.matches();
        if(!b){
            JOptionPane.showConfirmDialog(FormPanel.this,
                    LabelProvider.getLabel("FormPanel.invalidLastname"), "Warning", JOptionPane.DEFAULT_OPTION);
            return false;
        }

        Pattern pun = Pattern.compile("^[0-9A-z]+$");
        m = pun.matcher(username);
        boolean c = m.matches();
        if(!c){
            JOptionPane.showConfirmDialog(FormPanel.this,
                    LabelProvider.getLabel("FormPanel.invalidUsername"), "Warning", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        return true;

    }

    public void fillFields(String id,String uuid,String firstname, String lastname, String username, String usertype){
        IDField.setText(id);
        UUIDField.setText(uuid);
        firstNameField.setText(firstname);
        lastNameField.setText(lastname);
        usernameField.setText(username);
        userTypeCombo.setSelectedItem(usertype);

        passwordField.setText("");
    }

    public static Container parentOf(Component component){
        return SwingUtilities.getAncestorOfClass(JPanel.class,component);
    }


    public void layoutComponents(){

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //firstrow
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(UUIDLbl,gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(UUIDField,gc);
        UUIDField.setEnabled(false);

        //secondRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(usernameLbl,gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(usernameField,gc);

        //thirdRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(firstNameLbl,gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(firstNameField,gc);

        //nextRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(lastNameLbl,gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lastNameField,gc);

        //nextRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(passwordLbl,gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(passwordField,gc);

        //nextRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(userTypeLbl,gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(userTypeCombo,gc);

        //idrows
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,5);
        add(IDLbl,gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(IDField,gc);
        IDField.setEnabled(false);

        //nextRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(saveBtn,gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.NORTH;
        add(updateBtn,gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(deleteBtn,gc);

    }

    public void setFormListener(FormListener formListener){
        this.formListener = formListener;
    }


}
