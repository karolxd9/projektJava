package com.quiz;

public enum Type {
    OPENED,
    CLOSED,
    CLOSED_MULTI_CHOICE;

    public String typeOfTask(Type type){
        String typeString = "";
        switch (type){
            case CLOSED -> {
                typeString = "zamknięte";
                break;
            }

            case OPENED -> {
                typeString = "otwarte";
                break;
            }

            case CLOSED_MULTI_CHOICE -> {
                typeString = "zamknięte wielokrotnego wyboru";
            }
        }
        return typeString;
    }
}
