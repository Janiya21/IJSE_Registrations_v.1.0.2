package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import db.TimetableDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class timeTable_Controller {

    @FXML
    private JFXTextField Mon_1;

    @FXML
    private JFXTextField Mon_2;

    @FXML
    private JFXTextField Mon_3;

    @FXML
    private JFXTextField wed_1;

    @FXML
    private JFXTextField wed_2;

    @FXML
    private JFXTextField wed_3;

    @FXML
    private JFXTextField Mon_4;

    @FXML
    private JFXTextField wed_4;

    @FXML
    private JFXTextField week;

    @FXML
    private JFXTextField month;

    @FXML
    private JFXTextField Fri_1;

    @FXML
    private JFXTextField Fri_2;

    @FXML
    private JFXTextField Fri_3;

    @FXML
    private JFXTextField Fri_4;

    @FXML
    private JFXButton back;

    @FXML
    void SaveInDB(ActionEvent event) {
        insertTimeTable(Mon_1,Mon_2,Mon_3,Mon_4,wed_1,wed_2,wed_3,wed_4,Fri_1,Fri_2,Fri_3,Fri_4,week,month);
    }

    public static void insertTimeTable(JFXTextField m1, JFXTextField m2, JFXTextField m3, JFXTextField m4, JFXTextField w1, JFXTextField w2,
                                       JFXTextField w3, JFXTextField w4, JFXTextField f1, JFXTextField f2, JFXTextField f3, JFXTextField f4,
                                       JFXTextField weekOf , JFXTextField monthOf) {

        String mon1 = m1.getText();
        String mon2 = m2.getText();
        String mon3 = m3.getText();
        String mon4 = m4.getText();
        String wed1 = w1.getText();
        String wed2 = w2.getText();
        String wed3 = w3.getText();
        String wed4 = w4.getText();
        String fri1 = f1.getText();
        String fri2 = f2.getText();
        String fri3 = f3.getText();
        String fri4 = f4.getText();
        String selectedWeek = weekOf.getText();
        String selectedMonth = monthOf.getText();

        boolean isEmpty = mon1.trim().isEmpty() || mon2.trim().isEmpty() || mon3.trim().isEmpty() || mon4.trim().isEmpty() ||
                wed1.trim().isEmpty() || wed2.trim().isEmpty() || wed3.trim().isEmpty() || wed4.trim().isEmpty() || fri1.trim().isEmpty()
                || fri2.trim().isEmpty() || fri3.trim().isEmpty() || fri4.trim().isEmpty() || selectedWeek.trim().isEmpty()
                || selectedMonth.trim().isEmpty();

        if(!isEmpty){
            try {
                TimetableDAO.insertTimetable(selectedWeek,selectedMonth, mon1,mon2,mon3,mon4,wed1,wed2,wed3,wed4,fri1,fri2,fri3,fri4);
                System.out.println("Data added...");
                output("Adding Done!","Successfully added data into database...");
            }catch(Exception e){
                System.out.println("Error occurs while inserting data to timetable...");
                e.printStackTrace();
                output("Error!!","The data you entered not successful !!");
            }
        }else{
            output("Error!!","Check whether the fields are empty !!");
        }
    }

    @FXML
    void goBack(ActionEvent event) {

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Timetable Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void output(String title,String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
