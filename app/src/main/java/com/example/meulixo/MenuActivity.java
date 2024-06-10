package com.example.meulixo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private Button buttonAtualizar,buttonExcluir,buttonVoltar;

    private String docId, nome;
    private TextView textViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        docId = getIntent().getStringExtra("docId");
        textViewUser = findViewById(R.id.textViewUser);
        nome = getIntent().getStringExtra("nome");
        textViewUser.setText("Bem-vindo, " + nome);

        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
        buttonAtualizar = (Button) findViewById(R.id.buttonAtualizar);
        buttonAtualizar.setOnClickListener(v -> {
            Intent intent = new Intent(this, AtualizarActivity.class);
            intent.putExtra("docId", docId);
            startActivity(intent);
        });

        buttonExcluir = (Button) findViewById(R.id.buttonExcluir);
        buttonExcluir.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExcluirActivity.class);
            intent.putExtra("docId", docId);
            startActivity(intent);
        });

    }
}
