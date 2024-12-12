package com.example.proyecto.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.proyecto.Fragments.Africa;
import com.example.proyecto.Fragments.America;
import com.example.proyecto.Fragments.Antartida;
import com.example.proyecto.Fragments.Asia;
import com.example.proyecto.Fragments.Europa;
import com.example.proyecto.Fragments.Oceania;
import com.example.proyecto.Fragments.Total;

/**
 * Adaptador para manejar los fragmentos dentro de un ViewPager2.
 * Este adaptador permite mostrar las vistas de los continentes en diferentes pestañas.
 */
public class AdapterTab extends FragmentStateAdapter {

    // Array que almacena los fragmentos para las distintas secciones.
    private final Fragment[] fragments;

    /**
     * Constructor que inicializa el adaptador con los fragmentos correspondientes.
     *
     * @param fragmentActivity La actividad asociada al adaptador.
     */
    public AdapterTab(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        // Inicialización de los fragmentos.
        fragments = new Fragment[]{
                new Total(),     // Vista total de todos los continentes.
                new Europa(),    // Vista de Europa.
                new Africa(),    // Vista de África.
                new Asia(),      // Vista de Asia.
                new Oceania(),   // Vista de Oceanía.
                new America(),   // Vista de América.
                new Antartida()  // Vista de la Antártida.
        };
    }

    /**
     * Devuelve el fragment correspondiente a la posición indicada.
     *
     * @param position La posición de la pestaña en el ViewPager2.
     * @return El fragment correspondiente a la posición.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    /**
     * Devuelve el número total de fragmentos en el adaptador.
     *
     * @return El número de fragmentos.
     */
    @Override
    public int getItemCount() {
        return fragments.length;
    }

    /**
     * Devuelve el fragment correspondiente a la posición indicada (método auxiliar).
     *
     * @param position La posición del fragmento en la lista de fragmentos.
     * @return El fragment correspondiente a la posición.
     */
    public Fragment getFragment(int position) {
        return fragments[position];
    }
}
