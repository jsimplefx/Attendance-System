package Login;

/*
TODO an image view for the main dashboard (don't want to show everything asap).
TODO design the rest of the scenes.
TODO add students data.
TODO tell the other group niggas am working on this.
TODO code cleanup and optimization.
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("resources/window.png")); // set window icon
        primaryStage.setScene(new Scene(root, 480, 400));
        primaryStage.setResizable(false); // make the window unresizable (easier to design and no need to worry about that anymore)
        primaryStage.show();
        // ask user to confirm if they tried to close the program
        primaryStage.setOnCloseRequest(we -> {
            we.consume(); // consume the close event the program wont close anyway even if you said no
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Are you sure You want to exit?");
            ButtonType Yes = new ButtonType("Yes");
            ButtonType No = new ButtonType("No");
            alert.getButtonTypes().setAll(Yes, No);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == Yes) primaryStage.close();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
