package com.db;

import com.conf.GlobalSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class DBServerThread {
    private static String query = "";
    public void main() {
        try {
            ServerSocket serverSocket = new ServerSocket(7005);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nowe połączenie: " + socket);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DBServerThread.query = bufferedReader.readLine();

                if(DBServerThread.query.startsWith("SELECT")) {
                    Thread thread = new Thread(new ClientHandler(socket));
                    thread.start();
                }
                else{
                    Thread thread = new Thread(new DMLHandler(socket));
                    thread.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;


        }
        @Override
        public void run() {
            try {

                // Pobierz dane z bazy danych
                Connection connection = DriverManager.getConnection("jdbc:mysql://h28.seohost.pl/srv63119_platforma_testowa", "srv63119_platforma_testowa", "root");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(DBServerThread.query);

                if(DBServerThread.query.startsWith("SELECT")){
                // Przekazanie danych do klienta
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                // Przetwarzanie wyników zapytania na listę obiektów
                ArrayList<Object> resultData = new ArrayList<>();
                while (resultSet.next()){
                    // Przykładowe dodawanie do listy (zależy to od struktury tabeli)
                    resultData.add(resultSet.getObject(1));
                }

                outputStream.writeObject(resultData);
}


            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static class DMLHandler implements Runnable {
        private Socket socket;

        public DMLHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://h28.seohost.pl/srv63119_platforma_testowa", "srv63119_platforma_testowa", "root");
                connection.setAutoCommit(false);

                try {
                    String[] queries = DBServerThread.query.split(";");

                    for (String query : queries) {
                        // Ensure the query is not empty or blank
                        if (!query.trim().isEmpty()) {
                            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                                preparedStatement.executeUpdate();
                            }
                        }
                    }

                    connection.commit();
                    System.out.println("Transakcja się powiodła");
                } catch (SQLException ex) {
                    connection.rollback();
                    ex.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}