package com.example.meulixo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.meulixo.models.LocalDescarte;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth auth;
 Button buttonVoltar,buttonEnviar;

 EditText editTextNome,editTextEmail,editTextTelefone,editTextRua,editTextNumero,editTextCep,editTextCidade,editTextSenha;;

 CheckBox checkBoxEletronico,checkBoxPilha,checkBoxVestuario,checkBoxMedicamentos,checkBoxMoveis,checkBoxOleo,checkBoxPlastico,checkBoxEletrodomesticos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
        auth = FirebaseAuth.getInstance();
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextRua = findViewById(R.id.editTextRua);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextCep = findViewById(R.id.editTextCep);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextSenha = findViewById(R.id.editTextSenha);

        checkBoxEletronico = findViewById(R.id.checkBoxEletronico);
        checkBoxPilha = findViewById(R.id.checkBoxPilha);
        checkBoxVestuario = findViewById(R.id.checkBoxVestuario);
        checkBoxMedicamentos = findViewById(R.id.checkBoxMedicamentos);
        checkBoxMoveis = findViewById(R.id.checkBoxMoveis);
        checkBoxOleo = findViewById(R.id.checkBoxOleo);
        checkBoxPlastico = findViewById(R.id.checkBoxPlastico);
        checkBoxEletrodomesticos = findViewById(R.id.checkBoxEletrodomesticos);

        buttonEnviar = findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarLocalDescarte();
            }
        });
    }

    private void salvarLocalDescarte() {
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String rua = editTextRua.getText().toString();
        String numero = editTextNumero.getText().toString();
        String cep = editTextCep.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String senha = editTextSenha.getText().toString();

        List<String> categorias = new ArrayList<>();
        if (checkBoxEletronico.isChecked()) categorias.add("Eletrônicos");
        if (checkBoxPilha.isChecked()) categorias.add("Pilhas e Baterias");
        if (checkBoxMedicamentos.isChecked()) categorias.add("Medicamentos");
        if (checkBoxMoveis.isChecked()) categorias.add("Móveis");
        if (checkBoxOleo.isChecked()) categorias.add("Óleos");
        if (checkBoxPlastico.isChecked()) categorias.add("Plástico");
        if (checkBoxEletrodomesticos.isChecked()) categorias.add("Eletrodomésticos");

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(email) || TextUtils.isEmpty(telefone) || TextUtils.isEmpty(rua) || TextUtils.isEmpty(numero) || TextUtils.isEmpty(cep) || TextUtils.isEmpty(cidade) || TextUtils.isEmpty(senha) || categorias.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        db = FirebaseFirestore.getInstance();
        String id = db.collection("locais_descarte").document().getId();

        LocalDescarte localDescarte = new LocalDescarte(id, nome, email, telefone, rua, numero, cep, cidade, senha, categorias);
        Map<String, Object> localDescarteMap = new HashMap<>();
        localDescarteMap.put("id", localDescarte.getId());
        localDescarteMap.put("nome", localDescarte.getNome());
        localDescarteMap.put("email", localDescarte.getEmail());
        localDescarteMap.put("telefone", localDescarte.getTelefone());
        localDescarteMap.put("rua", localDescarte.getRua());
        localDescarteMap.put("numero", localDescarte.getNumero());
        localDescarteMap.put("cep", localDescarte.getCep());
        localDescarteMap.put("cidade", localDescarte.getCidade());
        localDescarteMap.put("categorias", localDescarte.getCategorias());
        localDescarteMap.put("senha", senha);

        db.collection("locais_descarte").document(id).set(localDescarteMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this, "Local cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Erro ao cadastrar local.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG", "Email sent.");
                                            }
                                        }
                                    });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}

