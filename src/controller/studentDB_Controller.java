package controller;

import Model.Student;
import db.StudentDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class studentDB_Controller {

    @FXML
    private TableView<Student> studentView;

    @FXML
    private JFXTextField studentId;

    @FXML
    private TableColumn<Student, Integer> ColumnStudentId;

    @FXML
    private TableColumn<Student, String> ColumnStudentName;

    @FXML
    private TableColumn<Student, String> ColumnStudentMail;

    @FXML
    private TableColumn<Student, String> ColumnPayMethod;

    @FXML
    private TableColumn<Student, Integer> ColumnTelNo;

    @FXML
    private JFXTextField telNo;

    @FXML
    private JFXTextField studentEmail;

    @FXML
    private JFXTextField studentsName;

    @FXML
    private JFXTextField paymentMethod;

    @FXML
    private JFXButton searchStudentBtn;

    @FXML
    private JFXTextField searchStuId;

    @FXML
    private JFXButton dltStudBtn;

    @FXML
    private JFXButton updateStuBtn;

    @FXML
    private JFXButton refreshBtn;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnQuit;

    @FXML
    void backFromDb(ActionEvent event) {

        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Registration Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void dltStudent(ActionEvent event) throws Exception {

        try{
            StudentDAO.deleteStudent(studentId.getText());

            ObservableList<Student> empList = StudentDAO.getAllRecords();
            populateTable(empList);

        }catch(Exception e){
            System.out.println("Error occurs while deleting the data " + e);
            e.printStackTrace();
            throw e;
        }

    }

    @FXML
    void quitFromDb(ActionEvent event) {

        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void refreshStudent(ActionEvent event) throws Exception{
        try {
            ObservableList<Student> empList = StudentDAO.getAllRecords();
            populateTable(empList);
        }catch (Exception e){
            System.out.println("Error occurs while refreshing the data " + e);
            e.printStackTrace();
            throw e;
        }

    }

    @FXML
    void searchStudent(ActionEvent event) throws Exception {

        try{
            ObservableList<Student> list = StudentDAO.SearchEmployee(searchStuId.getText());
            populateTable(list);

        }catch(Exception e){
            System.out.println("Error occurs while searching the data " + e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    void updateStudent(ActionEvent event) throws Exception{
        try{
            StudentDAO.updateEmployee(studentId.getText(),studentEmail.getText(),telNo.getText(), studentsName.getText(),paymentMethod.getText());

            ObservableList<Student> empList = StudentDAO.getAllRecords();
            populateTable(empList);

        }catch(Exception e){
            System.out.println("Error occurs while updating the data " + e);
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    void btnAdd(ActionEvent event) throws Exception{
        studentId.clear();studentEmail.clear();telNo.clear();studentsName.clear();paymentMethod.clear();
    }

    @FXML
    private void initialize() throws Exception{

        // that setValueFactory function enables you to replace data into the specific columns that you created...

        // In StudentDAO you created getStudentObjects() and getAllRecords() methods.. which is getting all records from the
        // database as a resultSet and put those into ObservableList which can handle the data according to the columnLabel..
        // and in that observableList returns each column data into Setter methods in the Student Class...............

        // In bellow what I does is set the values to the table's columns as a property data using setValueFactory() method
        // (which are handle by the Student Class)

        // If you used property rather than String you need to specify that as a asObject().....
        ColumnStudentId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        ColumnStudentName.setCellValueFactory(cellData -> cellData.getValue().namePropertyProperty());
        ColumnStudentMail.setCellValueFactory(cellData -> cellData.getValue().PMethodPropertyProperty());
        ColumnPayMethod.setCellValueFactory(cellData -> cellData.getValue().emailPropertyProperty());
        ColumnTelNo.setCellValueFactory(cellData -> cellData.getValue().telNoPropertyProperty().asObject());

        ObservableList<Student> empList = StudentDAO.getAllRecords();
        populateTable(empList);
    }

    private void populateTable(ObservableList<Student> empList) {
        studentView.setItems(empList);
    }

}
