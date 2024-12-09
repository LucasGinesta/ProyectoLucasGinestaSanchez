package com.example.proyecto.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.Adapters.AdaptadorPaises;
import com.example.proyecto.InformacionPaises;
import com.example.proyecto.PaisesModel;
import com.example.proyecto.PaisesViewModel;
import com.example.proyecto.R;

import java.util.ArrayList;
import java.util.List;

public class Oceania extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorPaises adaptadorPaises;
    private List<PaisesModel> paisesOcenia;

    public Oceania() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oceania, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerOceania);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista y adaptador
        paisesOcenia = new ArrayList<>();
        adaptadorPaises = new AdaptadorPaises(paisesOcenia, country -> {
            // Acción al hacer clic en un país
            showCountryDetails(country);
        });
        recyclerView.setAdapter(adaptadorPaises);

        // Obtener datos desde el ViewModel
        PaisesViewModel viewModel = new ViewModelProvider(requireActivity()).get(PaisesViewModel.class);
        viewModel.getPaisesOceania().observe(getViewLifecycleOwner(), paises -> {
            if (paises != null) {
                paisesOcenia.clear();
                paisesOcenia.addAll(paises);
                adaptadorPaises.notifyDataSetChanged();
            }
        });

        return view;
    }

    // Método para mostrar detalles del país seleccionado
    private void showCountryDetails(PaisesModel country) {
        // Implementa la lógica para mostrar los detalles del país
    }
}