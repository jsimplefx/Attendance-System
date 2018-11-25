package Dashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        java.util.Date current = new java.util.Date();
        date.setText(String.valueOf(current));
    }
}
