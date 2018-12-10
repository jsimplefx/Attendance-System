package dbConnection;

import java.sql.*;


public class Connect {
    private static Connection conn; // create a connection variable
    public static Connection getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn =  DriverManager.getConnection("jdbc:sqlite:Database/PersonDB.db"); // set connection
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

