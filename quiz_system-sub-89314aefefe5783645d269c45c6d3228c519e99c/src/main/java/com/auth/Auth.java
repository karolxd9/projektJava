package com.auth;

import com.conf.GlobalSettings;
import com.db.Client;

import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

//klasa odpowiada za poprawną autoryzację użytkownika
public class Auth {

    private String loginFromDB;
    public boolean login1step(String username, String pass, Socket socket) throws SQLException, IOException {

        String query = "SELECT * FROM user_login WHERE login="+"'"+username+"'"+
                " AND PASSWORD="+"'"+SHA256Hashing.hashStringToSHA256(pass)+"'";
        Object [] result = null;
        Client client = new Client(query);
        client.main();
        result = client.getResultArray();
        if(result == null){
            return false;
        }
        return true;

    }



}


