/*

package com.goncalves.project.controller;


import com.conf.GlobalSettings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private AnchorPane loginAnchor;
    @FXML
    private TextField login_showPassword;
    @FXML
    private TextField loginInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private CheckBox passwordShow;
    @FXML
    private Button loginButton;
    @FXML
    private Button CreateAccount;
    @FXML
    private AnchorPane signupForm;
    @FXML
    private TextField signup_username;
    @FXML
    private TextField signup_name;
    @FXML
    private TextField signup_surname;
    @FXML
    private PasswordField signup_password;
    @FXML
    private PasswordField signup_cpassword;
    private static String username;
    private static String password;
    private static boolean loginStatus;
    private String newPassword="";
    private String cPassword="";


    public static void setLogin(String login) {
        loginController.username = login;
    }


    @FXML
    public void loginToSystem() throws SQLException, IOException {
        this.signupForm.setDisable(true);
        this.signupForm.setVisible(false);
        String username = loginInput.getText();
        String password = passwordInput.getText();
        if(username.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pola");
            alert.setTitle("Puste pola");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();

        }
        else {

            Auth auth = new Auth();


            try {
                loginStatus = auth.login1step(username, password, GlobalSettings.socket);
            } catch (SQLException e) {
                System.out.println("Błąd z bazą danych.");
            }

            if (loginStatus == true) {
                String query = "SELECT * FROM user_login WHERE login = '"+username+"'";
                ResultSet idResult = QueryExecutor.result(query,GlobalSettings.socket);
                idResult.next();

                int currentID = idResult.getInt("user_id");
                SharedData.getInstance().setDane(currentID);
                SharedData.getInstance().setLoginStatus(loginStatus);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quiz_system/views/homeForm.fxml"));
                Parent mainForm = loader.load();
                Scene mainFormScene = new Scene(mainForm);

                // Get the Stage from the current scene
                Stage window = (Stage) loginButton.getScene().getWindow();
                // Set the scene to the stage
                window.setScene(mainFormScene);

                SharedData.getInstance().setStage(window);
                window.show();



            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawne dane logowania");
                alert.setTitle("Niepoprawne dane logowania");
                alert.setContentText("Wprowadź poprawne dane logowania");
                alert.show();
            }

        }

    }

    @FXML
    public void showPassword(){
        if(passwordShow.isSelected()){
            login_showPassword.setText(passwordInput.getText());
            login_showPassword.setVisible(true);
            passwordInput.setVisible(false);
        }
        else{
            login_showPassword.setText(passwordInput.getText());
            login_showPassword.setVisible(false);
            passwordInput.setVisible(true);
        }

    }

    public void accountCreator(){
        this.loginAnchor.setVisible(false);
        this.loginAnchor.setDisable(true);
        this.signupForm.setDisable(false);
        this.signupForm.setVisible(true);
    }

    public void accountCreator2() throws SQLException, IOException {
        String newLogin = this.signup_username.getText();
        String newName = this.signup_name.getText();
        String newSurname = this.signup_surname.getText();
        this.newPassword = this.signup_password.getText();
        this.cPassword = this.signup_cpassword.getText();
        Register newUser = new Register(newName,"",newSurname,newLogin,newPassword,GlobalSettings.socket);
        if((newLogin.isEmpty() || newName.isEmpty() || newSurname.isEmpty() || newPassword==""| cPassword=="")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pola");
            alert.setTitle("Puste pola");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else {
            if (newUser.isOK() && (this.newPassword.equals(this.cPassword))) {
                newUser.register();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawne rejestracja");
                alert.setTitle("Poprawna rejestracja");
                alert.setContentText("Rejestracja przebiegła pomyślnie. Przekierowany zostaniesz do panelu logowania.");
                alert.show();
                returnToLogin();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna rejestracja");
                alert.setTitle("Niepoprawna rejestracja");
                alert.setContentText("Rejestracja nie powiodła się, spróbuj jeszcze raz");
                alert.show();
                this.signup_username.setText("");
                this.signup_name.setText("");
                this.signup_surname.setText("");
                this.signup_password.setText("");
                this.signup_cpassword.setText("");
            }
        }
    }

    public void returnToLogin() throws SQLException, IOException {
        this.signupForm.setDisable(true);
        this.signupForm.setVisible(false);
        this.loginAnchor.setDisable(false);
        this.loginAnchor.setVisible(true);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordShow.setSelected(false);
        this.signupForm.setDisable(true);
        this.signupForm.setVisible(false);
        this.loginAnchor.setDisable(false);
        this.loginAnchor.setVisible(true);
    }
}

*/
