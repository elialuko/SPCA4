package com.example.spca4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.MyViewHolder>{
    Context context;
    List<Users> list;

    public TwoAdapter(Context context, List<Users> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public TwoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.entry3,parent,false);
        return new TwoAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Users users = list.get(position);
        holder.name.setText(users.getName());
        holder.email.setText(users.getEmail());
        holder.number.setText(users.getNumber());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, number;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.textName);
            email = itemView.findViewById(R.id.textEmail);
            number = itemView.findViewById(R.id.textNumber);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


}
