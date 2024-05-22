package com.example.mysql.DAO;

import com.example.mysql.MYSQLconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertDAO {
    public void executor(String nome, String preso,String estoque, String parament, String inserts, String table) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Connection conn = null;
            try {
                conn = new MYSQLconnection().CONN();
                String query = "INSERT INTO "+table+" ("+inserts+") VALUES ("+parament+")";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nome);
                if (!preso.isEmpty()){ stmt.setDouble(2, Double.parseDouble(preso));}
                if (!estoque.isEmpty()){ stmt.setDouble(3, Integer.parseInt(estoque));}
                stmt.execute();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
