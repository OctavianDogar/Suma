package backend.service.impl;

import java.security.MessageDigest;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.model.User;
import backend.repository.DaoFactory;
import backend.repository.RepositoryException;
import backend.repository.UserDao;
import backend.service.ServiceException;
import backend.service.UserService;
import backend.util.PasswordEncrypter;

/**
 * Created by dogaro on 11/07/2016.
 */


/**
 * @author Dogar Octavian
 * Service layer implementation class
 */
public class UserServiceImpl implements UserService{
	
	private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
    private DaoFactory df;
    private UserDao ud;

    public UserServiceImpl(){
        df = DaoFactory.getInstance();
        ud = df.getUserDao();
    }

    public boolean login(String username, String password) {

        User user = ud.getUserByUsername(username);
        
        String candidatePassword = PasswordEncrypter.generateHashedPassword(password,"");

        return MessageDigest.isEqual(user.getPassword().getBytes(),candidatePassword.getBytes());
    }

    public User getUserByUsername(String username) {
        User user=null;
        try{
            user = ud.getUserByUsername(username);
        }catch(RepositoryException re){
            LOG.trace("Unable to get user by username",re);
            throw new ServiceException("Unable to get user by username",re);
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> list;
        try{
            list = ud.getAllUsers();
        }catch (RepositoryException re){
            LOG.trace("Unable to get all users",re);
            throw new ServiceException("Unable to get all users",re);
        }
        return list;
    }

    public User insertUser(User user) {
        try{
            user.setPassword(PasswordEncrypter.generateHashedPassword( user.getPassword(),""));
            ud.insertUser(user);
        }catch (RepositoryException re){
            LOG.trace("Unable to insert user",re);
            throw new ServiceException("Unable to insert user",re);
        }
        return user;
    }

    public void updateUser(User user) {
        try{
            user.setPassword(PasswordEncrypter.generateHashedPassword(user.getPassword(),""));
            ud.updateUser(user);
        }catch (RepositoryException re){
            LOG.trace("Unable to update user",re);
            throw new ServiceException("Unable to update user",re);
        }
    }

    public void deleteUser(User user) {
        try{
           ud.deleteUser(user);
        }catch (RepositoryException re){
            LOG.trace("Unable to delete user",re);
            throw new ServiceException("Unable to delete user",re);
        }
    }
}
