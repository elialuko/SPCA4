package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {

    RecyclerView recyclerView;
    Button checkout;
    ArrayList<Basket> list;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    TextView total;
    FirebaseDatabase database;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        recyclerView = findViewById(R.id.recyclerview);
        checkout = findViewById(R.id.checkout);
        total = findViewById(R.id.total);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, list);
        recyclerView.setAdapter(adapter);
        database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Users");
        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DatabaseReference userRef2 = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("Basket");

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists() && snapshot.getChildrenCount() > 0){
                            Intent i = new Intent(Checkout.this, Discount.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(Checkout.this, "Your basket is empty", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot reportSnapshot : dataSnapshot.child("Basket").getChildren()) {
                        Basket basket = reportSnapshot.getValue(Basket.class);
                        list.add(basket);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        userRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double totalAmount = 0.0;
                for (DataSnapshot basketSnapshot : snapshot.getChildren()) {
                    try {
                        String priceString = basketSnapshot.child("price").getValue(String.class);
                        Double price = Double.valueOf(priceString);
                        if (price != null) {
                            totalAmount += price;
                        } else {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                    total.setText("€" + String.format("%.2f", totalAmount));
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
