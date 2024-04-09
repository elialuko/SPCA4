package com.example.spca4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;
    List<Clothing> list;

    public MyAdapter(Context context, List<Clothing> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.entry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Clothing clothing = list.get(position);
        holder.category.setText(clothing.getCategory());
        holder.title.setText(clothing.getTitle());
        holder.manufacturer.setText(clothing.getManufacturer());
        holder.price.setText(clothing.getPrice());
        holder.quantity.setText(clothing.getQuantity());
    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category, title, manufacturer, price, quantity;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            category = itemView.findViewById(R.id.textCategory);
            title = itemView.findViewById(R.id.textTitle);
            manufacturer = itemView.findViewById(R.id.textManufacturer);
            price = itemView.findViewById(R.id.textPrice);
            quantity = itemView.findViewById(R.id.textQuantity);
        }
    }
}
