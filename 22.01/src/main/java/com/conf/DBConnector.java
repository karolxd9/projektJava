package com.conf;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Klasa umożliwiająca łączenie z bazą danych
 */
public class DBConnector {
    private static String URL = "jdbc:mysql://h28.seohost.pl/srv63119_platforma_testowa";
    private static String USER = "srv63119_platforma_testowa";
    private static String PASS = "root";

    /**
     * Łączenie z bazą danych
     * @return zwraca ustanowione połączenie
     */
    public static Connection connect(){
        // Zarejestruj sterownik JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Nie można znaleźć sterownika JDBC.");
            e.printStackTrace();
        }

        Connection connection = null;
        try {
           connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Udało się");
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Bład");
        }

        return connection;
    }

    /**
     * Zamyka połączenie z bazą danych
     * @param connection połączenie z bazą danych
     */
    public static void disconnect(Connection connection){
        try{
            connection.close();
            System.out.println("Poprawne zakończenie połączenia z bazą danych.");
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Błąd zakończenia połączenia z bazą danych");
        }
    }

}
