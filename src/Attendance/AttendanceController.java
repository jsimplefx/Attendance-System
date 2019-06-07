package Attendance;

import Classes.Student;
import Classes.Teacher;
import Login.LoginModel;
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
    private TableColumn<Student, Boolean> present_col;

    @FXML
    private TableColumn<Student, String> excuse_col;

    // get logged in teacher
    private Teacher logged = LoginModel.getLogged();

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

        list_table.setItems(students); // set table items as the Students observable list
        list_table.setEditable(true); // enable table editing
        excuse_col.setCellFactory(TextFieldTableCell.forTableColumn()); // enable column editing
        list_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // resize column based on whole table(window) size

        for (Student stud : students) {
            stud.getPresent().setOnAction(e -> updateAtten(stud));
        }
    }

    private void updateAtten(Student stud) {
        String query = "update '" + logged.getID() + "' set present = ? where id = ?"; // sql query
        try {
            checkConn(); // check connection
            PreparedStatement psts = conns.prepareStatement(query);
            psts.setBoolean(1, stud.getPresent().isSelected());
            psts.setString(2, String.valueOf(stud.getID())); // pass the id of the column that has to be edited
            conns.isClosed();
            psts.execute(); // execute query
            psts.close();
            conns.close(); // close connection for now
            System.out.println("we over!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadTable() {
        list_table.getItems().clear(); // clear table content before adding them again
        try {
            checkConn(); // check connection
            ResultSet rs = Objects.requireNonNull(conns).createStatement()
                    .executeQuery(" select * from '" + logged.getID() + "'"); // sql statement
            while (rs.next()) {
                // store each row in a student object
                students.add(new Student(rs.getInt("ID"), rs.getString("name"),
                        rs.getString("excuse"), rs.getBoolean("present"), rs.getString("subjects")));
            }
            rs.close(); // close statement
            conns.close(); // close connection for now
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // this method can edit any selected row (if you have enabled editing for it)
    public void takeAtten(TableColumn.CellEditEvent edditedcell) {
        Student selected = list_table.getSelectionModel().getSelectedItem(); // get the student being edited right now
        String query = "update '" + logged.getID() + "' set " + edditedcell.getTableColumn().getText().toLowerCase()
                + " = ? where id = ?"; // sql query
        try {
            checkConn(); // check connection
            PreparedStatement pst = Objects.requireNonNull(conns).prepareStatement(query);
            pst.setString(1, edditedcell.getNewValue().toString()); // pass the inputted value to be updated in that row
            pst.setString(2, String.valueOf(selected.getID())); // pass the id of the column that has to be edited
            pst.execute(); // execute query
            pst.close();
            conns.close(); // close connection for now
            loadTable(); // reload tables after updating field
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void checkConn() {
        // if connection is closed get it again
        try {
            if (conns.isClosed()) conns = Connect.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
