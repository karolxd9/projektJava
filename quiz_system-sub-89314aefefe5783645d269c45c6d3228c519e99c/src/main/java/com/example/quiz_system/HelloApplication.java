package com.example.quiz_system;

import com.auth.Auth;
import com.conf.GlobalSettings;
import com.db.Client;
import com.db.DBServerThread;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class HelloApplication{
    public static void main(String[] args) throws IOException, SQLException {
        Auth auth = new Auth();
        System.out.println(auth.login1step("MariaDBDB","Malut?enki69", GlobalSettings.socket));



    }
}
