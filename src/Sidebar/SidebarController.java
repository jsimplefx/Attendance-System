package Sidebar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {
     /* clicking a section button will just change the center of the pane
      not actually the whole window
       (this allows us to make the sidebar permanent and doesn't move
       */
    @FXML private BorderPane homePane;

    @FXML private Label UserName;

    // handle buttons press events.
    @FXML private void DashboardBtnPressed(MouseEvent event) throws IOException {
        loadPane("../Dashboard/Dashboard");
    }

    @FXML private void AttenBtnPressed(MouseEvent  event) throws IOException {
        loadPane("../Attendance/Attendance");
    }

    @FXML private void ListBtnPressed(MouseEvent  event) throws IOException {
        loadPane("../List/List");
    }

    @FXML
    public void AboutBtnPressed(MouseEvent mouseEvent) throws IOException {
        loadPane("../About/About");
    }

    @FXML private void SettingsBtnPressed(MouseEvent  event) throws IOException {
        loadPane("../Settings/Settings");
    }

    @FXML void logoutNow(ActionEvent event) throws IOException {
        // an alert box to confirm user action
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You really want to logout?");
        ButtonType Yes = new ButtonType("Yes");
        ButtonType No = new ButtonType("No");
        alert.getButtonTypes().setAll(Yes, No);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == Yes){
            // back to the main scene if user selected to logout
            Parent Logout = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
            Scene Login = new Scene(Logout);
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Login");
            window.setScene(Login);
            window.show();
        }

    }

    private void loadPane(String UI) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UI+".fxml"));
        homePane.setCenter(root);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserName.setStyle("-fx-font-weight: bold");
        UserName.setText("Welcome");
    }

}
