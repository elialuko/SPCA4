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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog(basket);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category, title, manufacturer, price;
        CardView cardView;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            category = itemView.findViewById(R.id.textCategory);
            title = itemView.findViewById(R.id.textTitle);
            manufacturer = itemView.findViewById(R.id.textManufacturer);
            price = itemView.findViewById(R.id.textPrice);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void showDialog(Basket basket){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Basket Details");

        String message = "Category: " + basket.getCategory() + "\n" +
                "Title: " + basket.getTitle() + "\n" +
                "Manufacturer: " + basket.getManufacturer() + "\n" +
                "Price: " + basket.getPrice();

        builder.setMessage(message);

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Save clothing details to Checkout under current user
                deleteFromBasket(basket);
            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing, just close the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void deleteFromBasket(Basket basket) {
        // Get current user ID
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        DatabaseReference basketRef = userRef.child("Basket");

        // Query to find the item with matching category
        Query query = basketRef.orderByChild("category").equalTo(basket.getCategory());

        // Execute the query to get the matching items
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Check if the snapshot matches all other criteria
                    Basket basketItem = snapshot.getValue(Basket.class);
                    if (basketItem.getTitle().equals(basket.getTitle()) &&
                            basketItem.getManufacturer().equals(basket.getManufacturer()) &&
                            basketItem.getPrice().equals(basket.getPrice())) {

                        // Get the unique identifier (key) of the basket item
                        String basketItemId = snapshot.getKey();

                        // Reference to the specific item in the basket under current user
                        DatabaseReference basketItemRef = basketRef.child(basketItemId);

                        // Remove the item from the database
                        basketItemRef.removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Item successfully deleted
                                        Toast.makeText(context, "Item deleted from basket", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Failed to delete the item
                                        Toast.makeText(context, "Failed to delete item from basket", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled
            }
        });
    }




}
