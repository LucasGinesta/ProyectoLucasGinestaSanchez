package com.example.proyecto.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Clase para configurar Retrofit
public class RetrofitClient {

    private static final String BASE_URL = "https://restcountries.com/v3.1/";
    private static Retrofit retrofit;

    //Obtener instancia
    public static Retrofit getRetrofitInstancia(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
