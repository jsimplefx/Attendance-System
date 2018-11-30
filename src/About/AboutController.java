package About;

import Classes.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    @FXML
    private ImageView portrait;

    @FXML
    private Label gender;

    @FXML
    private Label name;

    @FXML
    private Label ID;

    @FXML
    private Label email;

    public void setLabels(Teacher teacher){
        portrait.setImage(teacher.getPortrait());
        name.setText(teacher.getName());
        ID.setText(Integer.toString(teacher.getID()));
        email.setText(teacher.getEmail());
        gender.setText(teacher.getGender());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
