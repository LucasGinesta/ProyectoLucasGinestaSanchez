package com.example.proyecto.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.Adapters.AdaptadorPaises;
import com.example.proyecto.PaisesModel;
import com.example.proyecto.PaisesViewModel;
import com.example.proyecto.R;

import java.util.ArrayList;
import java.util.List;

public class Europa extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorPaises adaptadorPaises;
    private List<PaisesModel> paisesEuropa;

    public Europa() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_europa, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerEuropa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista y adaptador
        paisesEuropa = new ArrayList<>();
        adaptadorPaises = new AdaptadorPaises(paisesEuropa, country -> {
            // Acción al hacer clic en un país
            showCountryDetails(country);
        });
        recyclerView.setAdapter(adaptadorPaises);

        // Obtener datos desde el ViewModel
        PaisesViewModel viewModel = new ViewModelProvider(requireActivity()).get(PaisesViewModel.class);
        viewModel.getPaisesEuropa().observe(getViewLifecycleOwner(), paises -> {
            if (paises != null) {
                paisesEuropa.clear();
                paisesEuropa.addAll(paises);
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