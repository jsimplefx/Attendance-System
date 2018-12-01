package Login;

import dbConnection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

class LoginModel {
    // get connection
    private Connection con = Connect.getConnect();

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
            // close connection after checking login
            con.close();
        return set.next();
    }

}


