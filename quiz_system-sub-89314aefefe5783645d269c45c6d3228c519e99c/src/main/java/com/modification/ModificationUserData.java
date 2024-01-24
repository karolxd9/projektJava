//
//package com.modification;
//
//import com.auth.Register;
//import com.auth.SHA256Hashing;
//import com.conf.GlobalSettings;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class ModificationUserData {
//    private DMLHandler modificationHandler; //Obsługa DML
//
//
//
///**
//     * Zmiana hasła
//     * @param userID identyfikator użytkownika
//     * @param currentPassword obecne hasło
//     * @param newPassword potencjalne nowe hasło(może nie spełniać zasad tworzenia hasła)
//     * @throws SQLException
//     */
//
//    public void changePassword(int userID,String currentPassword,String newPassword) throws SQLException {
//        QueryExecutor queryExecutor = new QueryExecutor();
//        ResultSet resultSet = queryExecutor.executeSelect("SELECT * FROM user_login WHERE PASSWORD="+"'"+ SHA256Hashing.hashStringToSHA256(currentPassword)+"'");
//        int count = QueryExecutor.countRows(resultSet);
//        if(count == 1){
//            if(Register.includePasswordConditions(newPassword)){
//                ArrayList<String>list = new ArrayList<>();
//                String query = "UPDATE user_login SET PASSWORD="+"'"+SHA256Hashing.hashStringToSHA256(newPassword)+"'"+" WHERE user_id = "+userID;
//                list.add(query);
//                this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
//                this.modificationHandler.run();
//            }
//        }
//    }
//
//
///**
//     * Zmiana loginu
//     * @param userID identyfikator użytkownika
//     * @param newLogin potencjalny nowy login(może nie spełniać kryteriów ten podany przez użytkownika)
//     * @throws SQLException
//     */
//
//    public void changeLogin(int userID,String newLogin) throws SQLException {
//        if(Register.includeLoginConditions(newLogin)){
//            ArrayList<String>list = new ArrayList<>();
//            String query = "UPDATE user_login SET login="+"'"+newLogin+"'"+" WHERE user_id = "+userID;
//            list.add(query);
//            this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
//            this.modificationHandler.run();
//        }
//    }
//
//
///**
//     * Zmiana pierwszego imienia
//     * @param userID identyfikator użytkownika
//     * @param name potencjalne pierwsze imię(przechodzi weryfikację)
//     * @throws SQLException
//     */
//
//    public void changeFirstName(int userID,String name) throws SQLException{
//        if(Register.includeNameConditions(name)) {
//            ArrayList<String> list = new ArrayList<>();
//            String query = "UPDATE user SET first_name=" + "'" + name + "'" + " WHERE user_id = " + userID;
//            list.add(query);
//            this.modificationHandler = new DMLHandler(GlobalSettings.socket, list);
//            this.modificationHandler.run();
//        }
//    }
//
//
///**
//     * Zmiana drugiego imienia
//     * @param userID identyfikator użytkownika
//     * @param name potencjalne drugie imię
//     * @throws SQLException
//     */
//
//    public void changeSecondName(int userID,String name) throws SQLException{
//
//            ArrayList<String> list = new ArrayList<>();
//            String query = "UPDATE user SET second_name=" + "'" + name + "'" + " WHERE user_id = " + userID;
//            list.add(query);
//            this.modificationHandler = new DMLHandler(GlobalSettings.socket, list);
//            this.modificationHandler.run();
//
//    }
//
//
///**
//     * Zmiana nazwiska
//     * @param userID identyfikator użytkownika
//     * @param lastName potencjalne nazwisko(przechodzi weryfikację)
//     * @throws SQLException
//     */
//
//    public void changeSurname(int userID,String lastName) throws SQLException{
//        if(Register.includeNameConditions(lastName)) {
//            ArrayList<String> list = new ArrayList<>();
//            String query = "UPDATE user SET last_name=" + "'" + lastName + "'" + " WHERE user_id = " + userID;
//            list.add(query);
//            this.modificationHandler = new DMLHandler(GlobalSettings.socket, list);
//            this.modificationHandler.run();
//        }
//    }
//
//}
//
