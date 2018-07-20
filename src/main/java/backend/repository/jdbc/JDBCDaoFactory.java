package backend.repository.jdbc;

import backend.repository.DaoFactory;
import backend.repository.UserDao;

/**
 * Created by dogaro on 11/07/2016.
 */

/**
 * @author Dogar Octavian
 * Auxiliary class that receives an instance of the JDBC data access implementation
 */
public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return new JDBCUserDao();
    }


}
