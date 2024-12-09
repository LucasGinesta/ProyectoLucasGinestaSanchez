package com.example.proyecto.API;

import com.example.proyecto.PaisesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Creamos esta interfaz que nos permite hacer la conexion con la API de la informacion sobre paises
public interface InterfazPaises {

    @GET("all")
    Call<List<PaisesModel>> getPaises();
}
