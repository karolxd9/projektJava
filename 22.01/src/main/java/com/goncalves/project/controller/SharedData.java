package com.goncalves.project.controller;

import javafx.stage.Stage;

public class SharedData {
    private static SharedData instance = null;
    private static boolean loginStatus = false;
    private static Stage newStage = null;

    private int dane;

    private SharedData() {
        // prywatny konstruktor
    }

    public static synchronized SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public int getDane() {
        return dane;
    }

    public void setDane(int dane) {
        this.dane = dane;
    }

    public static boolean isLoginStatus() {
        return loginStatus;
    }

    public static void setLoginStatus(boolean loginStatus) {
        SharedData.loginStatus = loginStatus;
    }

    public static void setStage(Stage stage){
        newStage = stage;
    }

    public static Stage getNewStage(){
        return newStage;
    }
}

