package com.example.proyecto;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class InformacionPaises extends Fragment {

    private PaisesModel pais;

    public static InformacionPaises newInstance(PaisesModel pais) {
        InformacionPaises fragment = new InformacionPaises();
        Bundle args = new Bundle();
        args.putSerializable("pais", pais);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informacion_paises, container, false);

        if (getArguments() != null) {
            pais = (PaisesModel) getArguments().getSerializable("pais");
        }

        TextView nameText = view.findViewById(R.id.text_country_name);
        ImageView flagImage = view.findViewById(R.id.image_country_flag);
        TextView regionText = view.findViewById(R.id.text_region);
        TextView continentText = view.findViewById(R.id.text_continent);
        TextView populationText = view.findViewById(R.id.text_population);
        TextView languagesText = view.findViewById(R.id.text_languages);
        TextView timezonesText = view.findViewById(R.id.text_timezones);
        TextView latlngText = view.findViewById(R.id.text_latlng);
        TextView currenciesText = view.findViewById(R.id.text_currencies);
        Button button = view.findViewById(R.id.buttonVolver);
        button.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        nameText.setText(pais.getName().getCommon());
        regionText.setText("Region: " + pais.getRegion());
        continentText.setText("Continent: " + pais.getContinent());
        populationText.setText("Population: " + pais.getPopulation());
        languagesText.setText("Languages: " + pais.getLanguages());
        currenciesText.setText("Currencies: " + pais.getCurrencies());

        return view;
    }

}