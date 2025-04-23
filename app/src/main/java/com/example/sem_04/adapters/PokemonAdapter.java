package com.example.sem_04.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sem_04.ColorClass;
import com.example.sem_04.DetallepokActivity;
import com.example.sem_04.Entidades.Pokemon;
import com.example.sem_04.Entidades.PokemonList;
import com.example.sem_04.FormColorActivity2;
import com.example.sem_04.R;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{

    private List<PokemonList> data;

    public PokemonAdapter(List<PokemonList> data)
    {
        this.data = data;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        TextView tvname = holder.itemView.findViewById(R.id.tvname);
        PokemonList pokemon = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetallepokActivity.class);
                intent.putExtra("name", pokemon.getName());
                intent.putExtra("url", pokemon.getUrl());
                v.getContext().startActivity(intent);
            }
        });

        tvname.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder{
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
