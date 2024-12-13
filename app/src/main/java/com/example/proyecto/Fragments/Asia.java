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
import com.example.proyecto.MainActivity;
import com.example.proyecto.PaisesModel;
import com.example.proyecto.PaisesViewModel;
import com.example.proyecto.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragmento que muestra la lista de países de Asia utilizando un RecyclerView.
 * Este fragmento obtiene la lista de países desde el ViewModel y la muestra en la interfaz.
 */
public class Asia extends Fragment {

    private RecyclerView recyclerView;
    private AdaptadorPaises adaptadorPaises;
    private List<PaisesModel> paisesAsia;

    /**
     * Instantiates a new Asia.
     */
// Constructor vacío
    public Asia() {
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asia, container, false);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerAsia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtener datos desde el ViewModel
        PaisesViewModel viewModel = new ViewModelProvider(requireActivity()).get(PaisesViewModel.class);
        viewModel.getPaisesAsia().observe(getViewLifecycleOwner(), paises -> {
            if (paises != null) {
                paisesAsia.clear();
                paisesAsia.addAll(paises);
                adaptadorPaises.notifyDataSetChanged();
            }
        });

        // Inicializar lista y adaptador
        paisesAsia = new ArrayList<>();
        adaptadorPaises = new AdaptadorPaises(paisesAsia, country -> {
            // Llama al método showCountryDetails() de MainActivity cuando un país es seleccionado
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
     * Método que se llama cuando un país es seleccionado y muestra sus detalles.
     * Este método necesita ser implementado según la lógica que desees para mostrar los detalles del país.
     *
     * @param country El país seleccionado cuya información se desea mostrar.
     */
    private void showCountryDetails(PaisesModel country) {
        // Implementa la lógica para mostrar los detalles del país
    }

    /**
     * Filtra la lista de países de Asia según el texto de búsqueda proporcionado.
     * Si el texto de búsqueda está vacío o nulo, se muestra la lista completa.
     *
     * @param query El texto que se utilizará para filtrar los países.
     */
    public void findLista(String query) {
        List<PaisesModel> paisesFind = new ArrayList<>();

        // Verifica si la búsqueda no está vacía y paisesList no es nula
        if (query != null && !query.trim().isEmpty() && paisesAsia != null) {
            // Filtra la lista según el query
            for (PaisesModel pais : paisesAsia) {
                if (pais.getName().getCommon().toLowerCase().contains(query.toLowerCase())) {
                    paisesFind.add(pais);
                }
            }
        } else if (paisesAsia != null) {
            // Si no hay búsqueda o está vacía, muestra la lista completa
            paisesFind = new ArrayList<>(paisesAsia);  // Copia la lista completa
        }

        // Actualiza el adaptador con la lista filtrada o completa
        adaptadorPaises.updateList(paisesFind);
    }
}
