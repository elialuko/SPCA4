package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShippingDetails extends AppCompatActivity {

    TextView firstAddress, secondAddress, town, eircode;
    Button use;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_details);
        firstAddress = findViewById(R.id.firstAddress);
        secondAddress = findViewById(R.id.secondAddress);
        town = findViewById(R.id.town);
        eircode = findViewById(R.id.eircode);
        use = findViewById(R.id.use);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Address");

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot addressSnapshot : snapshot.getChildren()) {
                    String addressFirst = addressSnapshot.child("firstAddress").getValue(String.class);
                    String addressSecond = addressSnapshot.child("secondAddress").getValue(String.class);
                    String addressTown = addressSnapshot.child("town").getValue(String.class);
                    String addressEir = addressSnapshot.child("eircode").getValue(String.class);
                    firstAddress.setText(addressFirst);
                    secondAddress.setText(addressSecond);
                    town.setText(addressTown);
                    eircode.setText(addressEir);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShippingDetails.this, Payment.class);
                startActivity(i);
            }
        });
    }
}