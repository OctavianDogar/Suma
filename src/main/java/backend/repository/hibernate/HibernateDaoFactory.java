package backend.repository.hibernate;

import backend.repository.DaoFactory;
import backend.repository.UserDao;

/**
 * @author Dogar Octavian
 * Auxiliary class that receives an instance of the Hibernate data access implementation
 */
public class HibernateDaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return new HibernateUserDao();
    }

}
