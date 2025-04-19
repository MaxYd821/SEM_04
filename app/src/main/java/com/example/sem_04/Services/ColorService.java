package com.example.sem_04.Services;

import com.example.sem_04.ColorClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ColorService {
    @GET("/colores")
    Call<List<ColorClass>> getColors(@Query("limit") int limit, @Query("page") int page);

    @POST("/colores")
    Call<ColorClass> crearColor(@Body ColorClass color);

    @PUT("/colores/{id}")
    Call<ColorClass> update(@Path("id") int id, @Body ColorClass color);

    @DELETE("/colores/{id}")
    Call<ColorClass> delete(@Path("id") int id);
}
