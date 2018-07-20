package backend.service;

import java.util.List;

import backend.model.User;
import backend.service.impl.UserServiceImpl;

/**
 * Created by dogaro on 11/07/2016.
 */

/**
 * @author Dogar Octavian
 * Service layer functionalities interface implemented by {@link UserServiceImpl}
 */
public interface UserService {

    boolean login(String username, String password) throws ServiceException;
    User getUserByUsername(String username)throws ServiceException;
    List<User> getAllUsers()throws ServiceException;
    User insertUser(User user)throws ServiceException;
    void updateUser(User user)throws ServiceException;
    void deleteUser(User user)throws ServiceException;

}
