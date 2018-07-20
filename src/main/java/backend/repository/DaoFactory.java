package backend.repository;

import backend.repository.hibernate.HibernateUserDao;
import backend.repository.jdbc.JDBCDaoFactory;
import backend.repository.jdbc.JDBCUserDao;

/**
 * Created by dogaro on 11/07/2016.
 */
/**
 * @author Dogar Octavian
 * Abstract utility class that enables switching the data access implementations: {@link HibernateUserDao}, {@link JDBCUserDao}
 */
public abstract class DaoFactory {

    public static DaoFactory getInstance(){
        return new JDBCDaoFactory();
//        return new HibernateDaoFactory();
    }

    public abstract UserDao getUserDao();
}
