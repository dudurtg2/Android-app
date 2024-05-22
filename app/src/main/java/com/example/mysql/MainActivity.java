package com.example.mysql;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysql.DAO.ConnectionDAO;
import com.example.mysql.DAO.InsertDAO;
import com.example.mysql.DTO.ResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText bnome, bpreco, bquantidade, blimpar;
    ResultDTO resultDTO;
    ConnectionDAO connectionDAO;
    String name;
    String preso;
    String quantidade;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnome = findViewById(R.id.tnome);
        bpreco = findViewById(R.id.tpreco);
        blimpar = findViewById(R.id.tlimpar);
        bquantidade = findViewById(R.id.tquantidade);
        connectionDAO = new ConnectionDAO();

    }
    public void insert(View v) {
        String nome = "Null", preso = "1", estoque = "1";
        String query;
        String table = "tb_produtos";
        InsertDAO insertDAO = new InsertDAO();
        if (!bnome.getText().toString().isEmpty()) {
            nome = bnome.getText().toString();
        }
        if (!bpreco.getText().toString().isEmpty()) {
            preso =  bpreco.getText().toString();
        }
        if (!bquantidade.getText().toString().isEmpty()) {
            estoque = bquantidade.getText().toString();
        }
        TextView textView = findViewById(R.id.textView);
        query = "INSERT INTO " + table + " (`nome`,`preco`,`estoque`) VALUES (?,?,?)";
        insertDAO.executor(nome,preso,estoque,query);
        textView.setText(query);
        Resulta(v);
    }

    public void deleta(View v) {
        if (!blimpar.getText().toString().isEmpty()) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                String sql = "DELETE FROM `loja`.`tb_produtos` WHERE (`id` = ?)";
                Connection conn = null;
                try {
                    conn = new ConnectionDAO().CONN();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, blimpar.getText().toString());
                    int rowsAffected = stmt.executeUpdate();
                    stmt.close();
                    conn.close();

                    // Notify the user about the result of the deletion
                    runOnUiThread(() -> {
                        if (rowsAffected > 0) {
                            showConnectionStatus("Produto deletado com sucesso!");
                            Resulta(v);
                        } else {
                            showConnectionStatus("Nenhum produto encontrado com o ID fornecido.");
                            Resulta(v);
                        }
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> showConnectionStatus("Erro ao deletar produto."));
                } finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            showConnectionStatus("Erro ao deletar produto.");
        }
    }

//    public void connect(View v) {
//
//            try {
//                ArrayList<ResultDTO> resultDTO1 = new ResultDAO().lista(v);
//            } catch (Exception e){
//                showConnectionStatus(e.getMessage());
//            }
//
//    }

    public void Resulta(View v) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Connection conn = null;
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
                id = bStr4.toString();
                name = bStr.toString();
                preso = bStr2.toString();
                quantidade = bStr3.toString();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            runOnUiThread(()->{
                TextView ida = findViewById(R.id.id);
                ida.setText(id);
                TextView texto = findViewById(R.id.texto);
                texto.setText(name);
                TextView texto2 = findViewById(R.id.texto3);
                texto2.setText(preso);
                TextView texto3 = findViewById(R.id.texto2);
                texto3.setText(quantidade);
            });
        });
    }
    private void showConnectionStatus(String message) {
        runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
