package codecomm.util;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {
    /*usage example: 
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        //code that uses the connection to work with the database
        pool.freeConnection(connection);
       (context.xml pooling requires tomcat6+)
    */
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/dbcodecomm");
        } catch (NamingException e){
            System.out.println(e);
        }
    }
    
    public static synchronized ConnectionPool getInstance() {
        if(pool == null){
            pool = new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }
    
    public void freeConnection(Connection c){
        try{
            c.close();
        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
