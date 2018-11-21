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
    private ImageView portrait = new ImageView();

    @FXML
    private Label gender = new Label();

    @FXML
    private Label name = new Label();

    @FXML
    private Label ID = new Label();

    @FXML
    private Label email = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setLabels(Teacher teacher){
        portrait.setImage(teacher.getPortrait());
        name.setText(teacher.getName());
        ID.setText(Integer.toString(teacher.getID()));
        email.setText(teacher.getEmail());
        String gen;
        if (!teacher.isGender()){
            gen = "Male";
        } else {
            gen = "Female";
        }
        gender.setText(gen);

    }
}
