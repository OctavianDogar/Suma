package backend.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.repository.RepositoryException;
import backend.util.PropertyManager;

/**
 * Created by dogaro on 11/07/2016.
 */


/**
 * @author Dogar Octavian
 * JDBC configuration and connection establishing class 
 */
public final class ConnectionManager {
	
	private static Logger LOG = LoggerFactory.getLogger(ConnectionManager.class);
	
    private  List<Connection> pool;
    public static ConnectionManager instance;
    public static final int SIZE=10;

    public static final String dbUrl = PropertyManager.INSTANCE.getProperty("dbUrl");
    public static final String user = PropertyManager.INSTANCE.getProperty("user");
    public static final String password = PropertyManager.INSTANCE.getProperty("password");

    private ConnectionManager(){
        pool = new LinkedList<Connection>();
        initializePool();
    }

    public synchronized static ConnectionManager getInstance(){
        if(instance == null){
            instance= new ConnectionManager();
        }return instance;
    }

    public synchronized Connection getConnection() throws RepositoryException{

        Connection con = null;
        if(pool.size() > 0){
            con = pool.get(0);
            pool.remove(0);
        }
        if(con==null){
        	LOG.error("No connecitons in pool");
            throw new RepositoryException("No connections in pool");
        }
        return con;
    }

    public synchronized  void returnConnection(Connection con){
        if(pool.size()<SIZE){
            pool.add(con);
        }
    }

    private void initializePool(){
        pool = new LinkedList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            for(int i=0; i<SIZE; i++){
                Connection con;
                try{
                    con = DriverManager.getConnection(dbUrl,user,password);
                }catch(SQLException e){
                    LOG.error("Unable to instatiate pool",e);
                    throw new RepositoryException("Unable to instatiate pool",e);
                }
                pool.add(con);
            }
        } catch (IllegalAccessException| InstantiationException | ClassNotFoundException e) {
                LOG.error("Unable to instatiate pool", e);
                throw new RepositoryException("Unable to instatiate pool", e);
        }


    }

}