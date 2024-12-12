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

/**
 * Fragmento que muestra la lista de todos los países utilizando un RecyclerView.
 * Este fragmento obtiene la lista de países desde el ViewModel y la muestra en la interfaz.
 */
public class Total extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorPaises adaptadorPaises;
    private PaisesViewModel paisesViewModel;
    private List<PaisesModel> paisesList;

    // Constructor vacío requerido por el sistema para crear el fragmento
    public Total() {
    }

    /**
     * Método que infla la vista del fragmento y configura el RecyclerView con su adaptador.
     * También observa el ViewModel para actualizar la lista de países cuando los datos cambian.
     *
     * @param inflater El inflador para inflar la vista.
     * @param container El contenedor donde se adjuntará el fragmento.
     * @param savedInstanceState El estado guardado previamente.
     * @return La vista inflada del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todos, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerTodos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtener el ViewModel que contiene la lista de países
        paisesViewModel = new ViewModelProvider(this).get(PaisesViewModel.class);

        // Obtener datos desde el ViewModel
        PaisesViewModel viewModel = new ViewModelProvider(requireActivity()).get(PaisesViewModel.class);
        viewModel.getPaisesTotal().observe(getViewLifecycleOwner(), paises -> {
            if (paises != null) {
                paisesList.clear();
                paisesList.addAll(paises);
                adaptadorPaises.notifyDataSetChanged();
            }
        });

        // Inicializar lista y adaptador
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

        return view;
    }

    /**
     * Filtra la lista de países según el texto de búsqueda proporcionado.
     * Si el texto de búsqueda está vacío, se muestra la lista completa.
     *
     * @param query El texto que se utilizará para filtrar los países.
     */
    public void findLista(String query) {
        List<PaisesModel> paisesFind = new ArrayList<>();

        // Verifica que la búsqueda no esté vacía y que la lista de países no sea nula
        if (!query.isEmpty() && paisesList != null) {
            for (PaisesModel pais : paisesList) {
                // Filtra la lista de países por el nombre común del país
                if (pais.getName().getCommon().toLowerCase().contains(query.toLowerCase())) {
                    paisesFind.add(pais);
                }
            }
        }

        // Actualiza el adaptador con la lista filtrada
        adaptadorPaises.updateList(paisesFind);
    }
}
