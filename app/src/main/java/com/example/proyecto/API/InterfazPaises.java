package com.example.proyecto.API;

import com.example.proyecto.PaisesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interfaz para la conexión con la API que proporciona información sobre los países.
 * Utiliza Retrofit para hacer la llamada y obtener los datos de los países.
 */
public interface InterfazPaises {

    /**
     * Obtiene la lista de países con los datos de interés desde la API.
     *
     * @return Un objeto {@link Call} que puede usarse para obtener la lista de países con información como nombre, capital, monedas, banderas, población, continentes, región, zonas horarias, área, coordenadas,         idiomas, gentilicios y continentes.
     */
    @GET("all?fields=name,capital,currencies,flags,population,continents,region,timezones,area,latlng,languages,demonyms,continents")
    Call<List<PaisesModel>> getPaises();
}
