package com.example.meulixo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroMActivity extends AppCompatActivity {
    Spinner Spinner;
    Button buttonVoltar,buttonEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mactivity);
        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
        Spinner = (Spinner) findViewById(R.id.spinner);
        String[] options = {"Selecione uma Categoria","Eletrônicos", "pilhas e baterias", "Vestuario","Medicamentos","Móveis","Óleos","Plásticos","Eletrodomésticos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(adapter);
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String)
                        parent.getItemAtPosition(1);
                Toast.makeText(getApplicationContext(),"Selecione uma Categoria",Toast.LENGTH_SHORT);
                parent.getItemAtPosition(2);
                   Toast.makeText(getApplicationContext(), "Categoria Eletrônicos", Toast.LENGTH_SHORT);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"ErRo0!",Toast.LENGTH_SHORT);
            }
        });
        buttonEnviar.setOnClickListener(v -> {
            AlertDialog.Builder confirmarEnvio = new AlertDialog.Builder(this);
            confirmarEnvio.setTitle("Cadastro");
            confirmarEnvio.setMessage("Usuario Cadastrado com Sucesso!");
            confirmarEnvio.setCancelable(false);
            confirmarEnvio.setPositiveButton("Ok", null);
            confirmarEnvio.show();
        });
    }
}

