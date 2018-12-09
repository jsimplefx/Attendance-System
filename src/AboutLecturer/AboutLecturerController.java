package AboutLecturer;

import Classes.Teacher;
import Login.LoginModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutLecturerController implements Initializable {
    @FXML
    private Label gender;

    @FXML
    private Label name;

    @FXML
    private Label ID;

    @FXML
    private Label email;

    @FXML
    private Label subjects;

    @FXML
    private Label exp;

    @FXML
    private Label number;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Teacher teacher = LoginModel.getLogged(); // get logged in teacher from login model class

        // set the about info to that
        name.setText(teacher.getName());
        ID.setText(Integer.toString(teacher.getID()));
        email.setText(teacher.getEmail());
        gender.setText(teacher.getGender());
        subjects.setText(teacher.getSubjects());
        exp.setText(teacher.getXP());
        number.setText(String.valueOf(teacher.getPhone()));

    }


}
