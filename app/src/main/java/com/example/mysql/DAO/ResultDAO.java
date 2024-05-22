//package com.example.mysql.DAO;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.View;
//import android.widget.TextView;
//
//import com.example.mysql.DTO.ResultDTO;
//import com.example.mysql.R;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class ResultDAO {
//    public ArrayList<ResultDTO> lista(View v) {
//
//        Connection conn = null;
//        String sql = "SELECT * FROM tb_produtos";
//        ArrayList<ResultDTO> produtos = new ArrayList<ResultDTO>();
//        ResultSet rs;
//        conn = new ConnectionDAO().CONN();
//        try {
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                ResultDTO produto = new ResultDTO(rs.getString("nome"), rs.getString("preco"), rs.getString("estoque"));
//                produtos.add(produto);
//            }
//            stmt.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return produtos;
//    }
//}

