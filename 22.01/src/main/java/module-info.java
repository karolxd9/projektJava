module com.example.quiz_system {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafaker;
    requires com.google.common;

    exports com.goncalves.project.controller;
    opens com.goncalves.project.controller to javafx.fxml;


    opens com.example.quiz_system to javafx.fxml;
    exports com.example.quiz_system;
    exports com.conf;
    opens com.conf to javafx.fxml;

}