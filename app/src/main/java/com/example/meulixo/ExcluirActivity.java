package com.example.meulixo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class ExcluirActivity extends AppCompatActivity {

    private EditText editTextNome,editTextEmail, editTextSenha;
    private Button buttonExcluir;
    private FirebaseFirestore db;
    private String docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonExcluir = findViewById(R.id.buttonExcluir);

        db = FirebaseFirestore.getInstance();

        docId = getIntent().getStringExtra("docId");
        editTextNome.setText(getIntent().getStringExtra("nome"));
        editTextEmail.setText(getIntent().getStringExtra("email"));
        editTextSenha.setText(getIntent().getStringExtra("senha"));

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirLocal();
            }
        });
    }
    private void excluirLocal() {
        db.collection("locais_descarte").document(docId)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ExcluirActivity.this, "Local excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ExcluirActivity.this, "Erro ao excluir local.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}