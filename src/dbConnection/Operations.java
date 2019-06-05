package dbConnection;

import Classes.Student;
import Classes.Teacher;
import Login.LoginModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Operations {

    private static Teacher logged = LoginModel.getLogged();
    private static Connection conns = Connect.getConnect();

    public static void FilterData(JFXTextField searchFiled, ObservableList<Student> students, TableView<Student> list_table) {
        FilteredList<Student> filteredList = new FilteredList<>(students, p -> true);
        searchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String word = newValue.toLowerCase();
                if (student.getID() != 0) { // to ignore empty fields (otherwise its a NullPointer ma dude)
                    if (String.valueOf(student.getID()).toLowerCase().contains(word)) return true;
                }
                if (student.getName() != null) { // to ignore empty fields (otherwise its a NullPointer ma dude)
                    if (student.getName().toLowerCase().contains(word)) return true;
                }
                if (student.getAbsences() != null) { // to ignore empty fields (otherwise its a NullPointer ma dude)
                    if (student.getAbsences().toLowerCase().contains(word)) return true;
                }
                if (student.getBar_status() != null) { // to ignore empty fields (otherwise its a NullPointer ma dude)
                    if (student.getBar_status().toLowerCase().contains(word)) return true;
                }
                if (student.getEmail() != null) { // to ignore empty fields (otherwise its a NullPointer ma dude)
                    return student.getEmail().toLowerCase().contains(word);
                }
                return false;
            });
            SortedList<Student> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(list_table.comparatorProperty());
            list_table.setItems(sortedList);
        });
    }

    public static void FilterClass(TableView<Student> list_table, ObservableList<Student> students,
                                   JFXComboBox<String> subjs) throws SQLException {
        list_table.setItems(students); // surpass the error when the observable list being used is the filtered one
        list_table.getItems().clear(); // clear table content before adding them again
        String selectedItem = subjs.getSelectionModel().getSelectedItem();
        checkConn(); // check connection
        String st = "select * from '" + logged.getID() + "' where subjects like " + "'%" + selectedItem + "%'";
        ResultSet rs = Objects.requireNonNull(conns).createStatement().executeQuery(st);
        while (rs.next()) {
            // store each row in a student object
            students.add(new Student(rs.getInt("ID"), rs.getString("name"),
                    rs.getString("gender"), rs.getString("email"), rs.getString("absences"),
                    rs.getString("bar"), rs.getString("subjects"), rs.getBoolean("present"), rs.getString("excuse")));
        }
    }

    public static void LoadData(TableView<Student> list_table, ObservableList<Student> students){
        list_table.getItems().clear(); // clear table content before adding them again
        try {
            checkConn(); // check connection
            ResultSet rs = Objects.requireNonNull(conns).createStatement()
                    .executeQuery(" select * from '" + logged.getID() + "'"); // sql statement
            while (rs.next()) {
                // store each row in a student object
                students.add(new Student(rs.getInt("ID"), rs.getString("name"),
                        rs.getString("gender"), rs.getString("email"), rs.getString("absences"),
                        rs.getString("bar"), rs.getString("subjects"), rs.getBoolean("present"), rs.getString("excuse")));
            }
            rs.close(); // close statement
            conns.close(); // close connection for now
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void checkConn() {
        // if connection is closed get it again
        try {
            if (conns.isClosed()) conns = Connect.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
