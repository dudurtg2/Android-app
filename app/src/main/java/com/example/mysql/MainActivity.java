package com.example.mysql;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.sql.*;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private MYSQLconnection mysqlConnection;
    private Connection conn;
    private TextView texto,texto2,texto3;
    private EditText bnome, bpreco, bquantidade;
    String name, preso, quantidade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysqlConnection = new MYSQLconnection();
        bnome = findViewById(R.id.tnome);
        bpreco = findViewById(R.id.tpreco);
        bquantidade = findViewById(R.id.tquantidade);
    }

    public void inserir(View v){
        String axl, axlIns, inserts, paraments, table = "tb_produtos";
        axl = axlIns = inserts = paraments = "?";


        if (!(bnome.getText().toString()).isEmpty() || !(bpreco.getText().toString()).isEmpty() || !(bquantidade.getText().toString()).isEmpty()) {
            if (!(bnome.getText().toString()).isEmpty()){
                inserts = "nome";
                paraments = "?";
            }
            if (!(bpreco.getText().toString()).isEmpty()) {
                axlIns = inserts;
                inserts = axl + "preso";
                axl = paraments;
                paraments = axl + ",?";
            }
            if (!(bquantidade.getText().toString()).isEmpty()) {
                axlIns = inserts;
                inserts = axl + "estoque";
                axl = paraments;
                paraments = axl + ",?";
            }
            executor(bnome.getText().toString(), bpreco.getText().toString(), bquantidade.getText().toString(), paraments, inserts, table);
        } else {
            showConnectionStatus("COMPLETE OS CAMPOS");
        }


    }
    public void executor(String nome, String preso,String estoque, String parament, String inserts, String table) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                conn = mysqlConnection.CONN();
                String query = "INSERT INTO "+table+" ("+inserts+") VALUES ("+parament+")";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nome);
                if (!(bpreco.getText().toString()).isEmpty()){ stmt.setDouble(2, Double.parseDouble(preso));}
                if (!(bquantidade.getText().toString()).isEmpty()){ stmt.setDouble(3, Integer.parseInt(estoque));}
                stmt.execute();
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
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
    private void showConnectionStatus(String message) {
        runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
