package backend.repository;

import java.util.List;

import backend.model.User;
import backend.repository.hibernate.HibernateUserDao;
import backend.repository.jdbc.JDBCUserDao;

/**
 * Created by dogaro on 11/07/2016.
 */
/**
 * @author Dogar Octavian
 * {@link User} entity's CRUD utilities interface, implemented by DA classes: {@link JDBCUserDao}, {@link HibernateUserDao} 
 */
public interface UserDao {

    List<User> getAllUsers() throws RepositoryException;
    User getUserByUsername(String username)throws RepositoryException;
    User getUserById(Long id) throws RepositoryException;
    User insertUser(User user)throws RepositoryException;
    void updateUser(User user)throws RepositoryException;
    void deleteUser(User user)throws RepositoryException;

}
