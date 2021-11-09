package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class registrationController {

    @FXML
    private JFXButton Settings;

    @FXML
    private JFXButton GoBack;

    @FXML
    private JFXTextField studentName;

    @FXML
    private JFXButton databaseBtn;

    @FXML
    private JFXTextField date;

    @FXML
    private JFXTextField mail;

    @FXML
    private JFXRadioButton GMale;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXRadioButton GFemale;

    @FXML
    private Button Next;

    @FXML
    private Button Quit;

    @FXML
    private JFXTextField tel;

    @FXML
    private JFXComboBox<?> month;

    @FXML
    private JFXComboBox<?> year;

    @FXML
    private JFXTextField idNo;

    @FXML
    void BtnBack(ActionEvent event) {

        Stage stage = (Stage) GoBack.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Menu Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void BtnDatabase(ActionEvent event) {

        Stage stage = (Stage) databaseBtn.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/view/studentView.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/LoginPac/Login2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Settings Window");
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void ButtonQuit(ActionEvent event) {

        studentName.clear();
        date.clear();
        month.getItems().clear();
        year.getItems().clear();
        mail.clear();
        tel.clear();
        idNo.clear();

    }

    public static String StudentName = null;
    public static String BornDate = null;
    public static String BornMonth = null;
    public static String BornYear = null;
    public static String gender = null;
    public static String email = null;
    public static int telNo = 0;
    public static String idNumber = null;

    @FXML
    void ButtonNext(ActionEvent event) {
        try {
            validation(studentName,date,month,year,GMale,GFemale,mail,tel,idNo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurs in user inputs");
        }
    }


    //============================================= functions ==============================================================================

    private void validation(TextField Tf1, TextField Tf2 , JFXComboBox<?> combo1, JFXComboBox<?> combo2, RadioButton rb1, RadioButton rb2,
                            TextField Tf3, TextField Tf4, TextField Tf5) throws Exception{

        boolean ok = ( !combo1.getSelectionModel().isEmpty() && !combo2.getSelectionModel().isEmpty() );// ok --> Combo box is not empty
        boolean Fok = !(Tf1.getText().trim().isEmpty()) && !(Tf2.getText().trim().isEmpty()) && !(Tf3.getText().trim().isEmpty()) &&
                !(Tf4.getText().trim().isEmpty()) && !(Tf5.getText().trim().isEmpty()); // Both Fields are not empty
        boolean rbClicked = rb1.isSelected() || rb2.isSelected() ;

        if(ok && Fok && rbClicked){
            String IdNo = Tf5.getText();
            int IdSize = IdNo.length();
            boolean idLength = (IdSize >= 8) ;
            if(idLength){
                output("Wrong Input","Please use less than 8 digits for the Student's Id Number");
            }

            boolean statement = Integer.parseInt(Tf2.getText()) < 0 || Integer.parseInt(Tf2.getText()) > 31 ;
            if(statement){
                output("Wrong Input","Hey you Alien...Please insert real date of birth.");
            }

            String str = Tf3.getText();
            boolean sign1 = str.contains("@");
            if(!sign1){
                output("Wrong Input","Invalid Email. Please check again '@' is missing.");
            }
            boolean sign2 = str.contains(".");
            if(!sign2){
                output("Wrong Input","Invalid Email. Please check again '.' is missing.");
            }

            int numberWord = Tf4.getText().length();
            boolean rangeOfTelNo = ( numberWord != 10 );
            try{
                int numbers = Integer.parseInt(Tf4.getText());
                if(rangeOfTelNo){
                    output("Wrong Input","Check the size of the telephone number again.");
                }
            }catch(Exception e){
                output("Wrong Input","Number type is wrong. Please check again.");
            }
            if(!statement && sign1 && sign2 && !rangeOfTelNo && !idLength){

                StudentName = Tf1.getText();
                BornDate = Tf2.getText();
                BornMonth = (String) combo1.getSelectionModel().getSelectedItem();
                BornYear = (String) combo2.getSelectionModel().getSelectedItem();
                email = Tf3.getText();
                telNo = Integer.parseInt(Tf4.getText());
                idNumber = Tf5.getText();

                //----------------------------------------------------

                Stage stage = (Stage) Next.getScene().getWindow();
                stage.close();

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/view/payment.fxml"));

                primaryStage.setTitle("Menu Window");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }
        if(!ok || !Fok || !rbClicked){
            output("Error","Please fill each component correctly for continue the payment session.");
        }
    }

    private static void output(String title,String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
