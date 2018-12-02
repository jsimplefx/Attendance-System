package List;

import Classes.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dbConnection.Connect;
import javafx.beans.binding.BooleanBinding;
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
    /* TODO handle table editing
       TODO handle add button click
     *  */

    @FXML
    private TableView<Student> list_table;

    @FXML
    private TableColumn<Student, Integer> id_col;

    @FXML
    private TableColumn<Student, String> name_col;

    @FXML
    private TableColumn<Student, Integer> past_col;

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

    // an observable list of students
    private ObservableList<Student> students = FXCollections.observableArrayList();

    // get connection
    private Connection conns = Connect.getConnect();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadTable(); // load table on class start
        // define what each column is going to hold (based on student class)
        id_col.setCellValueFactory(new PropertyValueFactory<>("ID"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        mail_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        past_col.setCellValueFactory(new PropertyValueFactory<>("past"));

        list_table.setItems(students);

        // disable add button unless all fields are filled
        BooleanBinding isData = id_field.textProperty().isEmpty()
                .and(name_field.textProperty().isEmpty())
                .and(abs_field.textProperty().isEmpty())
                .and(mail_field.textProperty().isEmpty());
        addBtn.disableProperty().bind(isData);
    }

    @FXML
    void addRow(ActionEvent event) {

        // if connection is closed get it again
        try {
            if (conns.isClosed()) conns = Connect.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "insert into Students (ID, name, mail, past) values(?, ?, ?, ?)";

        try {
            PreparedStatement pst = Objects.requireNonNull(conns).prepareStatement(query);
            pst.setString(1, id_field.getText());
            pst.setString(2, name_field.getText());
            pst.setString(3, mail_field.getText());
            pst.setString(4, abs_field.getText());

            pst.execute();
            pst.close();
            conns.close();
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id_field.clear();
        name_field.clear();
        abs_field.clear();
        mail_field.clear();
    }

    @FXML
    void deleteRow(ActionEvent event){

        // if connection is closed get it again
        try {
            if (conns.isClosed()) conns = Connect.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Student stud = list_table.getSelectionModel().getSelectedItem(); // get selected item
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

                PreparedStatement pst = Objects.requireNonNull(conns).prepareStatement(query);
                pst.execute();
                conns.close(); // close connection for now
                loadTable(); // reload tables after deleting row
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadTable(){
        list_table.getItems().clear(); // clear table content before adding them again
        try {
            if (conns.isClosed()) conns = Connect.getConnect(); // if connection is closed get it again
            ResultSet rs = Objects.requireNonNull(conns).createStatement().executeQuery(" select * from Students"); // sql statement
            while (rs.next()){
                // store each row in a student object
                students.add(new Student(rs.getInt("ID"), rs.getString("name"),
                        rs.getString("gender"), rs.getString("mail"), rs.getInt("past")));
            }
            rs.close(); // close query
            conns.close(); // close connection for now
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
