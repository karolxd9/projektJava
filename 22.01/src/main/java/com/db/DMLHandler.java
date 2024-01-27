package com.db;

import com.conf.QueryExecutor;

import java.net.Socket;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


public class DMLHandler implements Runnable {
    private final Socket clientSocket;
    private final List<String> query; // Poprawione: Użyj interfejsu List zamiast konkretnego typu (ArrayList)

    private QueryExecutor queryExecutor;

    public DMLHandler(Socket clientSocket, List<String> query) { // Poprawione: Użyj interfejsu List
        this.clientSocket = clientSocket;
        this.query = query;
    }

    @Override
    public synchronized void run() {
        Iterator<String> queryIterator = null;
        try {
            queryExecutor = new QueryExecutor();
            queryExecutor.getConnection().setAutoCommit(false);
            queryIterator = this.query.iterator();

            while (queryIterator.hasNext()) { // Poprawione: Użyj hasNext() zamiast for-eacha z next()
                String currentQuery = queryIterator.next();// Poprawione: Wydobądź aktualne zapytanie przed wywołaniem executeSelect
                System.out.println(currentQuery);
                queryExecutor.executeQuery(currentQuery);
            }

            queryExecutor.getConnection().commit();
            System.out.println("Transakcja zakończona pomyślnie");
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if (queryExecutor.getConnection() != null) {
                    queryExecutor.getConnection().rollback();
                    System.out.println("Transakcja wycofana z powodu błędu.");
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                if (queryExecutor != null && queryExecutor.getConnection() != null) {
                    queryExecutor.getConnection().close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
    }
}
