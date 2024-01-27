package com.goncalves.project.controller;

import com.auth.Register;
import com.auth.SHA256Hashing;
import com.conf.GlobalSettings;
import com.conf.QueryExecutor;
import com.db.DMLHandler;
import com.modification.ModificationUserData;
import com.quiz.Task;
import com.quiz.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuBar primaryMenu;
    @FXML
    private StackPane anchorWindow;
    @FXML
    private Button logout_btn;
    @FXML
    private BorderPane EditData;
    @FXML
    private Button changeLoginButton;
    @FXML
    private Button changeNameButton;
    @FXML
    private Button changeSurnameButton;
    @FXML
    private TextField changeLoginField;
    @FXML
    private TextField changeNameField;
    @FXML
    private TextField changeSurnameField;
    @FXML
    private PasswordField currentPassField;
    @FXML
    private PasswordField changePassField;
    @FXML
    private BorderPane editPass;
    @FXML
    private BorderPane quizPane;
    @FXML
    private Button changePassButton;
    @FXML
    private SplitPane quizNameSplit;
    @FXML
    private Button nameQuizButton;
    @FXML
    private TextField nameQuizField;
    @FXML
    private MenuItem toQuizCreator;
    @FXML
    private BorderPane taskCreatorPane;
    @FXML
    private ComboBox typeComboBox;
    @FXML
    private ComboBox levelComboBox;
    @FXML
    private TextField nameTaskField;
    @FXML
    private Spinner maxPointsField;
    @FXML
    private TextArea taskArea;
    @FXML
    private Button submitTaskButton;

    private int ID;
    private boolean loginStatus;
    private Stage newStage;
    private String namaAndSurname = null;
    private ResultSet r1 = null;
    private String login = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        primaryMenu.setMinWidth(anchorWindow.getWidth());
        changeLoginField.requestFocus();
        ID = SharedData.getInstance().getDane();
        loginStatus = SharedData.getInstance().isLoginStatus();
        String dataFromUser = "SELECT * FROM user_login NATURAL JOIN user WHERE user_id = "+ ID;
        System.out.println(dataFromUser);
        r1 = QueryExecutor.result(dataFromUser, GlobalSettings.socket);
        try {
            r1.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            namaAndSurname = r1.getString("first_name") +" "+r1.getString("last_name");
            login = r1.getString("login");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(namaAndSurname);
        disactiveAll();
        this.welcomeLabel.setVisible(true);
        this.welcomeLabel.setText("Witaj, " + namaAndSurname+" !");
        ObservableList<String> options = FXCollections.observableArrayList(
                "zamknięte",
                "otwarte",
                "zamknięte wielokrotnego wyboru"
        );
        ObservableList<String> optionsLevel = FXCollections.observableArrayList(
                "łatwe",
                "średnie",
                "trudne"
        );
        this.typeComboBox.getItems().addAll(options);
        this.levelComboBox.getItems().addAll(optionsLevel);
    }

    public void disactiveAll(){
        this.EditData.setDisable(true);
        this.EditData.setVisible(false);
        this.welcomeLabel.setVisible(false);
        this.editPass.setDisable(true);
        this.editPass.setVisible(false);
        this.quizPane.setDisable(true);
        this.quizPane.setVisible(false);
        this.taskCreatorPane.setVisible(false);
        this.taskCreatorPane.setDisable(true);

    }

    public void logout() throws IOException {
        SharedData.getInstance().setLoginStatus(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quiz_system/views/loginForm.fxml"));
        Parent mainForm = loader.load();
        Scene mainFormScene = new Scene(mainForm);

        // Get the Stage from the current scene
        Stage window = SharedData.getInstance().getNewStage();

        // Set the scene to the stage
        window.setScene(mainFormScene);
        window.show();
    }

    public void showUserInfo() {
        disactiveAll();
        this.welcomeLabel.setVisible(true);
        this.welcomeLabel.setText("Imię i nazwisko: "+this.namaAndSurname+"\n"+"Login: "+login);
    }

    public void changeData(){
        disactiveAll();
        this.EditData.setDisable(false);
        this.EditData.setVisible(true);

    }

    public void changeLogin() throws SQLException {
        String login = changeLoginField.getText();

        if(login.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            ModificationUserData changes = new ModificationUserData();
            if(Register.includeLoginConditions(login)){
                changes.changeLogin(ID,login);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana loginu");
                alert.setTitle("Poprawna zmiana loginu");
                alert.setContentText("Zmiana loginu przebiegła pomyślnie");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmaina loginu");
                alert.setTitle("Niepoprawna zmaina loginu");
                alert.setContentText("Zmiana loginu się nie powiodła");
                alert.show();
            }
        }
    }

    public void changeName(ActionEvent actionEvent) throws SQLException {
        String name = changeNameField.getText();
        if(name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            ModificationUserData changes = new ModificationUserData();
            if(Register.includeNameConditions(name)){
                changes.changeFirstName(ID,name);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana imienia");
                alert.setTitle("Poprawna zmiana imienia");
                alert.setContentText("Zmiana imienia przebiegła pomyślnie");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmaina imienia");
                alert.setTitle("Niepoprawna zmaina imienia");
                alert.setContentText("Zmiana imienia się nie powiodła");
                alert.show();
            }
        }
    }

    public void changeSurname(ActionEvent actionEvent) throws SQLException {
        String surname = changeSurnameField.getText();
        this.editPass.setVisible(false);
        this.editPass.setDisable(true);
        if(surname.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            ModificationUserData changes = new ModificationUserData();
            if(Register.includeNameConditions(surname)){
                try {
                    changes.changeSurname(ID,surname);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana nazwiska");
                alert.setTitle("Poprawna zmiana nazwiska");
                alert.setContentText("Zmiana nazwiska przebiegła pomyślnie");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmaina nazwiska");
                alert.setTitle("Niepoprawna zmaina nazwiska");
                alert.setContentText("Zmiana nazwiska się nie powiodła");
                alert.show();
            }
        }
    }

    public void changePass2() throws SQLException {
        disactiveAll();
        this.editPass.setVisible(true);
        this.editPass.setDisable(false);

        String currentPasswordBeforeChange = r1.getString("PASSWORD");
        String currentPassword = currentPassField.getText();
        String newPassword = changePassField.getText();
        ModificationUserData changePasswordObj = new ModificationUserData();
        if(currentPassword.isEmpty() || newPassword.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else {
            System.out.println(currentPasswordBeforeChange);
            System.out.println(SHA256Hashing.hashStringToSHA256(currentPassword));
            if ((currentPasswordBeforeChange.equals(SHA256Hashing.hashStringToSHA256(currentPassword))) && Register.includePasswordConditions(newPassword) == true ) {
                changePasswordObj.changePassword(ID,currentPassword,newPassword);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana hasła");
                alert.setTitle("Poprawna zmiana hasła");
                alert.setContentText("Zmiana hasło przebiegła poprawnie");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmiana hasła");
                alert.setTitle("Niepoprawna zmiana hasła");
                alert.setContentText("Zmiana hasło nie powiodła się");
                alert.show();
            }
        }


    }

    public void editPassClicked() {
        disactiveAll();
        this.editPass.setVisible(true);
        this.editPass.setDisable(false);
    }

    public void showNameQuizForm(){
        disactiveAll();
        this.quizPane.setVisible(true);
        this.quizPane.setDisable(false);
    }
    public void nameQuizButtonAction(){
        String nameQuiz = nameQuizField.getText();
        if(nameQuiz.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            String query = "INSERT INTO quiz(quiz_name,creator_id) VALUES('"+nameQuiz+"',"+ID+")";
            ArrayList<String>queries = new ArrayList<>();
            queries.add(query);
            DMLHandler insertQuiz = new DMLHandler(GlobalSettings.socket,queries);
            insertQuiz.run();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Dodanie quizu");
            alert.setTitle("Dodanie quizu się powiodło");
            alert.setContentText("Dodanie quizu ukończone pomyślnie");
            alert.show();
            NameQuizButtonAction();
        }
    }
    public void NameQuizButtonAction(){
        disactiveAll();
        this.taskCreatorPane.setDisable(false);
        this.taskCreatorPane.setVisible(true);


    }

    public void createTask(){
        String nameTask = this.nameTaskField.getText();
        int maxPoints = 5;
        String typeTask = (String) this.typeComboBox.getValue();
        String levelTask = (String) this.levelComboBox.getValue();
        String content = this.taskArea.getText();
        if(nameTask.isBlank() || typeTask.isBlank() || levelTask.isBlank() || content.isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            Task newTask = new Task(ID,"",nameTask,content,maxPoints);
            newTask.addTaskToDB();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Dodano poprawnie zadanie");
            alert.setTitle("DOdano poprawnie zadanie");
            alert.setContentText("Zadanie zostało dodane poprawnie");
            alert.show();
        }
    }


}
