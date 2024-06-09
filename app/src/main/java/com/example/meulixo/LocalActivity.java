package com.example.meulixo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LocalActivity extends AppCompatActivity {
    Button  buttonVoltar,buttonCadastro,buttonAtualizar,buttonExcluir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
        buttonCadastro = (Button) findViewById(R.id.buttonCadastro);
        buttonCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroActivity.class);
            startActivity(intent);
        });
        buttonAtualizar = (Button) findViewById(R.id.buttonAtualizar);
        buttonAtualizar.setOnClickListener(v -> {
            Intent intent = new Intent(this, AtualizarActivity.class);
            startActivity(intent);
        });
        buttonExcluir = (Button) findViewById(R.id.buttonExcluir);
        buttonExcluir.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExcluirActivity.class);
            startActivity(intent);
        });
    }
}