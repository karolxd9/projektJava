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
    public boolean login1step(String username, String pass, Socket socket) throws SQLException, IOException, ClassNotFoundException {

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

    public static boolean lackValue(String login,Socket socket) throws IOException {
        Client client = null;
        try {
            client = new Client("SELECT * FROM user_login WHERE login = '"+login+"';");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.main();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(client.getResultArray().length == 0){
            return true;
        }
        return false;
    }



}


