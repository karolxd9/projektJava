/*

package com.auth;

import com.conf.GlobalSettings;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Register {
    private String first_name;
    private String second_name;

    private String last_name;
    private String login;
    private String password;

    private Socket socket;

    private ArrayList<String>listOfQueries;

    public Register(String first_name,String second_name,String last_name,String login, String password,Socket socket){
        this.first_name = first_name;
        this.second_name = second_name;
        this.last_name = last_name;
        this.login = login;
        this.password = password;
        this.socket = socket;
        this.listOfQueries = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }


*/
/**
     * Sprawdza warunki bezpieczeństwa hasła
     * @param password hasło, które wprowadza użytkownik podczas rejestracji
     * @return wynik weryfikacji proponowanego hasła
     *//*


    public static boolean includePasswordConditions(String password){
        boolean lengthTest = false;
        boolean bigCharacterTest = false;
        boolean specialCharTest = false;

        int passLength = password.length();
        if(passLength>=8 && passLength<=20) lengthTest = true;
        for(int i=0;i<passLength;i++) {
            if (((int) (password.charAt(i)) >= 33 && (int) (password.charAt(i)) <= 64) ||
                    ((int) (password.charAt(i)) >= 123 && (int) (password.charAt(i)) <= 127)) {
                specialCharTest = true;
            }
            if ((int) password.charAt(i) >= 65 && (int) password.charAt(i) <= 90) {
                bigCharacterTest = true;
            }
        }
        if(lengthTest && bigCharacterTest && specialCharTest) return true;
        return false;
    }




*/
/**
     * Sprawdza występowalność wartości w kolumnie
     * @param column nazwa kolumny
     * @param table nazwa tabeli
     * @param username nazwa szukanej wartości
     * @return wynik testu występowalności
     * @throws SQLException
     *//*





*/
/**
     * Sprawdza poprawność warunku loginu
     * @param login proponowany login
     * @return wynik testu poprawności loginu
     *//*


    public static boolean includeLoginConditions(String login) throws SQLException {
        int loginLength = login.length();
        if(loginLength >= 8 && loginLength <=15 && QueryExecutor.lackValue("login","user_login",login,GlobalSettings.socket)) return true;
        return false;
    }




*/
/**
     * Sprawdza poprawność imienia(nazwisko również)
     * @param name imię podane przez użytkownika
     * @return sprawdzenie poprawności imienia
     *//*


    public static boolean includeNameConditions(String name){
        String nameStatrsWithUpperCase = name.replace("\\s","");
        int len = nameStatrsWithUpperCase.length();
        if(len == 0) return false; //Czy imię nie jest puste
        //czy zawiera znak niebędący literą
        for(int i=0;i< len;i++){
            if((int)(nameStatrsWithUpperCase.charAt(i))<97 && (int)(nameStatrsWithUpperCase.charAt(i))>122){
                return false;
            }
        }
        return true;
    }


*/
/**
     * Sprawdza czy wszytsko zostało wprowadzone poprawnie
     * @return wynik weryfikacji wprowadzonych danych
     *//*


    public boolean isOK() throws SQLException {
        boolean loginState = false;
        boolean passState = false;
        boolean firstNameState = false;
        boolean surnameState = false;
        boolean secondNameState = false;
        passState = includePasswordConditions(this.password);
        loginState = includeLoginConditions(this.login);
        firstNameState = includeNameConditions(this.first_name);
        surnameState = includeNameConditions(this.last_name);


        String secondName = this.second_name.replaceAll("\\s","");
        if(secondName == "") secondNameState = true;
        else{
            secondNameState = includeNameConditions(secondName);
        }

        return (loginState && firstNameState && surnameState);
    }
    public synchronized void register() throws SQLException{

            this.listOfQueries.add("INSERT INTO user (first_name, second_name, last_name) VALUES (" + "'" + this.first_name + "','" + this.second_name + "','" + this.last_name + "')");
            DMLHandler dmlHandler = new DMLHandler(GlobalSettings.socket,this.listOfQueries);
            dmlHandler.run();
            ArrayList<String>list = new ArrayList<>();
            String query = "SELECT * FROM user ORDER BY user_id DESC";
            ResultSet r1 = QueryExecutor.result(query,GlobalSettings.socket);
            r1.next();
            int userID = r1.getInt("user_id");
            list.add("INSERT INTO user_login VALUES ("+userID+",'" + this.login + "','" + SHA256Hashing.hashStringToSHA256(this.password) + "')");
            DMLHandler dmlHandler2 = new DMLHandler(GlobalSettings.socket,list);
            dmlHandler2.run();

    }

}
*/
