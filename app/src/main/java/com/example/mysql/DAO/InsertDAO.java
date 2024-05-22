package com.example.mysql.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertDAO {
    public void executor(String nome, String preso,String estoque, String query) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Connection conn = null;
            try {
                conn = new ConnectionDAO().CONN();
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nome);
                stmt.setDouble(2, Double.parseDouble(preso));
                stmt.setInt(3, Integer.parseInt(estoque));
                stmt.execute();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
