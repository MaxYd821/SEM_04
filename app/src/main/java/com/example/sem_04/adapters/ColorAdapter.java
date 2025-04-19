package com.example.sem_04.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sem_04.ColorClass;
import com.example.sem_04.FormColorActivity2;
import com.example.sem_04.R;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder>{

    private List<ColorClass> data;

    public ColorAdapter(List<ColorClass> data)
    {
        this.data = data;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.ColorViewHolder holder, int position) {
        TextView tvname = holder.itemView.findViewById(R.id.tvname);
        TextView tvhex = holder.itemView.findViewById(R.id.tvhex);
        ColorClass color = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Color: " + color.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), FormColorActivity2.class);
                // Pass the color data to the FormColorActivity2
                intent.putExtra("colorId", color.getId());
                intent.putExtra("colorName", color.getName());
                intent.putExtra("colorHex", color.getHex());
                v.getContext().startActivity(intent);
            }
        });

        FrameLayout flcirculo = holder.itemView.findViewById(R.id.flcirculo);
        Context context = holder.itemView.getContext();
        GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(context, R.drawable.round_border);
        tvname.setText(color.getName());
        tvhex.setText(color.getHex());
        String hex1 = color.getHex();
        drawable = (GradientDrawable) drawable.mutate();
        drawable.setColor(Color.parseColor(hex1));
        flcirculo.setBackground(drawable);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder{
        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
