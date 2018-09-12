package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnector {
    public static Connection createConnection(){
        Connection con= null ;
        String databaseName = System.getenv("database_name");
        String host = System.getenv("host");
        String username = System.getenv("username");
        String password = System.getenv("password");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionString = String.format("jdbc:mysql://%s/%s?autoReconnect=true&useSSL=false", host, databaseName);
            con = DriverManager.getConnection(connectionString, username, password);
            if(!con.isClosed()) System.out.println("DB Connection initialized.");

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
