package com.example.quiz_system;

import com.conf.SystemInfo;

/*import com.generator.UsersParallelGenerator;*/
import com.db.DBSelectServerThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException{

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/quiz_system/views/loginForm.fxml"));
            Scene scene = new Scene(root);

            stage.setTitle("SYSTEM QUIZÓW");
            stage.setScene(scene);

            stage.show();

        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public static void main() throws SQLException, InterruptedException, UnknownHostException, IOException {
        SystemInfo info = new SystemInfo();
        /*Auth logowanie = new Auth();
        logowanie.login1step("oskareczek","koteczek");
        Register rejestruj = new Register("Karol","Przybycin","oskar12345","Masochista123");
        System.out.println(rejestruj.isOK());*/
        // Socket socket = new Socket("192.168.0.103",6000);


        /*Register newUser = new Register("Maria","","Głaz","MARIADB","C", GlobalSettings.socket);
        newUser.register();*/
        /*Auth firstLoging = new Auth();
        System.out.println(firstLoging.login1step("MARIADB","Malut?enki69",GlobalSettings.socket));
*/
        /*Task task = new Task(15002,"test_header","tester_name","tester_content",69);
        task.addType(Type.CLOSED);
        task.addLevel(Level.HARD);
        task.addTaskToDB();*/

        /*Option option = new Option(15002,"kot");
        option.addCorretion(true);
        option.addTaskID(13);
        option.addOptionToDB();*/
        launch();
    }




}