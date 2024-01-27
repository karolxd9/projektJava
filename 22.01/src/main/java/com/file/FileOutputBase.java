package com.file;

import com.conf.GlobalSettings;
import com.conf.QueryExecutor;
import org.w3c.dom.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa ogólna za pomocą, która pomoże w generacji i tworzeniu pliku o danym rozszerzeniu
 */
public class FileOutputBase implements Runnable{

    private String name; //nazwa pliku
    private String path; //nazwa ścieżki, gdzie zapisać plik

    public FileOutputBase(String name,String path){
        this.name = name;
        this.path = path;
    }



    @Override
    public void run() {
        
    }
}
