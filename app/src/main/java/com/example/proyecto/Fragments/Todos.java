package com.example.proyecto.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.Adapters.AdaptadorPaises;
import com.example.proyecto.MainActivity;
import com.example.proyecto.PaisesModel;
import com.example.proyecto.PaisesViewModel;
import com.example.proyecto.R;

import java.util.ArrayList;
import java.util.List;

public class Todos extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorPaises adaptadorPaises;
    private PaisesViewModel paisesViewModel;
    private List<PaisesModel> paisesList;

    public Todos() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todos, container, false);

        recyclerView = view.findViewById(R.id.recyclerTodos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        paisesViewModel = new ViewModelProvider(this).get(PaisesViewModel.class);

        // Inicializar la lista y el adaptador
        paisesList = new ArrayList<>();
        adaptadorPaises = new AdaptadorPaises(paisesList, country -> {
            // Acción al hacer clic en un país
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).showCountryDetails(country);
            } else {
                Log.e("Todos", "La actividad no es una instancia de MainActivity");
            }
        });
        recyclerView.setAdapter(adaptadorPaises);

        paisesViewModel.getPaisesTotal().observe(getViewLifecycleOwner(), paises -> {
            if (paises != null) {
                paisesList.clear();
                paisesList.addAll(paises);
                adaptadorPaises.notifyDataSetChanged();
            } else {
                Log.e("Todos", "Error al obtener los países");
            }
        });

        return view;
    }

}