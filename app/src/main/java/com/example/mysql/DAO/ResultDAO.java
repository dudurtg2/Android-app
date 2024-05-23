package com.example.mysql.DAO;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.mysql.DTO.ResultDTO;

import com.example.mysql.R;
import com.mysql.jdbc.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultDAO {
    private static String name,preso,quantidade;
    private static ResultDTO resultDTO;
    private ConnectionDAO connectionDAO;
    public ResultDTO connect() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Connection conn;
            connectionDAO = new ConnectionDAO();
            try {
                conn = connectionDAO.CONN();
                String query = "SELECT * FROM tb_produtos";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                StringBuilder bStr = new StringBuilder("NOME:\n");
                StringBuilder bStr2 = new StringBuilder("PREÇO:\n");
                StringBuilder bStr3 = new StringBuilder("QUANTIDADE:\n");
                StringBuilder bStr4 = new StringBuilder("ID:\n");
                while (rs.next()){
                    bStr4.append(rs.getString("id")).append("\n");
                    bStr.append(rs.getString("nome")).append("\n");
                    bStr2.append(rs.getString("preco")).append("\n");
                    bStr3.append(rs.getString("estoque")).append("\n");
                }

                resultDTO = new ResultDTO(bStr.toString(),bStr2.toString(),bStr3.toString(),bStr4.toString());



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
        return resultDTO;
    }

}