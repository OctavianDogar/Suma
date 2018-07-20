package frontend.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import frontend.gui.model.LoginEvent;
import frontend.utils.LabelProvider;

/**
 * Created by dogaro on 13/07/2016.
 */

/**
 * @author Dogar Octavian
 * Panel class for encapsulating user login data credentials
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private String innerBorderS;
    private Border innerB;

    private JButton loginBtn;

    private LoginListener loginListener;

    public void setLabels(){
        usernameLbl.setText(LabelProvider.getLabel("LoginPanel.usernameLbl"));
        passwordLbl.setText(LabelProvider.getLabel("LoginPanel.passwordLbl"));
        innerBorderS = LabelProvider.getLabel("LoginPanel.innerBorder");
        innerB = BorderFactory.createTitledBorder(innerBorderS);
        Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder,innerB));
        loginBtn.setText(LabelProvider.getLabel("LoginPanel.loginBtn"));

    }


    public LoginPanel(){
        innerBorderS = LabelProvider.getLabel("LoginPanel.innerBorder");
        innerB = BorderFactory.createTitledBorder(innerBorderS);
        Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outterBorder,innerB));

        usernameLbl = new JLabel(LabelProvider.getLabel("LoginPanel.usernameLbl"));
        passwordLbl = new JLabel(LabelProvider.getLabel("LoginPanel.passwordLbl"));
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        loginBtn = new JButton(LabelProvider.getLabel("LoginPanel.loginBtn"));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();

                String password = new String(passwordField.getPassword());

                LoginEvent loginEvent = new LoginEvent(this,username,password);


                if(loginListener!=null){
                    loginListener.loginEventOccurred(loginEvent);
                }
            }
        });


        layoutComponents();


        setVisible(true);
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
        add(usernameLbl,gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(usernameField,gc);

        //secondRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(passwordLbl,gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(passwordField,gc);

        //nextRow
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(loginBtn,gc);

    }

    public void setLoginListener(LoginListener loginListener){
        this.loginListener = loginListener;
    }



}
