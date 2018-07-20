package backend;

import backend.model.User;
import backend.model.UserType;
import backend.repository.DaoFactory;
import backend.repository.UserDao;
import backend.service.impl.UserServiceImpl;

public class Backen {

	public static void main(String[] args) {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		UserDao userDao = daoFactory.getUserDao();
		
		
	    User user1 = new User("Zeta2","Zeta2", "root","root",UserType.valueOf("User"));
	    User user2 = new User("first2","last2", "pass12","user2",UserType.valueOf("Admin"));
	    User user3 = new User("First3","Last3", "pass13","username3",UserType.valueOf("Guest"));
	    User u4 = new User("firstname","lastname","pass14","username4",UserType.valueOf("Guest"));
		
	    UserServiceImpl asd= new UserServiceImpl();
//	    asd.insertUser(user2);
	    
	    System.out.println(asd.getUserByUsername("user2"));
	    System.out.println(asd.getUserByUsername("user2"));
	    
	    System.out.println(userDao.getUserById(new Long(107)));
	    System.out.println(userDao.getUserById(new Long(107)));
	    System.out.println('\n');
	    asd.getAllUsers().stream().forEach(System.out::println);
	    
	    System.out.println('\n');
	    asd.getAllUsers().stream().forEach(System.out::println);
	    
	    
//	    asd.getUserByUsername("user2");
//	    asd.getUserByUsername("user2");
	    


	}

}
