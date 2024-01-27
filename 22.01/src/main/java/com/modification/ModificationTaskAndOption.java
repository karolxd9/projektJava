package com.modification;

import com.conf.GlobalSettings;
import com.db.DMLHandler;
import com.quiz.Type;

import java.util.ArrayList;

public class ModificationTaskAndOption {


    /**
     * Usunięcie odpowiedzi
     * @param optionID identryfikator odpowiedzi
     */
    public void deleteOption(int optionID){
        ArrayList<String> list = new ArrayList<>();
        String query = "DELETE FROM option WHERE option_id = "+optionID;
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Usunięcie zadania
     * @param task_id identyfikator zadania
     */
    public void deleteTask(int task_id){
        ArrayList<String> list = new ArrayList<>();
        String queryOption = "DELETE FROM option WHERE task_id = "+task_id;
        String queryTask = "DELETE FROM task WHERE task_id = "+task_id;
        list.add(queryOption);
        list.add(queryTask);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana przynależności quizu do zadania
     * @param taskID identyfikator zadania
     * @param quizID identyfikator quizu
     */
    public void changeQuizIDForTask(int taskID, int quizID){
        ArrayList<String>list = new ArrayList<>();
        String query = "UPDATE task quiz_id = "+quizID+" WHERE taskID = "+taskID;
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana nazwy zadania
     * @param task_id identyfikator zadania
     * @param taskName nazwa zadania
     */
    public void changeTaskName(int task_id,String taskName){
        ArrayList<String>list = new ArrayList<>();
        String query = "UPDATE task SET name = "+"'"+taskName+"' WHERE task_id = "+task_id;
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana typu zadania
     * @param task_id identyfikator zadania
     * @param type typ zadania
     */
    public void changeType(int task_id, Type type){
        String typeString = type.typeOfTask(type);
        ArrayList<String>list = new ArrayList<>();
        String query = "UPDATE task SET type = '"+typeString+"' WHERE task_id = "+task_id;
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana nagłówka zadania
     * @param taskID identyfikator zadania
     * @param header tekst nagłówka
     */
    public void changeHeader(int taskID,String header){
        String query = "UPDATE task SET header = '"+header+"' WHERE task_id = "+taskID;
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana treści zadania
     * @param taskID identyfikator zadania
     * @param content treść zadania
     */
    public void changeContent(int taskID,String content){
        String query = "UPDATE task SET contant= '"+content+"' WHERE task_id = "+taskID;
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana maksymalnej możliwej liczby punktów za dane zadanie
     * @param taskID identyfikator zadania
     * @param maxPoints maksymalna możliwa liczba punktów za dane zadanie
     */
    public void changeMaxPoints(int taskID,String maxPoints){
        String query = "UPDATE task SET max_points = "+maxPoints+" WHERE task_id = "+taskID;
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana przynależności zadania dla opcji wyboru
     * @param optionID identyfikator opcji wyboru
     * @param taskID nowy identyfikator zadania
     */
    public void changeTaskIDForOption(int optionID,int taskID){
        String query = "UPDATE option SET task_id = "+taskID+" WHERE option_id = "+optionID;
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }

    /**
     * Zmiana poprawności odpowiedzi
     * @param option_id identyfikator odpowiedzi
     * @param isCorrect stan poprawności odpowiedzi
     */
    public void changeCorrection(int option_id, boolean isCorrect){
        int isCorrectNumber = 0;
        if(isCorrect) isCorrectNumber = 1;
        String query = "UPDATE option SET isCorrect = "+ isCorrectNumber +" WHERE option_id = "+option_id;
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        DMLHandler handler = new DMLHandler(GlobalSettings.socket,list);
        handler.run();
    }



}
