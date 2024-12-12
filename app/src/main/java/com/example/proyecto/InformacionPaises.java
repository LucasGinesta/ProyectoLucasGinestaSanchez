package com.example.proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Fragmento que muestra la información detallada de un país, como su nombre, bandera, región, población,
 * idiomas, etc. Recibe un objeto `PaisesModel` a través de un `Bundle` y muestra sus detalles en la interfaz.
 */
public class InformacionPaises extends Fragment {

    private PaisesModel pais;

    /**
     * Crea una nueva instancia de `InformacionPaises` con el objeto `PaisesModel` que contiene los detalles del país.
     *
     * @param pais El objeto `PaisesModel` con la información del país a mostrar.
     * @return Una nueva instancia del fragmento `InformacionPaises`.
     */
    public static InformacionPaises newInstance(PaisesModel pais) {
        InformacionPaises fragment = new InformacionPaises();
        Bundle args = new Bundle();
        args.putSerializable("pais", pais);  // Se pasa el objeto 'pais' como un Serializable en el Bundle
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Infla la vista del fragmento y muestra la información del país, incluyendo su nombre, bandera, región,
     * población, idiomas, etc. También maneja el botón "Volver" para ocultar el fragmento y mostrar la vista previa.
     *
     * @param inflater El inflador que se utiliza para inflar la vista.
     * @param container El contenedor donde se añadirá el fragmento.
     * @param savedInstanceState El estado guardado previamente del fragmento.
     * @return La vista inflada del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informacion_paises, container, false);

        // Recupera el objeto 'pais' desde los argumentos del fragmento
        if (getArguments() != null) {
            pais = (PaisesModel) getArguments().getSerializable("pais");
        }

        // Inicialización de vistas para mostrar los detalles del país
        TextView nameText = view.findViewById(R.id.text_country_name);
        ImageView flagImage = view.findViewById(R.id.image_country_flag);
        TextView regionText = view.findViewById(R.id.text_region);
        TextView demonimosText = view.findViewById(R.id.text_demonimos);
        TextView areaText = view.findViewById(R.id.text_area);
        TextView continentText = view.findViewById(R.id.text_continent);
        TextView populationText = view.findViewById(R.id.text_population);
        TextView languagesText = view.findViewById(R.id.text_languages);
        TextView latlngText = view.findViewById(R.id.text_latlng);
        TextView currenciesText = view.findViewById(R.id.text_currencies);

        // Configuración del botón "Volver"
        Button button = view.findViewById(R.id.buttonVolver);
        button.setOnClickListener(view1 -> {
            // Oculta la vista de información y muestra el viewPager
            requireActivity().findViewById(R.id.informacion_container).setVisibility(View.GONE);
            requireActivity().findViewById(R.id.viewPager2).setVisibility(View.VISIBLE);
        });

        // Carga la bandera del país usando Glide para cargar imágenes desde una URL
        Glide.with(this)
                .load(pais.getFlags().getPng())
                .into(flagImage);

        // Establece el texto de las vistas con la información del país
        regionText.setText("Region: " + pais.getRegion());
        demonimosText.setText("Demonimos: " + pais.getDemonyms());
        areaText.setText("Area: " + pais.getArea() + " km²");
        continentText.setText("Continent: " + pais.getContinents());
        populationText.setText("Population: " + pais.getPopulation() + " people");
        languagesText.setText("Languages: " + pais.getLanguages());
        latlngText.setText("Lat/Lng: " + pais.getLatlng());
        currenciesText.setText("Currencies: " + pais.getCurrencies());

        return view;
    }
}
