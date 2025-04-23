package com.example.sem_04.Services;

import com.example.sem_04.ColorClass;
import com.example.sem_04.Entidades.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("/pokemon")
    Call<Pokemon> getPokemon(@Query("limit") int limit, @Query("page") int page);
}
