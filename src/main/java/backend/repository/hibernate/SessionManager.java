package backend.repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.util.PropertyManager;


/**
 * @author Dogar Octavian
 * Configuration class for the Hibernate API that add several externalised 
 * database properties to the initial configuration
 */
public class SessionManager {

	private static Logger LOG = LoggerFactory.getLogger(SessionManager.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory(){
		
		try{

			final Configuration configuration = new Configuration();
			configuration.setProperty("hibernate.connection.username", PropertyManager.INSTANCE.getProperty("user"));
			configuration.setProperty("hibernate.connection.password", PropertyManager.INSTANCE.getProperty("password"));
			configuration.setProperty("hibernate.connection.url", PropertyManager.INSTANCE.getProperty("dbUrl"));
			configuration.setProperty("hibernate.connection.pool_size", PropertyManager.INSTANCE.getProperty("dbConnectionPoolSize"));
			
			return configuration.configure("Hibernate.cfg.xml").buildSessionFactory();
			
		}catch(Throwable e){
			LOG.error("SessionFactory initialization failed",e);
			throw new ExceptionInInitializerError(e);
		}
		
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	
}
