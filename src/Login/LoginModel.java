package Login;

import Classes.Teacher;
import dbConnection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginModel {
    // get connection
    private Connection con = Connect.getConnect();

    private static Teacher logged = new Teacher(); // new teacher to store the logged one in
    // check login details
    boolean isCorrect(String User, String Pass) throws SQLException {

        // if connection is closed get it again
        if (con.isClosed()){
            con = Connect.getConnect();
        }
        PreparedStatement statement;
        ResultSet set;
        String query = "select * from Teachers where id = ? and pass = ?";
            statement = Objects.requireNonNull(con).prepareStatement(query);
            statement.setString(1, User);
            statement.setString(2, Pass);
            set = statement.executeQuery();
            setLogged(set);
                // close connection after checking login
            con.close();
        return set.next();
    }

     public static Teacher getLogged() {
        return logged;
    }
     private static void setLogged(ResultSet set) throws SQLException {
        // store the logged in user details in an object for later use
        logged = new Teacher(set.getString("name"), set.getString("pass"),
                set.getInt("id"), set.getString("gender"), set.getString("email"),
                set.getString("subjects"), set.getString("exp"), set.getLong("phone"));
    }
}


