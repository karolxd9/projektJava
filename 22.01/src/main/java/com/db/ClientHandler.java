package com.db;

import com.conf.QueryExecutor;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

//klasa wspomagająca współbieżne odpytywanie serwera o wynik zapytania selekcji
public class ClientHandler implements Callable {

    private Socket clientSocket;
    private String query;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public String addQuery(String query){
        this.query = query;
        return query;
    }


    /**
     * Klient odpytujący serwer o wyniki zapytania selekcji
     * @return wynik zapytania
     */
    @Override
    public ResultSet call(){
        try{
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            QueryExecutor queryExecutor = new QueryExecutor();
            ResultSet resultSet = queryExecutor.executeSelect(this.query);
            return resultSet;
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}