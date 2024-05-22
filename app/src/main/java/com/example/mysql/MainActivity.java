package com.example.mysql;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mysql.DAO.InsertDAO;
import com.example.mysql.DAO.ResultDAO;
import com.example.mysql.DTO.ResultDTO;

public class MainActivity extends AppCompatActivity {
    private EditText bnome, bpreco, bquantidade;
    ResultDTO resultDTO;
    TextView texto ;
    TextView texto2;
    TextView texto3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnome = findViewById(R.id.tnome);
        bpreco = findViewById(R.id.tpreco);
        bquantidade = findViewById(R.id.tquantidade);
    }

    public void insert(View v){
        String axl, axlIns, inserts, paraments, table = "tb_produtos";
        axl = axlIns = inserts = paraments = "?";
        InsertDAO insertDAO = new InsertDAO();
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
            insertDAO.executor(bnome.getText().toString(), bpreco.getText().toString(), bquantidade.getText().toString(), paraments, inserts, table);
        } else {
            showConnectionStatus("COMPLETE OS CAMPOS");
        }
    }

    public void Result(View v) {

    }
    private void showConnectionStatus(String message) {
        runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
