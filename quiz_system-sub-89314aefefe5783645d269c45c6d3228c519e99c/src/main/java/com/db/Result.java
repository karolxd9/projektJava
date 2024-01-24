package com.db;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Result<T> implements Serializable {
    private ResultSet resultSet;
    private ArrayList<T> getData;
    public Result(ResultSet resultSet){
        this.resultSet = resultSet;
        this.getData = new ArrayList<>();
    }
    public ArrayList<T> getData(String Column) throws SQLException {
        while(resultSet.next()){
            getData.add((T) resultSet.getObject(Column));
        }
        return getData;
    }

}
