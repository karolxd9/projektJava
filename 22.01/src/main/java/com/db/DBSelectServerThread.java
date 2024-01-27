package com.db;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

//serwer do współbieżnej obsługi zapytań selekcji bazy danych
public class DBSelectServerThread {
    private Socket socket;

    private int port;
    private String query;

    private int howManyThread;
    private PrintWriter output;
    ExecutorService executor;
    public DBSelectServerThread(){
        this.port = 7000;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    /**
     * Świadczy usługę dostarczania zasobów z zapytania
     */
    public void main() {
        try (ServerSocket serverSocket = new ServerSocket(this.getPort())) {
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączono z klientem " + clientSocket.getInetAddress());
                clientSocket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}