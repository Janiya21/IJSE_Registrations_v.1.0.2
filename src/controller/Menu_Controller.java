package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu_Controller implements Initializable {

    @FXML
    private Pane vBoxX;

    @FXML
    private Button left;

    @FXML
    private Button addStudent;

    @FXML
    private Button timetable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), vBoxX);
        translate.setToX(-145);
        translate.play();
    }

    @FXML
    void goLeft(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1.2), vBoxX);
        translate.setToX(-165);
        translate.play();
    }

    @FXML
    void goRight(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1.2), vBoxX);
        translate.setToX(0);
        translate.play();
//        vBoxX.setPrefWidth(1222);
    }

    @FXML
    void addNewStudent(ActionEvent event) {
        Stage stage = (Stage) addStudent.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/registration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Menu Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void settings(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) addStudent.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/Login3.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Login Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void aboutIJSE(ActionEvent event) {

        try {
            Desktop.getDesktop().browse(new URL("https://www.ijse.lk/").toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void loadTimetable(ActionEvent event) {
        Stage stage = (Stage) timetable.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/timeTable.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Timetable Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
