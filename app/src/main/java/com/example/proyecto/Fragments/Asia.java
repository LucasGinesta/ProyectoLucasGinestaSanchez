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

public class Asia extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorPaises adaptadorPaises;
    private List<PaisesModel> paisesAsia;

    public Asia() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asia, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerAsia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista y adaptador
        paisesAsia = new ArrayList<>();
        adaptadorPaises = new AdaptadorPaises(paisesAsia, country -> {
            // Acción al hacer clic en un país
            showCountryDetails(country);
        });
        recyclerView.setAdapter(adaptadorPaises);

        // Obtener datos desde el ViewModel
        PaisesViewModel viewModel = new ViewModelProvider(requireActivity()).get(PaisesViewModel.class);
        viewModel.getPaisesAsia().observe(getViewLifecycleOwner(), paises -> {
            if (paises != null) {
                paisesAsia.clear();
                paisesAsia.addAll(paises);
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