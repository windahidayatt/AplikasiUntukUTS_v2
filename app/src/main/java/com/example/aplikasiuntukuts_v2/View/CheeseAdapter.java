package com.example.aplikasiuntukuts_v2.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiuntukuts_v2.Model.Cheese;
import com.example.aplikasiuntukuts_v2.R;

import java.util.ArrayList;
import java.util.List;

public class CheeseAdapter extends RecyclerView.Adapter<CheeseAdapter.CheeseHolder> {

    private List<Cheese> cheeses = new ArrayList<>();

    @NonNull
    @Override
    public CheeseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_item, parent, false);
        return new CheeseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CheeseHolder holder, int position) {
        holder.textViewName.setText(cheeses.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cheeses.size();
    }

    public void setCheeses(List<Cheese> cheeses){
        this.cheeses = cheeses;
        notifyDataSetChanged();
    }

    public  Cheese getCheeseAt(int position){
        return cheeses.get(position);
    }

    class CheeseHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;

        public CheeseHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.text_view_name);
        }
    }
}
