package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import db.StudentDAO;

import static controller.registrationController.*;


public class paymentController {


    @FXML
    private JFXButton btnRegistration;

    @FXML
    private JFXButton Settings;

    @FXML
    private JFXButton GoBack;

    @FXML
    private JFXButton database;

    @FXML
    private JFXButton method1;

    @FXML
    private JFXButton method2;

    @FXML
    private JFXButton method3;

    static String btnChooses = null;

    @FXML
    void BtnBack(ActionEvent event) {

        Stage stage = (Stage) GoBack.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        StudentName

        primaryStage.setTitle("Database Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void BtnDatabase(ActionEvent event) {

        Stage stage = (Stage) database.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Database Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void BtnSettings(ActionEvent event) {

        Stage stage = (Stage) Settings.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Database Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void GoToReg(ActionEvent event) {

        Stage stage = (Stage) btnRegistration.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Database Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void BtnMethod01(ActionEvent event) throws Exception {
        btnChooses = "Method 01";
        alertBox();
    }

    @FXML
    void BtnMethod02(ActionEvent event) throws Exception {
        btnChooses = "Method 02";
        alertBox();
    }

    @FXML
    void btnMethod03(ActionEvent event) throws Exception {
        btnChooses = "Method 03";
        alertBox();
    }

    //============================================== Functions ================================================

    public void alertBox() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("After You Confirm this, all details will upload to the database.");
        alert.setContentText("Are you sure about continue your payments according to the " + btnChooses + " ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            // replaced - insertEmployee
            StudentDAO.insertStudent(idNumber,StudentName,email,btnChooses,telNo);
            System.out.println("It will connect to database");

            if(btnChooses.equals("Method 01")){
                Stage stage = (Stage) method1.getScene().getWindow();
                stage.close();

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/studentView.fxml"));

                primaryStage.setTitle("Menu Window");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
            if(btnChooses.equals("Method 02")){
                Stage stage = (Stage) method2.getScene().getWindow();
                stage.close();

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/studentView.fxml"));

                primaryStage.setTitle("Menu Window");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
            if(btnChooses.equals("Method 03")){
                Stage stage = (Stage) method3.getScene().getWindow();
                stage.close();

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/studentView.fxml"));

                primaryStage.setTitle("Menu Window");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }

        } else {
            alert.close();
        }
    }


}
