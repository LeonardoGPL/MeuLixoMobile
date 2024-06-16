package com.example.meulixo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class ExcluirActivity extends AppCompatActivity {

    private Button buttonExcluir,buttonVoltar;
    private FirebaseFirestore db;
    private String docId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir);
        db = FirebaseFirestore.getInstance();
        docId = getIntent().getStringExtra("docId");
        buttonExcluir = findViewById(R.id.buttonExcluir);


        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });


        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
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

    public void dialog(){
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_yn, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(dialogView);

        TextView tituloCustom = dialogView.findViewById(R.id.tituloCustom);
        Button buttonYes = dialogView.findViewById(R.id.buttonYes);
        Button buttonNo = dialogView.findViewById(R.id.buttonNo);

        tituloCustom.setText(" Deseja Realmente Excluir seu Cadastro?");
        buttonYes.setText("SIM");
        buttonNo.setText("NÃO");

        buttonYes.setOnClickListener(v -> {
           excluirLocal();
        });

        buttonNo.setOnClickListener(v -> {
            finish();
        });

        dialog.setCancelable(false);

        dialog.create().show();
    }
}
