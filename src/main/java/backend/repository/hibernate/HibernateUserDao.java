package backend.repository.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.model.User;
import backend.model.UserType;
import backend.repository.RepositoryException;
import backend.repository.UserDao;

/**
 * @author Dogar Octavian 
 * Hibernate data access implementation class
 */
public class HibernateUserDao implements UserDao {

	private static Logger LOG = LoggerFactory.getLogger(HibernateUserDao.class);

	@SuppressWarnings({ "deprecation" })
	@Override
	public List<User> getAllUsers() throws RepositoryException {

		Session session = null;
		Transaction tx = null;

		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<User> users = session.createCriteria(User.class).list();

			tx.commit();
			return users;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Unable to get all users", e);
			throw new RepositoryException("Unable to get all users",e);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("deprecation")
	public User getUserByUsername(String username) throws RepositoryException {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(User.class);
			User user = (User) criteria.add(Restrictions.eq("username", username)).uniqueResult();

			if (user == null)
				throw new NullPointerException();
			tx.commit();
			return user;
		} catch (HibernateException | NullPointerException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Unable to get user by username", e);
			throw new RepositoryException("Unable to get user by username", e);
		} finally {
			session.close();
		}
	}

	public User getUserById(Long id) throws RepositoryException {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			User user = (User) session.get(User.class, id);
			tx.commit();
			return user;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Unable to get user by id",e);
			throw new RepositoryException(e);
		} finally {
			session.close();
		}
	}

	public User insertUser(User user) throws RepositoryException {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			session.save(user);
			tx.commit();
			return user;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Unable to insert User",e);
			throw new RepositoryException("Unable to insert User",e);
		} finally {
			session.close();
		}
	}

	public String getUType(User user) {
		String match;
		UserType userType = user.getUserType();
		if (userType == UserType.Admin) {
			match = "Admin";
		} else if (userType == UserType.User) {
			match = "User";
		} else {
			match = "Guest";
		}
		return match;
	}

	public void updateUser(User user) throws RepositoryException {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			User got = (User) session.get(User.class, user.getId());

			got.setFirstName(user.getFirstName());
			got.setLastName(user.getLastName());
			got.setUsername(user.getUsername());
			got.setPassword(user.getPassword());
			got.setUuid(user.getUUID());
			got.setUserType(user.getUserType());

			session.merge(got);

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				LOG.error("Unable to update user",e);
				tx.rollback();
			throw new RepositoryException("Unable to update user",e);
		} finally {
			session.close();
		}

	}

	public void deleteUser(User user) throws RepositoryException {
		Session session = null;
		Transaction tx = null;

		try {
			session = SessionManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			session.delete(user);

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Unable to delete user",e);
			throw new RepositoryException("Unable to delete user",e);
		} finally {
			session.close();
		}

	}

}
