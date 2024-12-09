package com.example.proyecto.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.proyecto.Fragments.Africa;
import com.example.proyecto.Fragments.America;
import com.example.proyecto.Fragments.Antartida;
import com.example.proyecto.Fragments.Asia;
import com.example.proyecto.Fragments.Europa;
import com.example.proyecto.Fragments.Oceania;
import com.example.proyecto.Fragments.Todos;

public class AdapterTab extends FragmentStateAdapter {

    public AdapterTab(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0:
                return new Todos();
            case 1:
                return new Europa();

            case 2:
                return  new Africa();

            case 3:
                return new Asia();

            case 4:
                return new Oceania();

            case 5:
                return new America();

            case 6:
                return new Antartida();

            default:
                throw new IllegalArgumentException("Posicion no valida: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
