/*

package com.quiz;

import com.conf.GlobalSettings;

import java.util.ArrayList;

public class Certification {
    private String name;
    private int minPoints;

    public String getName() {
        return name;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public Certification(String name,int minPoints){
        this.name = name;
        this.minPoints = minPoints;
    }

    public void addCertificationToDB(){
        String query = "INSERT INTO certefication('min_points,name') VALUES("+this.getMinPoints()+","+"'"+this.getName()+"')";
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        DMLHandler dmlHandler = new DMLHandler(GlobalSettings.socket,list);
        dmlHandler.run();
    }

}

*/
