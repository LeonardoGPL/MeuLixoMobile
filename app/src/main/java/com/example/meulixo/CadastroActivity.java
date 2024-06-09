package com.example.meulixo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class CadastroActivity extends AppCompatActivity {
 Button buttonVoltar,buttonProximo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonProximo = (Button) findViewById(R.id.buttonProximo);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
        buttonProximo.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroMActivity.class);
            startActivity(intent);
        });

    }
}