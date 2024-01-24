
package com.auth;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hashing {

    public static String hashStringToSHA256(String input) {
        try {
            // Utwórz instancję MessageDigest z algorytmem SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Przekształć wejściowy tekst na tablicę bajtów
            byte[] byteData = input.getBytes();

            // Oblicz hasz
            byte[] hashBytes = md.digest(byteData);

            // Konwersja tablicy bajtów na reprezentację szesnastkową
            StringBuilder hashStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashStringBuilder.append(String.format("%02x", b));
            }

            // Zwróć zahaszowany tekst
            return hashStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            // Obsłuż wyjątek, gdy algorytm nie jest dostępny
            e.printStackTrace();
            return null;
        }
    }
}

