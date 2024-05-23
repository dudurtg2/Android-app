package com.example.mysql;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysql.DAO.ConnectionDAO;
import com.example.mysql.DAO.InsertDAO;
import com.example.mysql.DAO.ResultDAO;
import com.example.mysql.DTO.ResultDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private EditText bnome, bpreco, bquantidade, blimpar;
    private TextView idd;
    private TextView texto;
    private TextView texto2;
    private TextView texto3;
    TextView textView;
    ResultDTO resultDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnome = findViewById(R.id.tnome);
        bpreco = findViewById(R.id.tpreco);
        blimpar = findViewById(R.id.tlimpar);
        bquantidade = findViewById(R.id.tquantidade);
        idd = findViewById(R.id.id);
        texto = findViewById(R.id.texto);
        texto2 = findViewById(R.id.texto3);
        texto3 = findViewById(R.id.texto2);
        textView = findViewById(R.id.textView);
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

    public void Resulta(View v) {
            try {
                resultDTO = new ResultDAO().connect();
                idd.setText(ResultDTO.getIdd());
                texto.setText(resultDTO.getNome());
                texto2.setText(resultDTO.getPreso());
                texto3.setText(resultDTO.getEstoque());
            } catch (Exception e){
                textView.setText(e.getMessage());
            }

    }

    private void showConnectionStatus(String message) {
        runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
