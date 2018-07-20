package backend.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backend.model.User;
import backend.model.UserType;
import backend.repository.DaoFactory;
import backend.repository.RepositoryException;
import backend.repository.UserDao;

/**
 * Created by dogaro on 11/07/2016.
 */

/**
 * @author Dogar Octavian
 * JDBC data access implementation class
 */
public class JDBCUserDao implements UserDao {
	
	private static Logger LOG = LoggerFactory.getLogger(JDBCUserDao.class);
	
    private final ConnectionManager cn;
    @SuppressWarnings("unused")
	private final DaoFactory df;


    public JDBCUserDao(){
        cn = ConnectionManager.getInstance();
        df = DaoFactory.getInstance();
    }

    public List<User> getAllUsers() throws RepositoryException {
        final List<User> userList = new ArrayList<>();
        Connection con = null;
        try{
            con = cn.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from USER");

            while(rs.next()){
                User user = new User(rs.getLong("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString
                        ("password"),rs
                        .getString("username"), UserType.valueOf(rs.getString("userType")));
                user.setUuid(rs.getString("UUID"));
                userList.add(user);

            }

        } catch (SQLException e) {
        	LOG.trace("Unable to get all users",e);
            throw new RepositoryException("Unable to get all users",e);
        }finally {
            if(con!=null){
                cn.returnConnection(con);
            }
        }
        return userList;
    }

    public User getUserByUsername(String username) throws RepositoryException {
        User user;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = cn.getConnection();
            String sql = "select * from USER where username=?";
            ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, username);

            rs = ps.executeQuery();
            rs.next();

            user = new User(rs.getString("firstName"),rs.getString("lastName"),rs.getString("password"),rs.getString
                    ("username"), UserType.valueOf(rs.getString("userType")));

            return user;
        } catch (SQLException e) {
        	LOG.trace("Cannot get user by username",e);
            throw new RepositoryException("Cannot get user by username",e);
        }finally {
            if(con!=null){
                cn.returnConnection(con);
            }
        }
    }
    public User getUserById(Long id)throws RepositoryException{
        User user;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = cn.getConnection();
            String sql = "select * from USER where id=?";
            ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setLong(1, id);

            rs = ps.executeQuery();
            rs.next();

            user = new User(rs.getString("firstName"),rs.getString("lastName"),rs.getString("password"),rs.getString
                    ("username"), UserType.valueOf(rs.getString("userType")));

            return user;
        } catch (SQLException e) {
        	LOG.trace("Cannot get user by ID",e);
            throw new RepositoryException("Cannot get user by ID",e);
        }finally {
            if(con!=null){
                cn.returnConnection(con);
            }
        }
    }

    public User insertUser(User user) throws RepositoryException {
        Connection con = null; //LA SAVE SA NU FIE NULL LA UPDATE DACA II NULA NU O SALVEZ
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = cn.getConnection();

            String sql = "insert into User(firstName,lastName,username,uuid,password,userType) values (?,?,?,?,?," +
                    "?)";
            ps = con.prepareStatement(sql);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from USER ");

            rs.last();
            ps.setString(1,user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3,user.getUsername());
            ps.setString(4, user.getUuid().substring(0,4));
            ps.setString(5,user.getPassword());
            ps.setString(6,getUType(user));


            ps.executeUpdate();

        }catch (SQLException e){
            LOG.trace("Unable to insert user",e);
            throw new RepositoryException("Unable to insert user",e);
        }finally {
            if(con!=null){
                cn.returnConnection(con);
            }
        }
        return user;
    }

    public String getUType(User user){
        String match;
        UserType userType = user.getUserType();
        if (userType == UserType.Admin){
            match="Admin";
        }else if(userType == UserType.User){
            match="User";
        }else{
            match="Guest";
        }
        return match;
    }

    public void updateUser(User user) throws RepositoryException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();

            String sql = "update USER set firstName=?,lastName=?, password=?, userType=?, username=? where " +
                    "id=?";

            user.setUuid(user.getUuid());

            ps = con.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setString(4, getUType(user));
            ps.setString(5, user.getUsername());
            ps.setLong(6, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.trace("Unable update user",e);
            throw new RepositoryException("Unable to update user",e);
        }finally {
            if(con!=null){
                cn.returnConnection(con);
            }
        }
    }

    public void deleteUser(User user) throws RepositoryException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = cn.getConnection();
            String sql = "delete from USER where ID=?";
            ps = con.prepareStatement(sql);
            ps.setLong(1, user.getId());

            ps.executeUpdate();

        }catch  (SQLException e) {
            LOG.trace("Unable to delete user",e);
            throw new RepositoryException("Unable to delete from user",e);
        }finally {
            if(con!=null){
                cn.returnConnection(con);
            }
        }
    }
}
