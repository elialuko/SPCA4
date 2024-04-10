package com.example.spca4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;
    List<Basket> list;

    public Adapter(Context context, List<Basket> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.entry2,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Basket basket = list.get(position);
        holder.category.setText(basket.getCategory());
        holder.title.setText(basket.getTitle());
        holder.manufacturer.setText(basket.getManufacturer());
        holder.price.setText(basket.getPrice());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category, title, manufacturer, price;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            category = itemView.findViewById(R.id.textCategory);
            title = itemView.findViewById(R.id.textTitle);
            manufacturer = itemView.findViewById(R.id.textManufacturer);
            price = itemView.findViewById(R.id.textPrice);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
