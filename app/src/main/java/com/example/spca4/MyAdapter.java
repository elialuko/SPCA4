package com.example.spca4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog(clothing);
            }
        });
    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category, title, manufacturer, price, quantity;
        CardView cardView;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            category = itemView.findViewById(R.id.textCategory);
            title = itemView.findViewById(R.id.textTitle);
            manufacturer = itemView.findViewById(R.id.textManufacturer);
            price = itemView.findViewById(R.id.textPrice);
            quantity = itemView.findViewById(R.id.textQuantity);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    private void showDialog(Clothing clothing) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Clothing Details");

        // Set the dialog message using clothing details
        String message = "Category: " + clothing.getCategory() + "\n" +
                "Title: " + clothing.getTitle() + "\n" +
                "Manufacturer: " + clothing.getManufacturer() + "\n" +
                "Price: " + clothing.getPrice() + "\n" +
                "Quantity: " + clothing.getQuantity();

        builder.setMessage(message);

        // Add Save button to save the clothing details
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Save clothing details to Checkout under current user
                saveToCheckout(clothing);
            }
        });

        // Add Close button to close the dialog
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing, just close the dialog
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveToCheckout(Clothing clothing) {
        // Get current user ID
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        DatabaseReference basketRef = userRef.child("Basket");
        String basketID = basketRef.push().getKey();
        Map<String, Object> basketData = new HashMap<>();
        basketData.put("category",clothing.getCategory());
        basketData.put("title",clothing.getTitle());
        basketData.put("manufacturer",clothing.getManufacturer());
        basketData.put("price",clothing.getPrice());
        basketRef.child(basketID).setValue(basketData);

        // Display success message or perform any other action
        Toast.makeText(context, "Clothing saved to Basket", Toast.LENGTH_SHORT).show();
    }

}
