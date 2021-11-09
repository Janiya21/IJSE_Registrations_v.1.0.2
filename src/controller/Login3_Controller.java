package controller;

import db.loginDAO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login3_Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private VBox vb_content;

    @FXML
    private Button signBtn;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private Button LoginBtn;

    @FXML
    private JFXComboBox<String> branchLogInCombo;

    //--------------------------------------

    @FXML
    private JFXTextField userSignInTxt;

    @FXML
    private JFXTextField passWordField;

    @FXML
    private JFXPasswordField rePassWordField;

    @FXML
    private JFXComboBox<String> branchSignInCombo;

    public static boolean userAvailable = false;
    public static int Number = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        branchSignInCombo.getItems().addAll(
                "colombo",
                "kandy",
                "galle"
        );

        branchLogInCombo.getItems().addAll(
                "colombo",
                "kandy",
                "galle"
        );
    }

    @FXML
    void left(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), vb_content);
        translate.setToX(root.getLayoutX());
        translate.play();
    }

    @FXML
    void right(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), vb_content);
        translate.setToX(vb_content.getLayoutX() + (root.getPrefWidth() - vb_content.getPrefWidth()) + 7);
        translate.play();
    }

    @FXML
    void signIn(ActionEvent event) {
        if(!userAvailable){
            try {
                checkValidateSignUpForm(userSignInTxt,passWordField,rePassWordField,branchSignInCombo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            new Alert(Alert.AlertType.ERROR,"This username is already exists..").show();
        }
    }

    @FXML
    public int ButtonLoginAction(ActionEvent event) throws Exception{

        int number = user(usernameField, passwordField,branchLogInCombo);

        if(number == 1) {
            output("Successful","Login to IJSE management Successful");

            try {
                Stage stage = (Stage) LoginBtn.getScene().getWindow();
                stage.close();

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));

                primaryStage.setTitle("Menu Window");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();

            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        if(number == 2) {
            new Alert(Alert.AlertType.WARNING,"Please check username and password again !!!").show();
        }

        if(number == 3) {
            new Alert(Alert.AlertType.WARNING,"Recheck!! Invalid data types or null fields !!!").show();
        }

        if(number == 4) {
            new Alert(Alert.AlertType.WARNING,"Recheck!! Fill the fields is compulsory..").show();
        }

        if(number == 5) {
            new Alert(Alert.AlertType.WARNING,"Select a Branch is compulsory !!!").show();
        }

        if(number == 6) {
            new Alert(Alert.AlertType.ERROR,"Please Fill the application to connect the database !!!").show();
        }

        Number = number;
        return Number;
    }

    @FXML
    public void checkUserAvailability(javafx.scene.input.KeyEvent keyEvent) throws SQLException {

        System.out.println(userSignInTxt.getText());
        if((loginDAO.userAvailability(userSignInTxt.getText()))){
            System.out.println("have");
            userAvailable = true;
            userSignInTxt.setStyle("-fx-text-inner-color: red;");
        }else{
            userAvailable = false;
            userSignInTxt.setStyle("-fx-text-inner-color: black;");
        }
    }

    //===================================== Functions ========================================================

    private int user(TextField Tf1, TextField Tf2 , ComboBox<String> comboB){

        boolean comboOK = !(comboB.getSelectionModel().isEmpty());// ok --> Combo box is not empty
        boolean fieldsOK = !(Tf1.getText().trim().isEmpty()) && !(Tf2.getText().trim().isEmpty()); // Both Fields are not empty

        if(comboOK && fieldsOK){

            try{
                String name = Tf1.getText();
                String enteredPassword = Tf2.getText();
                String enteredBranch = (String) comboB.getSelectionModel().getSelectedItem();

                loginDAO.checkValidation(name);
                String actualPassword = loginDAO.password_fromDB;
                String actual_branch = loginDAO.branch_fromDB;

                if(enteredPassword.equals(actualPassword) && enteredBranch.equals(actual_branch)){
                    Tf1.clear();Tf2.clear();
                    return 1;
                }
                else{
                    Tf2.clear();
                    return 2;
                }
            }catch(Exception ignored){
                Tf1.clear(); Tf2.clear();
                return 3;
            }
        }

        if(comboOK) return 4;

        if(fieldsOK) return 5;

        return 6;

    }

    //-------------------------------------------------------

    public void checkValidateSignUpForm(JFXTextField userName,JFXTextField password, JFXPasswordField reEnteredPassword, JFXComboBox<?> combo) throws Exception{
        boolean comboOK = !combo.getSelectionModel().isEmpty();// ok --> Combo box is not empty
        boolean fieldsOK = !(userName.getText().trim().isEmpty()) && !(password.getText().trim().isEmpty()) && !(reEnteredPassword.getText().trim().isEmpty());

        if(comboOK && fieldsOK){
            try{
                String username_toDB = userName.getText();
                String password_toDB = password.getText();
                String password_Confirmed = reEnteredPassword.getText();
                String combo_toDB = (String) combo.getSelectionModel().getSelectedItem();

                if( ! (loginDAO.userAvailability(username_toDB))){
                    if(password_Confirmed.equals(password_toDB)){

                        boolean addedDone = loginDAO.insertNewAdmin(username_toDB,password_Confirmed,combo_toDB);

                        if(addedDone){
                            new Alert(Alert.AlertType.CONFIRMATION,"New user added successfully !!!").show();

                            ActionEvent event = new ActionEvent();
                            left(event);

                            userName.clear();
                            password.clear();
                            reEnteredPassword.clear();
                        }

                    }else{
                        new Alert(Alert.AlertType.WARNING,"Please re-enter you password !!").show();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"This username already exists !!").show();
                    System.out.println("Already exists");
                }

            }catch(Exception e){
                System.out.println("input miss match !!");
                e.printStackTrace();
            }
        }
        if(!fieldsOK || !comboOK){
            new Alert(Alert.AlertType.ERROR,"Please fill the form correctly").show();
        }
    }

    public static void output(String title,String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
