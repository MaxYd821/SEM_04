package com.example.sem_04.Services;

import com.example.sem_04.ColorClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ColorService {
    @GET("/colores")
    Call<List<ColorClass>> getColors();
}
