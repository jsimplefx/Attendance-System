package AboutLecturer;

import Classes.Teacher;
import Login.LoginModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutLecturerController implements Initializable {

    @FXML
    private ImageView avatar;
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
        String[] subj = teacher.getSubjects().split(" ");
        subjects.setText(""); // we set it to an empty string so it wont overlap in the next for loop
        for (int i = 0; i < subj.length; i++) {
            if (i == subj.length - 1) { // to prevent adding another comma at the end of the last subject
                subjects.setText(subjects.getText() + subj[i]);
                break;
            }
            subjects.setText(subjects.getText() + subj[i] + ", ");
        }
        exp.setText(teacher.getXP());
        number.setText(String.valueOf(teacher.getPhone()));

        Image male = new Image("resources/male.png");
        Image female = new Image("resources/female.png");

        if (teacher.getGender().startsWith("F") || teacher.getGender().startsWith("f")) {
            avatar.setImage((female));
        } else avatar.setImage(male);

    }


}
