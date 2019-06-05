package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;


public class Connect {
    public static Connection getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            /*
            we need a way to make the program read the database when its run from the jar,
            the resource URL is not getting this job done,
            otherwise this app is ready to be shipped and wrapped
            */
            String UrlInit = "jdbc:sqlite:"; // constant, the rest is the file directory which will be changed on each extract
            String dbUrl = "Database/PersonDB.db";
            return DriverManager.getConnection(UrlInit + dbUrl);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection checkConn() {
        Connection con = getConnect();
        // if connection is closed get it again
        try {
            if (Objects.requireNonNull(con).isClosed()) con = Connect.getConnect();
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

