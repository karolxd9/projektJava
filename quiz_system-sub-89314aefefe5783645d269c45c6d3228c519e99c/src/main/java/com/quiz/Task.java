/*

package com.quiz;

import com.conf.GlobalSettings;

import java.util.ArrayList;

public class Task {
    private int quizID;
    private String header;
    private String name;
    private String content;
    private Type type;
    private int maxPoint;
    private Level level;

    public Task(int quizID,String header,String name,String content,int maxPoint){
        this.quizID = quizID;
        this.header = header;
        this.name = name;
        this.content = content;
        this.maxPoint = maxPoint;
        this.level = null;
    }
    public void addType(Type type){
        this.type = type;
    }
    public void addLevel(Level level){
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public Level getLevel(){
        return this.level;
    }

    public Type getType(){
        return this.type;
    }

    public int getQuizID() {
        return quizID;
    }

    public void addTaskToDB() {
        String query = "INSERT INTO task (quiz_id, name, type, header, contant, max_points, level) VALUES (";
        String quizIDquery = this.getQuizID() + ","; //1
        String nameQuery = "'" + this.name + "',"; //2
        String typeString = this.type.typeOfTask(this.getType());
        String typeQuery = "'" + typeString + "',"; //3
        String headerQuery = "'" + this.getHeader() + "',"; //4
        String contentQuery = "'" + this.getContent() + "',"; //5

        String levelString = this.getLevel().levelOfTask(this.getLevel());
        String levelQuery = "'" + levelString + "')";//6
        query = query + quizIDquery + nameQuery + typeQuery + headerQuery+ contentQuery + this.getMaxPoint()+"," + levelQuery;
        System.out.println(query);
        ArrayList<String> list = new ArrayList<>();
        list.add(query);
        System.out.println(query);
        DMLHandler dmlHandler = new DMLHandler(GlobalSettings.socket, list);
        dmlHandler.run();
    }

}

*/
