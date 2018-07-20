package frontend.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import backend.repository.RepositoryException;
import frontend.controller.FormController;
import frontend.controller.LoginController;
import frontend.gui.FormPanel;
import frontend.gui.LoginListener;
import frontend.gui.LoginPanel;
import frontend.gui.TablePanel;
import frontend.gui.model.FormEvent;
import frontend.gui.model.LoginEvent;
import frontend.utils.LabelProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by dogaro on 12/07/2016.
 */
/**
 * @author Dogar Octavian
 * Frame class that contains all other panels
 */
public class MainFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = LoggerFactory.getLogger(MainFrame.class);


	private static TablePanel tablePanel;

    private static FormPanel formPanel;

    private static LoginPanel loginPanel;

    private static JComboBox<Locale> languageComboBox;

    private LoginController loginController;
    private FormController formController;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public MainFrame() throws HeadlessException {
        super("SUMA");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());           
        } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException  e) {
            e.printStackTrace();
            LOG.error("Unable to set LookAndFeel",e);
        }


        setLayout(new BorderLayout());
        formPanel = new FormPanel();
        loginPanel = new LoginPanel();
        tablePanel = new TablePanel(formPanel);

        loginController = new LoginController();
        formController = new FormController();

        languageComboBox = new JComboBox<>();

        tablePanel.setData(formController.getUsers());

        add(formPanel, BorderLayout.WEST);

        loginPanel.setLoginListener(new LoginListener() {
            @Override
            public void loginEventOccurred(LoginEvent e) {
                String username = e.getUserName();
                String password = e.getPassword();

                boolean check=false;
                try {
                    check = loginController.checkLogin(username, password);
                    if(check) {
                        loginPanel.setVisible(false);
                        tablePanel.setVisible(true);
                        formPanel.setVisible(true);

                    }
                    //PASSWORD DOESN'T MATCH TO USERNAME
                    else {JOptionPane.showConfirmDialog(MainFrame.this,
                                LabelProvider.getLabel("LoginPanel.errDialog"), "Warning", JOptionPane.DEFAULT_OPTION);
                    }
                }catch (RepositoryException re){
                	LOG.error("No user found with the given username",re);
                   
                    JOptionPane.showConfirmDialog(MainFrame.this,
                            LabelProvider.getLabel("LoginPanel.errDialog"),"Warning",JOptionPane.DEFAULT_OPTION);

                }
            }
        });

        formPanel.setFormListener(new FormListener() {
            public void formEventOccured(FormEvent e, String s) {

                if(s.equals("save")){
                    if(formPanel.validate(e.getFirstName(),e.getLastName(),e.getUsername())){
                        formController.addUser(e);
                        tablePanel.setData(formController.getUsers());
                    }

                }else if(s.equals("update")){
                    if(formPanel.validate(e.getFirstName(),e.getLastName(),e.getUsername())){
                        formController.updateUser(e);
                        tablePanel.setData(formController.getUsers());
                    }

                }else if(s.equals("delete")){
                    formController.deleteUser(e);
                    tablePanel.setData(formController.getUsers());
                }

            }
        });

        add(loginPanel,BorderLayout.EAST);
        add(tablePanel,BorderLayout.CENTER);

        languageComboBox = new JComboBox();

		DefaultComboBoxModel langComboModel = new DefaultComboBoxModel();
        langComboModel.addElement(new Locale("en","US"));
        langComboModel.addElement(new Locale("de","DE"));
        languageComboBox.setModel(langComboModel);
        languageComboBox.setSelectedIndex(0);
        languageComboBox.addItemListener(e -> {
            LabelProvider.setLocale((Locale) languageComboBox.getSelectedItem());
            loginPanel.setLabels();
            tablePanel.setLabels();
            formPanel.setLabels();
        });

        add(languageComboBox,BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,500);
        setMinimumSize(new Dimension(500,400));
        setVisible(true);
    }
}
