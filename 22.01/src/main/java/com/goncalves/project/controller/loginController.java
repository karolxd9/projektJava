package com.goncalves.project.controller;

import com.auth.Auth;
import com.auth.Register;
import com.conf.GlobalSettings;

import com.conf.QueryExecutor;
import com.example.quiz_system.HelloApplication;
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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
    @FXML
    private Button switchlanguagebutton;
    @FXML
    private Label registerHyperlink;
    @FXML
    private Label nameuser;
    @FXML
    private Label userofpass;
    @FXML
    private Label loginpanel;
    @FXML
    private Label pokazpass;
    @FXML
    private Label regkonto;
    @FXML
    private Label usernamereg;
    @FXML
    private Label namereg;
    @FXML
    private Label surnamereg;
    @FXML
    private Label passreg;
    @FXML
    private Label  passrepreg;
    @FXML
    private Button registerUserButton;
    @FXML
    private Label logreg;
    @FXML
    private Button signup_loginAccount;
    private static String username;
    private static String password;
    private static boolean loginStatus;
    private String newPassword="";
    private String cPassword="";

    private ResourceBundle resourceBundle;
    private List<Locale> supportedLocales;
    private int currentLocaleIndex;

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
            alert.setHeaderText(resourceBundle.getString("emptyFields.header"));
            alert.setTitle(resourceBundle.getString("emptyFields.header"));
            alert.setContentText(resourceBundle.getString("emptyFields.content"));
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
                alert.setHeaderText(resourceBundle.getString("erorFieldslog.text"));
                alert.setTitle(resourceBundle.getString("erorFieldslog.text"));
                alert.setContentText(resourceBundle.getString("erorlog.text"));
                alert.show();
            }

        }

    }

    public void initialize() {
        // Domyślnie używaj pliku zasobów w języku angielskim
        resourceBundle = ResourceBundle.getBundle("com.example.quiz_system.views.Messages_pl");
        updateText();

    }

    @FXML
    protected void clicktoswitchlanguage() {
        // Przy naciśnięciu przycisku zmień język
        switchLocale();
        updateText();
    }


    private void switchLocale() {
        // Sprawdź, czy supportedLocales zostało zainicjowane
        if (supportedLocales == null) {
            // Zainicjuj supportedLocales, jeśli jeszcze nie zostało zrobione
            supportedLocales = Arrays.asList(
                    new Locale("pl"),
                    new Locale("uk")

            );
            currentLocaleIndex = 0;
        }
        // Przełącz na następną lokalizację
        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
        Locale newLocale = supportedLocales.get(currentLocaleIndex);
        System.out.println("Switching to Locale: " + newLocale);
        resourceBundle = ResourceBundle.getBundle("com.example.quiz_system.views.Messages", newLocale);
    }


    private void updateText() {
        // Pobierz tekst z pliku zasobów i ustaw go na etykiecie
        //Panel logowania
        switchlanguagebutton.setText(resourceBundle.getString("switchlanguage.text"));
        nameuser.setText(resourceBundle.getString("nameuser.text"));
        userofpass.setText(resourceBundle.getString("userofpass.text"));
        loginButton.setText(resourceBundle.getString("loginButton.text"));
        registerHyperlink.setText(resourceBundle.getString("registerHyperlink.text"));
        CreateAccount.setText(resourceBundle.getString("CreateAccount.text"));
        loginpanel.setText(resourceBundle.getString("loginpanel.text"));
        pokazpass.setText(resourceBundle.getString("pokazpass.text"));

        //Panel rejestracji
        regkonto.setText(resourceBundle.getString("regkonto.text"));
        usernamereg.setText(resourceBundle.getString("usernamereg.text"));
        namereg.setText(resourceBundle.getString("namereg.text"));
        surnamereg.setText(resourceBundle.getString("surnamereg.text"));
        passreg.setText(resourceBundle.getString("passreg.text"));
        passrepreg.setText(resourceBundle.getString("passrepreg.text"));
        registerUserButton.setText(resourceBundle.getString("registerUserButton.text"));
        logreg.setText(resourceBundle.getString("logreg.text"));
        signup_loginAccount.setText(resourceBundle.getString("signup_loginAccount.text"));
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
            alert.setHeaderText(resourceBundle.getString("emptyFields.header"));
            alert.setTitle(resourceBundle.getString("emptyFields.header"));
            alert.setContentText(resourceBundle.getString("emptyFields.content"));
            alert.show();
        }
        else {
            if (newUser.isOK() && (this.newPassword.equals(this.cPassword))) {
                newUser.register();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(resourceBundle.getString("eror.text"));
                alert.setTitle(resourceBundle.getString("eror.text"));
                alert.setContentText(resourceBundle.getString("erorreg.text"));
                alert.show();
                returnToLogin();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(resourceBundle.getString("erorFields.text"));
                alert.setTitle(resourceBundle.getString("erorFields.text"));
                alert.setContentText(resourceBundle.getString("erorFieldsreg.text"));
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
