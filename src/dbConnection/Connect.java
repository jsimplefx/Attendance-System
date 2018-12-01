package dbConnection;

import java.sql.*;


public class Connect {
    private static Connection conn;
    public static Connection getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn =  DriverManager.getConnection("jdbc:sqlite:Database/PersonDB.db"); // set connection
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }
}

