package com.example.proyecto.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.proyecto.PaisesModel;
import com.example.proyecto.R;

import java.util.List;

public class AdaptadorPaises extends RecyclerView.Adapter<AdaptadorPaises.PaisesViewHolder> {

    private List<PaisesModel> paises;
    private OnItemClickListener listener;

    public AdaptadorPaises(List<PaisesModel> paises, OnItemClickListener listener) {
        this.paises = paises;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PaisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pais , parent, false);
        return new PaisesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisesViewHolder holder, int position) {
        PaisesModel pais = paises.get(position);
        holder.nameTextView.setText(pais.getName().getCommon());
        Glide.with(holder.itemView.getContext())
                .load(pais.getFlags().getPng())
                .into(holder.flagImageView);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(pais));
    }

    @Override
    public int getItemCount() {
        return paises.size();
    }

    public void setPaises(List<PaisesModel> paises) {
        this.paises.clear();
        this.paises.addAll(paises);
    }


    public interface OnItemClickListener {
        void onItemClick(PaisesModel pais);
    }

    // ViewHolder
    public static class PaisesViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView flagImageView;

        public PaisesViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nombrePais);
            flagImageView = itemView.findViewById(R.id.imagenPais);
        }

    }
}
