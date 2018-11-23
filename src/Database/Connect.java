package Database;

import java.sql.*;


public class Connect {
    public static Connection getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Database/PersonDB.db");
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }


    }
}

