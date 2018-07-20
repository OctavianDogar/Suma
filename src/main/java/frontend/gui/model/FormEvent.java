package frontend.gui.model;


import java.util.EventObject;


/**
 * Created by dogaro on 12/07/2016.
 */


/**
 * @author Dogar Octavian
 */
public class FormEvent extends EventObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
    private String firstName;
    private String lastName;
    private String password;
    private String userType;
    private String username;

    public FormEvent(Object source){
        super(source);
    }

    public FormEvent(Object source, String ID,String firstName, String lastName, String password, String
            userType, String username) {
        super(source);
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userType = userType;
        this.username = username;
    }

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
