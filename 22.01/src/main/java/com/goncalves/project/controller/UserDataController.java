package com.goncalves.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDataController {
    @FXML
    private Label dataInfoShow;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dataInfoShow.setText("Napis");
    }
}
