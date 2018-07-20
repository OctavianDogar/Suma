package backend.model;

/**
 * Created by dogaro on 11/07/2016.
 */
/**
 * @author Dogar Octavian
 * First database persisted entity and the object of the application
 */
public class User extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserType userType;

    public User(){
        super();
    }
    
    public User(String firstName,String lastName, String password, String username, UserType userType) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.userType = userType;
    }
    public User(Long id,String firstName,String lastName, String password, String username, UserType userType) {
        super();
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + getId() + '\'' +
                "UUID='" + getUuid() + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    public static long getSerialVesrionUID() {
        return serialVersionUID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public String getUUID(){
    	return getUuid();
    }
    
    public void setUUID(String UUID){
    	setUuid(UUID);
    }
}
