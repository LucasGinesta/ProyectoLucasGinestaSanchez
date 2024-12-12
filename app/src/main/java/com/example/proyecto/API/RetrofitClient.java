package com.example.proyecto.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase para configurar y gestionar la instancia de Retrofit que se utiliza para realizar peticiones a la API.
 * Esta clase asegura que solo haya una instancia de Retrofit en la aplicación, implementando el patrón Singleton.
 */
public class RetrofitClient {

    // URL base de la API que se va a consumir.
    private static final String BASE_URL = "https://restcountries.com/v3.1/";

    // Instancia de Retrofit para realizar las solicitudes a la API.
    private static Retrofit retrofit;

    /**
     * Obtiene la instancia de Retrofit.
     * Si no existe una instancia previa, crea una nueva utilizando la URL base y el convertidor Gson.
     *
     * @return La instancia de Retrofit configurada.
     */
    public static Retrofit getRetrofitInstancia() {
        if (retrofit == null) {
            // Configura Retrofit con la URL base y el convertidor Gson para la conversión de datos JSON a objetos Java.
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
