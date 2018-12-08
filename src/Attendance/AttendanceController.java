package Attendance;

import Classes.Student;
import dbConnection.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {

    @FXML
    private TableView<Student> list_table;

    @FXML
    private TableColumn<Student, Integer> id_col;

    @FXML
    private TableColumn<Student, String> name_col;

    @FXML
    private TableColumn<Student, String> present_col;

    @FXML
    private TableColumn<Student, String> excuse_col;




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
        present_col.setCellValueFactory(new PropertyValueFactory<>("present"));
        excuse_col.setCellValueFactory(new PropertyValueFactory<>("excuse"));

        list_table.setItems(students);

        list_table.setEditable(true);
        excuse_col.setCellFactory(TextFieldTableCell.forTableColumn());
        present_col.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void loadTable(){
        list_table.getItems().clear(); // clear table content before adding them again
        try {
            checkConn();
            ResultSet rs = Objects.requireNonNull(conns).createStatement().executeQuery(" select * from Students"); // sql statement
            while (rs.next()){
                // store each row in a student object
                students.add(new Student(rs.getInt("ID"), rs.getString("name"), rs.getString("excuse"), rs.getString("present")));
            }
            rs.close(); // close query
            conns.close(); // close connection for now
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // this method can edit any selected row (if you have enabled editing for it)
    public void takeAtten(TableColumn.CellEditEvent edditedcell){
        Student selected =  list_table.getSelectionModel().getSelectedItem();
        String query = "update Students set " +  edditedcell.getTableColumn().getText().toLowerCase() + " = ? where id = ?";
        try {
            checkConn();
            PreparedStatement pst = Objects.requireNonNull(conns).prepareStatement(query);
            pst.setString(1, edditedcell.getNewValue().toString());
            pst.setString(2, String.valueOf(selected.getID()));
            pst.execute();
            conns.close(); // close connection for now
            loadTable(); // reload tables after updating field
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void checkConn(){
        // if connection is closed get it again
        try {
            if (conns.isClosed()) conns = Connect.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
