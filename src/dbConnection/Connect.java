package dbConnection;

import java.sql.*;


public class Connect {
    public static Connection getConnect()   {
        try {
            Class.forName("org.sqlite.JDBC");
            // create a connection variable
            return DriverManager.getConnection("jdbc:sqlite:src/Database/PersonDB.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

