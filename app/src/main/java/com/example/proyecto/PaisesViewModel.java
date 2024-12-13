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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ViewModel que gestiona la lógica de negocio relacionada con los países.
 * Obtiene los datos desde una API y los organiza en diferentes regiones.
 */
public class PaisesViewModel extends AndroidViewModel {

    // LiveData que almacena las listas de paises por región.
    private MutableLiveData<List<PaisesModel>> paisesTotal;
    private MutableLiveData<List<PaisesModel>> paisesEuropa;
    private MutableLiveData<List<PaisesModel>> paisesAfrica;
    private MutableLiveData<List<PaisesModel>> paisesAsia;
    private MutableLiveData<List<PaisesModel>> paisesAmerica;
    private MutableLiveData<List<PaisesModel>> paisesOceania;
    private MutableLiveData<List<PaisesModel>> paisesAntartida;

    /**
     * The Executor.
     */
// Executor para ejecutar tareas en un hilo secundario.
    Executor executor;

    /**
     * Constructor que inicializa los LiveData y el Executor.
     *
     * @param application La aplicación que se usa para inicializar el ViewModel.
     */
    public PaisesViewModel(@NonNull Application application) {
        super(application);

        // Inicialización de los LiveData
        paisesTotal = new MutableLiveData<>();
        paisesEuropa = new MutableLiveData<>();
        paisesAfrica = new MutableLiveData<>();
        paisesAsia = new MutableLiveData<>();
        paisesAmerica = new MutableLiveData<>();
        paisesOceania = new MutableLiveData<>();
        paisesAntartida = new MutableLiveData<>();

        // Crear un executor para ejecutar tareas en un hilo secundario
        executor = Executors.newSingleThreadExecutor();

        // Llamada para obtener los países desde la API (descomentada si es necesario)
        // obtenerPaisesDesdeAPI();
    }

    /**
     * Obtiene los países de todas las regiones.
     *
     * @return LiveData con la lista de países de todas las regiones.
     */
    public LiveData<List<PaisesModel>> getPaisesTotal() {
        return paisesTotal;
    }

    /**
     * Obtiene los países de la región de Europa.
     *
     * @return LiveData con la lista de países de Europa.
     */
    public LiveData<List<PaisesModel>> getPaisesEuropa() {
        return paisesEuropa;
    }

    /**
     * Obtiene los países de la región de África.
     *
     * @return LiveData con la lista de países de África.
     */
    public LiveData<List<PaisesModel>> getPaisesAfrica() {
        return paisesAfrica;
    }

    /**
     * Obtiene los países de la región de Asia.
     *
     * @return LiveData con la lista de países de Asia.
     */
    public LiveData<List<PaisesModel>> getPaisesAsia() {
        return paisesAsia;
    }

    /**
     * Obtiene los países de la región de América.
     *
     * @return LiveData con la lista de países de América.
     */
    public LiveData<List<PaisesModel>> getPaisesAmerica() {
        return paisesAmerica;
    }

    /**
     * Obtiene los países de la región de Oceanía.
     *
     * @return LiveData con la lista de países de Oceanía.
     */
    public LiveData<List<PaisesModel>> getPaisesOceania() {
        return paisesOceania;
    }

    /**
     * Obtiene los países de la región de la Antártida.
     *
     * @return LiveData con la lista de países de la Antártida.
     */
    public LiveData<List<PaisesModel>> getPaisesAntartida() {
        return paisesAntartida;
    }

    /**
     * Obtiene los países desde la API utilizando Retrofit y organiza los datos por región.
     * Este método se ejecuta en un hilo secundario para no bloquear el hilo principal.
     */
    public void obtenerPaisesDesdeAPI() {
        // Crear una instancia del servicio de la API usando Retrofit
        InterfazPaises interfazPaises = RetrofitClient.getRetrofitInstancia().create(InterfazPaises.class);

        // Llamada a la API para obtener la lista de países
        Call<List<PaisesModel>> call = interfazPaises.getPaises();

        // Ejecutar la llamada en un hilo secundario usando el executor
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Realizar la solicitud asincrónica
                call.enqueue(new Callback<List<PaisesModel>>() {
                    @Override
                    public void onResponse(Call<List<PaisesModel>> call, Response<List<PaisesModel>> response) {
                        // Si la respuesta es exitosa y el cuerpo no es nulo, procesar los datos
                        if (response.isSuccessful() && response.body() != null) {
                            List<PaisesModel> todosLosPaises = response.body();

                            // Establecer los valores para todos los países
                            paisesTotal.postValue(todosLosPaises);

                            // Filtrar los países por región y actualizar los LiveData correspondientes
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
                        // Si hay un error en la solicitud, loguear el error y vaciar los LiveData
                        Log.d("PaisesViewModel","Error API: "+t);
                        paisesTotal.postValue(null);
                        paisesEuropa.postValue(null);
                        paisesAfrica.postValue(null);
                        paisesAsia.postValue(null);
                        paisesAmerica.postValue(null);
                        paisesOceania.postValue(null);
                        paisesAntartida.postValue(null);

                        // Intentar obtener los países de nuevo en caso de error
                        obtenerPaisesDesdeAPI();
                        Log.d("PaisesViewModel","Error Call: "+call);
                    }
                });
            }
        });
    }

    /**
     * Filtra la lista de paises por region y actualiza el LiveData correspondiente.
     *
     * @param todosLosPaises Lista completa de países obtenidos de la API.
     * @param region La región por la cual se desea filtrar los países.
     * @param liveData El LiveData que se actualizará con los países filtrados.
     */
    private void filtrarPorRegion(List<PaisesModel> todosLosPaises, String region, MutableLiveData<List<PaisesModel>> liveData) {
        List<PaisesModel> paisesFiltrados = new ArrayList<>();

        // Filtrar los países que pertenecen a la región especificada
        for (PaisesModel pais : todosLosPaises) {
            if (region.equalsIgnoreCase(pais.getRegion())) {
                paisesFiltrados.add(pais);
            }
        }

        // Establecer el valor del LiveData con la lista filtrada
        liveData.setValue(paisesFiltrados);
    }
}
