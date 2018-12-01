package List;

import Classes.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dbConnection.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListController implements Initializable {


    @FXML
    private TableView<Student> list_table;

    @FXML
    private TableColumn<Student, Integer> id_col;

    @FXML
    private TableColumn<Student, String> name_col;

    @FXML
    private TableColumn<Student, Boolean> past_col;

    @FXML
    private TableColumn<Student, String> mail_col;

    @FXML
    private JFXTextField id_field;

    @FXML
    private JFXTextField name_field;

    @FXML
    private JFXTextField abs_field;

    @FXML
    private JFXTextField mail_field;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton delBtn;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    private Connection con = Connect.getConnect();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadTable();
        id_col.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        past_col.setCellValueFactory(new PropertyValueFactory<>("past"));
        mail_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        list_table.setItems(students);

    }

    @FXML
    void addRow(ActionEvent event) {

    }

    @FXML
    void deleteRow(){
        Student stud = list_table.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Record");
        alert.setHeaderText("Are you sure You want to delete record with ID: " + stud.getID() + "?");
        ButtonType Yes = new ButtonType("Yes");
        ButtonType No = new ButtonType("No");
        alert.getButtonTypes().setAll(Yes, No);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == Yes) {
            String query = String.format("delete from Students where id=%d", stud.getID());
            try {
                PreparedStatement pst = Objects.requireNonNull(con).prepareStatement(query);
                pst.execute();
                loadTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadTable(){
        list_table.getItems().clear();

        try {

            ResultSet rs = Objects.requireNonNull(con).createStatement().executeQuery(" select * from Students");

            while (rs.next()){
                students.add(new Student(rs.getInt("ID"), rs.getString("name"),
                        rs.getString("gender"), rs.getString("mail"), rs.getInt("past")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
