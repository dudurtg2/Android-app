package com.example.mysql.DAO;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.mysql.DTO.ResultDTO;
import com.example.mysql.MYSQLconnection;
import com.example.mysql.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultDAO {

    public void connect(View v) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                conn = mysqlConnection.CONN();
                String query = "SELECT * FROM tb_produtos";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                StringBuilder bStr = new StringBuilder("NOME:\n");
                StringBuilder bStr2 = new StringBuilder("PREÇO:\n");
                StringBuilder bStr3 = new StringBuilder("QUANTIDADE:\n");
                while (rs.next()){
                    bStr.append(rs.getString("nome")).append("\n");
                    bStr2.append(rs.getString("preco")).append("\n");
                    bStr3.append(rs.getString("estoque")).append("\n");
                }
                name = bStr.toString();
                preso = bStr2.toString();
                quantidade = bStr3.toString();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            runOnUiThread(()->{
                texto = findViewById(R.id.texto);
                texto.setText(name);
                texto2 = findViewById(R.id.texto3);
                texto2.setText(preso);
                texto3 = findViewById(R.id.texto2);
                texto3.setText(quantidade);
            });
        });
    }

}
