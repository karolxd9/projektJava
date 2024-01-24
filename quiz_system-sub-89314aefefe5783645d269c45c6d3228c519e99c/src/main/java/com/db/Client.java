package com.db;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.conf.GlobalSettings;

public class Client {
    private String query;
    private Socket socket;
    private Object [] resultArray;

    public Client(String query) throws IOException {
        this.query = query;
        this.socket = GlobalSettings.socket;
    }

    public String getQuery() {
        return this.query;
    }

    public Object [] getResultArray(){
        return this.resultArray;
    }

    public void main() {
        try {

            //wyśyłanie danych do serwera
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);


            printWriter.println(this.query);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //odbierz dane od serwera
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            try {
                ArrayList<Object> receivedData = (ArrayList<Object>) inputStream.readObject();
                System.out.println("Otrzymano dane: " + receivedData.toString());
                resultArray = receivedData.toArray();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void sendQuery() throws IOException {

        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        // Wyślij zapytanie do serwera
        printWriter.println(this.query);


    }

    public void receiveResponse() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Odbierz dane od serwera
            try {
                ArrayList<Object> receivedData = (ArrayList<Object>) inputStream.readObject();
                System.out.println("Otrzymano dane: " + receivedData.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}