package com.auth;

import com.conf.GlobalSettings;
import com.conf.QueryExecutor;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

//klasa odpowiada za poprawną autoryzację użytkownika
public class Auth {

    private String loginFromDB;
    public boolean login1step(String username, String pass, Socket socket) throws SQLException {

        String query = "SELECT * FROM user_login WHERE login="+"'"+username+"'"+
                " AND PASSWORD="+"'"+SHA256Hashing.hashStringToSHA256(pass)+"'";
        System.out.println(query);
        ResultSet result = QueryExecutor.result(query, GlobalSettings.socket);
        if(QueryExecutor.countRows(result) == 1){
            return true;
        }
        return false;

    }



}
