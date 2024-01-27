package com.quiz;

import com.conf.GlobalSettings;
import com.conf.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Content {
    /**
     * Lista zadań z quizu
     * @param QuizID numer quizu
     * @return kolekcja zadań z danego quizu
     */
    public ArrayList<Task> getTasksToArrayList(int QuizID){
        ArrayList<Task>results = new ArrayList<>();
        Task quiz = null;
        ResultSet tasks = QueryExecutor.result("SELECT * FROM task WHERE quiz_id = "+QuizID, GlobalSettings.socket);
        try{
            while(tasks.next()){
                String name = tasks.getString("name");
                String header = tasks.getString("header");
                String content = tasks.getString("contant");
                int max_point = tasks.getInt("max_points");
                quiz = new Task(QuizID,header,name,content,max_point);
                results.add(quiz);
            }
        }catch(NullPointerException | SQLException e){
            e.printStackTrace();
        }
        return results;

    }

    /**
     * Możliwe odpowiedzi do quizu
     * @param QuizID numer quizu
     * @return kolekcja odowiedzi do danego quizu
     */
    public ArrayList<Option>getAnswersToArrayList(int QuizID){
        ArrayList<Option>answers = new ArrayList<>();
        try {
            Option optionFormat = null;
            ResultSet answersResult = QueryExecutor.result("SELECT * FROM option WHERE quiz_id = " + QuizID,GlobalSettings.socket);
            while (answersResult.next()) {
                String content = answersResult.getString("content");
            }
        }
        catch (NullPointerException | SQLException e){
            e.printStackTrace();
        }

        return answers;
    }
}
