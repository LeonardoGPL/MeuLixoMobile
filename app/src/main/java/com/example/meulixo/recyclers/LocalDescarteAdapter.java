package com.example.meulixo.recyclers;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meulixo.R;
import com.example.meulixo.models.LocalDescarte;

import java.util.List;

public class LocalDescarteAdapter extends RecyclerView.Adapter<LocalDescarteAdapter.ViewHolder> {

    private List<LocalDescarte> locaisDescarte;

    public LocalDescarteAdapter(List<LocalDescarte> locaisDescarte) {
        this.locaisDescarte = locaisDescarte;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reciclador, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocalDescarte localDescarte = locaisDescarte.get(position);
        holder.textViewNome.setText(localDescarte.getNome());
        holder.textViewRua.setText(localDescarte.getRua());
        holder.textViewNumero.setText(localDescarte.getNumero());
        holder.textViewCidade.setText(localDescarte.getCidade());
        holder.textViewCEP.setText(localDescarte.getCep());
        holder.textViewFone.setText(localDescarte.getTelefone());
        holder.textViewEmail.setText(localDescarte.getEmail());
        holder.textViewCategorias.setText(TextUtils.join(", ", localDescarte.getCategorias()));
    }

    @Override
    public int getItemCount() {
        return locaisDescarte.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNome;
        public TextView textViewRua;
        public TextView textViewNumero;
        public TextView textViewCidade;
        public TextView textViewCEP;
        public TextView textViewCategorias;
        public TextView textViewFone;
        public TextView textViewEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewRua = itemView.findViewById(R.id.textViewRua);
            textViewNumero = itemView.findViewById(R.id.textViewNumero);
            textViewCidade = itemView.findViewById(R.id.textViewCidade);
            textViewCEP = itemView.findViewById(R.id.textViewCEP);
            textViewCategorias = itemView.findViewById(R.id.textViewCategorias);
            textViewFone = itemView.findViewById(R.id.textViewFone);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
}


