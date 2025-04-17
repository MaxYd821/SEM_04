package com.example.sem_04;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sem_04.Services.ColorService;
import com.example.sem_04.adapters.ColorAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_color);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.color), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvColor = findViewById(R.id.rvColor);
        rvColor.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6800be1fb72e9cfaf7288888.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ColorService service = retrofit.create(ColorService.class);

        service.getColors().enqueue(new Callback<List<ColorClass>>() {
            @Override
            public void onResponse(Call<List<ColorClass>> call, Response<List<ColorClass>> response) {
                List<ColorClass> data = response.body();
                ColorAdapter adapter = new ColorAdapter(data);
                rvColor.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ColorClass>> call, Throwable throwable) {

            }
        });
    }
}