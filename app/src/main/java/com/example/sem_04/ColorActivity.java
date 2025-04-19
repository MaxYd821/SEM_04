package com.example.sem_04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sem_04.Services.ColorService;
import com.example.sem_04.adapters.ColorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorActivity extends AppCompatActivity {

    RecyclerView rvColor;
    boolean isLoading = false;
    boolean isLastPage = false;
    int currentPage = 1;
    List<ColorClass> data = new ArrayList<>();
    ColorAdapter adapter;

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

        Toast.makeText(getApplicationContext(), "ColorActivity onCreate", Toast.LENGTH_SHORT).show();

        FloatingActionButton button = findViewById(R.id.fabGotoColorForm);
        button.setOnClickListener(v->{
            Intent intent = new Intent(this,FormColorActivity2.class);
            startActivity(intent);
        });
        rvColor = findViewById(R.id.rvColor);
        rvColor.setLayoutManager(new LinearLayoutManager(this));

        setUpRecyclerView();
        //loadMoreColors();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "ColorActivity onResume", Toast.LENGTH_SHORT).show();

        data.clear();
        currentPage = 1;
        isLastPage = false;
        adapter.notifyDataSetChanged();

        loadMoreColors();
    }

    private void loadMoreColors(){

        isLoading = true;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6800be1fb72e9cfaf7288888.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ColorService service = retrofit.create(ColorService.class);
        service.getColors(20,currentPage).enqueue(new Callback<List<ColorClass>>() {
            @Override
            public void onResponse(Call<List<ColorClass>> call, Response<List<ColorClass>> response) {
                isLoading = false;
                if (!response.isSuccessful()) return;
                if(response.body() == null) return;
                if (response.body().isEmpty()) {
                    isLastPage = true;
                    return;
                }

                data.addAll(response.body());
                adapter.notifyDataSetChanged();
                //List<ColorClass> data = response.body();

            }

            @Override
            public void onFailure(Call<List<ColorClass>> call, Throwable throwable) {
                isLoading = false;
            }
        });
    }

    private void setUpRecyclerView() {

        adapter = new ColorAdapter(data);
        rvColor.setAdapter(adapter);

        rvColor.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) return;

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {

                        currentPage++;
                        loadMoreColors();
                    }
                }
            }
        });
    }
}