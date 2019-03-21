package AboutApp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutAppController implements Initializable {
    @FXML
    private Hyperlink jfx;

    @FXML
    private Hyperlink suan;

    @FXML
    private Hyperlink fawesome;

    @FXML
    private Hyperlink cfx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final String[] URLs = {"https://github.com/Blacksuan19", "https://github.com/jfoenixadmin/JFoenix",
                "https://bitbucket.org/Jerady/fontawesomefx", "https://github.com/controlsfx/controlsfx"};
        suan.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URL(URLs[0]).toURI());
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });

        jfx.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URL(URLs[1]).toURI());
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });

        fawesome.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URL(URLs[2]).toURI());
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });

        cfx.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URL(URLs[3]).toURI());
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        });
    }
}
