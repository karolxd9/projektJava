/*
package com.quiz;

import com.conf.GlobalSettings;

import java.util.ArrayList;

public class Option {

    private int quiz_id;
    private String content;
    private int taskID;
    private boolean isCorrect;

    public Option(int quiz_id,String content){
        this.quiz_id = quiz_id;
        this.content = content;
    }
    public void addCorretion(boolean isCorrect){
        this.isCorrect = isCorrect;
    }
    public void addTaskID(int id){
        this.taskID = taskID;
    }


    public int getQuiz_id() {
        return quiz_id;
    }

    public String getContent() {
        return content;
    }

    public int getTaskID(){
        return taskID;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void addOptionToDB(){
        String query = "INSERT INTO option(task_id,quiz_id,isCorrect,content) VALUES(";
        String taskIDQuery = "'"+this.taskID+"',";
        String quizIDQuery = "'"+this.quiz_id+"',";
        String isCorrect = (this.getIsCorrect() == true) ? "1" : "0";
        String correctionQuery = "'"+this.getIsCorrect()+"',";
        String contentQuery = "'"+this.getContent()+"')";
        query = query + taskIDQuery + quizIDQuery + isCorrect + "," + contentQuery;
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        System.out.println(query);
        DMLHandler dmlHandler = new DMLHandler(GlobalSettings.socket,list);
        dmlHandler.run();
    }
}
*/
