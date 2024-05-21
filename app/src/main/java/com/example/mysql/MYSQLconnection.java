package com.example.mysql;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class MYSQLconnection {
    public Connection CONN(){

        String host, port, databaseName, userName, password;
        host = port = databaseName = userName = password = null;
        host = "loja-dudurtg.k.aivencloud.com";
        port = "21136";
        databaseName = "loja";
        userName = "dudu";
        password = "AVNS_iZtwgBxxM8dLTLWa0_z";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);
        } catch (Exception e){
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }

        return connection;
    }
}
