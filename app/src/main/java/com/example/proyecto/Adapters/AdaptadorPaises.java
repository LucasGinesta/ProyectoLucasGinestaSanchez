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

/**
 * Adaptador para mostrar la lista de países en un RecyclerView.
 * Este adaptador inflará el diseño de los elementos y gestionará los clics en cada ítem.
 */
public class AdaptadorPaises extends RecyclerView.Adapter<AdaptadorPaises.PaisesViewHolder> {

    // Lista de países a mostrar
    private List<PaisesModel> paises;

    // Listener para manejar clics en los elementos
    private OnItemClickListener listener;

    /**
     * Constructor para inicializar el adaptador con la lista de países y el listener para los clics.
     *
     * @param paises Lista de países a mostrar.
     * @param listener Listener para gestionar los clics en los elementos.
     */
    public AdaptadorPaises(List<PaisesModel> paises, OnItemClickListener listener) {
        this.paises = paises;
        this.listener = listener;
    }

    /**
     * Crea un nuevo ViewHolder para cada ítem de la lista.
     *
     * @param parent El contenedor en el que se agregará el ítem.
     * @param viewType El tipo de vista.
     * @return Un nuevo ViewHolder con la vista inflada.
     */
    @NonNull
    @Override
    public PaisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla la vista de cada ítem
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pais, parent, false);
        return new PaisesViewHolder(itemView);
    }

    /**
     * Asocia los datos del país con las vistas del ViewHolder.
     *
     * @param holder El ViewHolder que contiene las vistas.
     * @param position La posición del ítem en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull PaisesViewHolder holder, int position) {
        PaisesModel pais = paises.get(position);
        holder.nameTextView.setText(pais.getName().getCommon());

        // Carga la imagen de la bandera utilizando Glide
        Glide.with(holder.itemView.getContext())
                .load(pais.getFlags().getPng())
                .into(holder.flagImageView);

        // Establece un clic en el ítem para manejar el evento
        holder.itemView.setOnClickListener(v -> listener.onItemClick(pais));
    }

    /**
     * Devuelve el número de ítems en la lista de países.
     *
     * @return El número de elementos en la lista.
     */
    @Override
    public int getItemCount() {
        return paises.size();
    }

    /**
     * Interfaz para manejar los clics en los elementos de la lista.
     */
    public interface OnItemClickListener {
        /**
         * Método que se llama cuando se hace clic en un ítem de la lista.
         *
         * @param pais El país que fue clickeado.
         */
        void onItemClick(PaisesModel pais);
    }

    /**
     * ViewHolder que contiene las vistas de cada ítem (nombre y bandera).
     */
    public static class PaisesViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView flagImageView;

        /**
         * Constructor que inicializa las vistas del ViewHolder.
         *
         * @param itemView La vista del ítem.
         */
        public PaisesViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nombrePais);
            flagImageView = itemView.findViewById(R.id.imagenPais);
        }
    }

    /**
     * Actualiza la lista de países y notifica al adaptador que los datos han cambiado.
     *
     * @param newPaisesList La nueva lista de países.
     */
    public void updateList(List<PaisesModel> newPaisesList) {
        this.paises = newPaisesList;
        notifyDataSetChanged();
    }
}
