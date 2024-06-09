package com.example.meulixo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonDescartar, buttonLocalDescarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDescartar = (Button) findViewById(R.id.buttonDescartar);
        buttonLocalDescarte = (Button) findViewById(R.id.buttonLocalDescarte);
        buttonDescartar.setOnClickListener(v -> {
            Intent descarte = new Intent(MainActivity.this, DescarteActivity.class);
            startActivity(descarte);
        });
        buttonLocalDescarte.setOnClickListener(v -> {
            Intent localDescarte = new Intent(this, LocalActivity.class);
            startActivity(localDescarte);
        });


    }

}