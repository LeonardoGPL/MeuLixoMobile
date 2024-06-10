package com.example.meulixo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtualizarActivity extends AppCompatActivity {

    EditText editTextNome,editTextEmail,editTextTelefone,editTextRua,editTextNumero,editTextCep,editTextCidade;

    CheckBox checkBoxEletronico,checkBoxPilha,checkBoxVestuario,checkBoxMedicamentos,checkBoxMoveis,checkBoxOleo,checkBoxPlastico,checkBoxEletrodomesticos;
    private Button buttonAtualizar, buttonVoltar;
    private FirebaseFirestore db;
    private String docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextRua = findViewById(R.id.editTextRua);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextCep = findViewById(R.id.editTextCep);
        editTextCidade = findViewById(R.id.editTextCidade);

        checkBoxEletronico = findViewById(R.id.checkBoxEletronico);
        checkBoxPilha = findViewById(R.id.checkBoxPilha);
        checkBoxVestuario = findViewById(R.id.checkBoxVestuario);
        checkBoxMedicamentos = findViewById(R.id.checkBoxMedicamentos);
        checkBoxMoveis = findViewById(R.id.checkBoxMoveis);
        checkBoxOleo = findViewById(R.id.checkBoxOleo);
        checkBoxPlastico = findViewById(R.id.checkBoxPlastico);
        checkBoxEletrodomesticos = findViewById(R.id.checkBoxEletrodomesticos);

        buttonAtualizar = findViewById(R.id.buttonAtualizar);

        db = FirebaseFirestore.getInstance();

        docId = getIntent().getStringExtra("docId");

        pushLocais();

        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarLocal();
            }
        });
    }
    private void pushLocais() {
        db.collection("locais_descarte").document(docId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    editTextNome.setText(documentSnapshot.getString("nome"));
                    editTextEmail.setText(documentSnapshot.getString("email"));
                    editTextTelefone.setText(documentSnapshot.getString("telefone"));
                    editTextRua.setText(documentSnapshot.getString("rua"));
                    editTextNumero.setText(documentSnapshot.getString("numero"));
                    editTextCep.setText(documentSnapshot.getString("cep"));
                    editTextCidade.setText(documentSnapshot.getString("cidade"));
                    List<String> categorias = (List<String>) documentSnapshot.get("categorias");

                    if (categorias != null) {
                        checkBoxEletronico.setChecked(categorias.contains("Eletrônicos"));
                        checkBoxPilha.setChecked(categorias.contains("Pilhas e Baterias"));
                        checkBoxVestuario.setChecked(categorias.contains("Vestuario"));
                        checkBoxMedicamentos.setChecked(categorias.contains("Medicamentos"));
                        checkBoxMoveis.setChecked(categorias.contains("Móveis"));
                        checkBoxOleo.setChecked(categorias.contains("Óleos"));
                        checkBoxPlastico.setChecked(categorias.contains("Plástico"));
                        checkBoxEletrodomesticos.setChecked(categorias.contains("Eletrodomésticos"));
                    }
                }
            }
        });
    }
    private void atualizarLocal() {
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String rua = editTextRua.getText().toString();
        String numero = editTextNumero.getText().toString();
        String cep = editTextCep.getText().toString();
        String cidade = editTextCidade.getText().toString();

        List<String> categorias = new ArrayList<>();
        if (checkBoxEletronico.isChecked()) categorias.add("Eletrônicos");
        if (checkBoxPilha.isChecked()) categorias.add("Pilhas e Baterias");
        if (checkBoxVestuario.isChecked()) categorias.add("Vestuario");
        if (checkBoxMedicamentos.isChecked()) categorias.add("Medicamentos");
        if (checkBoxMoveis.isChecked()) categorias.add("Móveis");
        if (checkBoxOleo.isChecked()) categorias.add("Óleos");
        if (checkBoxPlastico.isChecked()) categorias.add("Plástico");
        if (checkBoxEletrodomesticos.isChecked()) categorias.add("Eletrodomésticos");

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(telefone) || TextUtils.isEmpty(rua) || TextUtils.isEmpty(numero) || TextUtils.isEmpty(cep) || TextUtils.isEmpty(cidade) || categorias.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> localDescarte = new HashMap<>();
        localDescarte.put("nome", nome);
        localDescarte.put("telefone", telefone);
        localDescarte.put("rua", rua);
        localDescarte.put("numero", numero);
        localDescarte.put("cep", cep);
        localDescarte.put("cidade", cidade);
        localDescarte.put("categorias", categorias);

        db.collection("locais_descarte").document(docId)
                .set(localDescarte, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AtualizarActivity.this, "Local atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AtualizarActivity.this, "Erro ao atualizar local.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}