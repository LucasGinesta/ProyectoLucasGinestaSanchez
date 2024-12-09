package com.example.proyecto;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyecto.API.InterfazPaises;
import com.example.proyecto.API.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaisesViewModel extends AndroidViewModel {

    private MutableLiveData<List<PaisesModel>> paisesTotal;
    private MutableLiveData<List<PaisesModel>> paisesEuropa;
    private MutableLiveData<List<PaisesModel>> paisesAfrica;
    private MutableLiveData<List<PaisesModel>> paisesAsia;
    private MutableLiveData<List<PaisesModel>> paisesAmerica;
    private MutableLiveData<List<PaisesModel>> paisesOceania;
    private MutableLiveData<List<PaisesModel>> paisesAntartida;

    public PaisesViewModel(@NonNull Application application) {
        super(application);
        paisesTotal = new MutableLiveData<>();
        paisesEuropa = new MutableLiveData<>();
        paisesAfrica = new MutableLiveData<>();
        paisesAsia = new MutableLiveData<>();
        paisesAmerica = new MutableLiveData<>();
        paisesOceania = new MutableLiveData<>();
        paisesAntartida = new MutableLiveData<>();
        obtenerPaisesDesdeAPI();
    }

    public LiveData<List<PaisesModel>> getPaisesTotal() {
        return paisesTotal;
    }

    public LiveData<List<PaisesModel>> getPaisesEuropa() {
        return paisesEuropa;
    }

    public LiveData<List<PaisesModel>> getPaisesAfrica() {
        return paisesAfrica;
    }

    public LiveData<List<PaisesModel>> getPaisesAsia() {
        return paisesAsia;
    }

    public LiveData<List<PaisesModel>> getPaisesAmerica() {
        return paisesAmerica;
    }

    public LiveData<List<PaisesModel>> getPaisesOceania() {
        return paisesOceania;
    }

    public LiveData<List<PaisesModel>> getPaisesAntartida() {
        return paisesAntartida;
    }

    private void obtenerPaisesDesdeAPI() {
        InterfazPaises interfazPaises = RetrofitClient.getRetrofitInstancia().create(InterfazPaises.class);
        Call<List<PaisesModel>> call = interfazPaises.getPaises();

        call.enqueue(new Callback<List<PaisesModel>>() {
            @Override
            public void onResponse(Call<List<PaisesModel>> call, Response<List<PaisesModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PaisesModel> todosLosPaises = response.body();
                    paisesTotal.setValue(todosLosPaises);
                    Log.d("PaisesViewModel", "Paises recibidos: " + todosLosPaises.size());
                    filtrarPorRegion(todosLosPaises, "Europe", paisesEuropa);
                    filtrarPorRegion(todosLosPaises, "Africa", paisesAfrica);
                    filtrarPorRegion(todosLosPaises, "Asia", paisesAsia);
                    filtrarPorRegion(todosLosPaises, "Americas", paisesAmerica);
                    filtrarPorRegion(todosLosPaises, "Oceania", paisesOceania);
                    filtrarPorRegion(todosLosPaises, "Antarctic", paisesAntartida);
                }
            }


            @Override
            public void onFailure(Call<List<PaisesModel>> call, Throwable t) {
                paisesTotal.setValue(null);
                paisesEuropa.setValue(null);
                paisesAfrica.setValue(null);
                paisesAsia.setValue(null);
                paisesAmerica.setValue(null);
                paisesOceania.setValue(null);
                paisesAntartida.setValue(null);
            }
        });
    }

    private void filtrarPorRegion(List<PaisesModel> todosLosPaises, String region, MutableLiveData<List<PaisesModel>> liveData) {
        List<PaisesModel> paisesFiltrados = new ArrayList<>();
        for (PaisesModel pais : todosLosPaises) {
            if (region.equalsIgnoreCase(pais.getRegion())) {
                paisesFiltrados.add(pais);
            }
        }
        liveData.setValue(paisesFiltrados);
    }
}

