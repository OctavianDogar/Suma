package frontend.gui.model;


import java.util.EventObject;

import frontend.controller.LoginController;

/**
 * Created by dogaro on 13/07/2016.
 */

/**
 * @author Dogar Octavian
 * Custom Login Event used to carry login data towards the {@link LoginController}
 */
public class LoginEvent extends EventObject{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
    private String password;

    public LoginEvent(Object source) {
        super(source);
    }

    public LoginEvent(Object source, String userName, String password) {
        super(source);
        this.userName = userName;
        this.password = password;

    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    

}
