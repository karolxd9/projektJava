
package com.quiz;

public enum Level {
    EASY,
    MEDIUM,
    HARD;

    public String levelOfTask(Level level){
        String levelString = "";
        switch(level){
            case EASY -> {
                levelString = "łatwe";
                break;
            }
            case MEDIUM -> {
                levelString = "średnie";
                break;
            }
            case HARD -> {
                levelString = "trudne";
            }
        }
        return  levelString;
    }
}

