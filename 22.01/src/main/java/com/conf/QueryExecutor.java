package com.conf;

import com.db.ClientHandler;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Klasa umożliwiająca wykonywanie zapytań
 */
public class QueryExecutor {
    private Connection connection; // Zmienna do przechowywania połączenia

    public QueryExecutor() {
        connection = DBConnector.connect(); // Inicjalizacja połączenia w konstruktorze
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Wykonywanie selekcji (zapytań SELECT)
     *
     * @param selectQuery nasze zapytanie select
     * @return wykonane zapytanie selekcji
     */
    public ResultSet executeSelect(String selectQuery) throws RuntimeException {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Błąd zapytania SELECT: " + e.getMessage());
        }
    }

    /**
     * Wykonywanie zapytań modyfikujących stan bazy danych (UPDATE, INSERT, DELETE)
     *
     * @param query zapytanie (inne niż SELECT)
     */
    public void executeQuery(String query) throws RuntimeException {
        try {
            Statement statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Błąd zapytania: " + e.getMessage());
        }

    }

    /**
     * Liczy ilość wierszy po wykonaniu zapytania
     *
     * @param queryResult wynik zapytania
     * @return liczba wierszy
     */
    public static int countRows(ResultSet queryResult) throws SQLException {
        int count = 0;
        try{
        while (queryResult.next()) {
            count++;
        }}
        catch (NullPointerException e){
            System.out.println("NullPointerException");
            return 0;
        }
        return count;
    }

    /**
     * Sprawdzenie czy dana wartość występuje w kolumnie
     *
     * @param column   nazwa kolumny
     * @param table    nazwa tabeli
     * @param username wartość, której występowalność sprawdzamy w kolumnie danej tabeli
     * @param socket   gniazdo sieciowe do komunikacji
     * @return true, jeśli wartość nie występuje, false w przeciwnym razie
     * @throws SQLException
     */
    public static boolean lackValue(String column, String table, String username, Socket socket) throws SQLException {
        int count = 0;
        String query = "SELECT " + column + " FROM " + table + " WHERE " + column + " = '" + username + "'";
        ResultSet resultSet = result(query, socket);
        count = countRows(resultSet);

        return count == 0;
    }

    /**
     * Przetwarzanie danego zapytania selekcji za pomocą klienta
     *
     * @param query  zapytanie selekcji
     * @param socket gniazdo sieciowe do komunikacji
     * @return wynik zapytania selekcji
     */
    public static ResultSet result(String query, Socket socket) {
        ClientHandler clientHandler = new ClientHandler(socket);
        clientHandler.addQuery(query);
        FutureTask<ResultSet> task = new FutureTask<>(clientHandler);
        GlobalSettings.exec.execute(task);
        ResultSet resultSet = null;
        try {
            resultSet = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}


