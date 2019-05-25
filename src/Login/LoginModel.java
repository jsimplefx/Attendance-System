package Login;

import Classes.Teacher;
import dbConnection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginModel {
    private static Teacher logged; // new teacher to store the logged one in
    // get connection
    private Connection con = Connect.getConnect();

    public static Teacher getLogged() {
        return logged;
    }

    private static void setLogged(ResultSet set) throws SQLException {
        // store the logged in user details in an object for later use
        logged = new Teacher(set.getString("name"), set.getString("pass"),
                set.getInt("id"), set.getString("gender"), set.getString("email"),
                set.getString("subjects"), set.getString("exp"), set.getLong("phone"));
    }

    // check login details
    boolean isCorrect(String User, String Pass) throws SQLException {

        // if connection is closed get it again
        if (con.isClosed()) {
            con = Connect.getConnect();
        }
        PreparedStatement statement;
        ResultSet set;
        String query = "select * from Teachers where id = ? and pass = ?";
        statement = Objects.requireNonNull(con).prepareStatement(query);
        statement.setString(1, User);
        statement.setString(2, Pass);
        set = statement.executeQuery();
        if (set.next()) { // only store the logged in user if its correct
            setLogged(set);
            return true;
        } else return false;
    }
}
