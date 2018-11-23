package Login;

import Database.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    private Connection con;

    LoginModel() {
        con = Connect.getConnect();
    }

    public boolean isConnected() {
        try {
            return !con.isClosed();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    boolean isCorrect(String User, String Pass) throws SQLException {
        PreparedStatement statement;
        ResultSet set;
        String query = "select * from Teachers where id = ? and pass = ?";

            statement = con.prepareStatement(query);
            statement.setString(1, User);
            statement.setString(2, Pass);
            set = statement.executeQuery();
        return set.next();
    }
}


