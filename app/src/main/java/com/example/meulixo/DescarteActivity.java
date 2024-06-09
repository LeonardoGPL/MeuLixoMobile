package com.example.meulixo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.meulixo.models.LocalDescarte;
import com.example.meulixo.recyclers.LocalDescarteAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DescarteActivity extends AppCompatActivity {
    Button buttonVoltar;
    private RecyclerView recyclerView;
    private LocalDescarteAdapter adapter;
    private List<LocalDescarte> locaisDescarte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descarte);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
        locaisDescarte = new ArrayList<>();
        adapter = new LocalDescarteAdapter(locaisDescarte);
        recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference locaisDescarteRef = db.collection("locais_descarte");

        locaisDescarteRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    locaisDescarte.clear();
                    for (DocumentSnapshot document : task.getResult()) {
                        LocalDescarte localDescarte = document.toObject(LocalDescarte.class);
                        locaisDescarte.add(localDescarte);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.w("MainActivity", "Error getting documents.", task.getException());
                }
            }
        });

    }
}

