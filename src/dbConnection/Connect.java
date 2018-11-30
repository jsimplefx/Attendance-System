package dbConnection;

import java.sql.*;


public class Connect {
    public static Connection getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:Database/PersonDB.db");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }


    }
}

