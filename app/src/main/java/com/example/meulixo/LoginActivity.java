package com.example.meulixo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextSenha;
    private Button buttonLogin,buttonVoltar;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        db = FirebaseFirestore.getInstance();

        buttonLogin.setOnClickListener(v -> {
            validarUsuario();
        });
    }
    private void validarUsuario() {
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
            Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("locais_descarte")
                .whereEqualTo("email", email)
                .whereEqualTo("senha", senha)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            DocumentSnapshot document = task.getResult().getDocuments().get(0);
                            String docId = document.getId();
                            String nome = document.getString("nome");
                            editTextEmail.setText("");
                            editTextSenha.setText("");
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            intent.putExtra("docId", docId);
                            intent.putExtra("nome", nome);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Email ou senha inválidos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}