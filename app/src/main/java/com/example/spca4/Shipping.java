package com.example.spca4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Shipping extends AppCompatActivity {
    TextView firstAddress, secondAddress, town, eircode;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        firstAddress = findViewById(R.id.firstAddress);
        secondAddress = findViewById(R.id.firstAddress);
        town = findViewById(R.id.town);
        eircode = findViewById(R.id.eircode);
        save = findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtFirstAddress = firstAddress.getText().toString();
                String txtSecondAddress = secondAddress.getText().toString();
                String txtTown = town.getText().toString();
                String txtEircode = eircode.getText().toString();
                if (TextUtils.isEmpty(txtFirstAddress)){
                    Toast.makeText(Shipping.this, "Please Enter an Address", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(txtSecondAddress)){
                    Toast.makeText(Shipping.this, "Please Enter an Address", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(txtTown)){
                    Toast.makeText(Shipping.this, "Please Enter a Town", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(txtEircode)){
                    Toast.makeText(Shipping.this, "Please Enter an Eircode", Toast.LENGTH_LONG).show();
                }else{
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                    DatabaseReference shippingRef = userRef.child("Address");
                    String shippingID = shippingRef.push().getKey();
                    Map<String, Object> shippingData = new HashMap<>();
                    shippingData.put("firstAddress",txtFirstAddress);
                    shippingData.put("secondAddress",txtSecondAddress);
                    shippingData.put("town",txtTown);
                    shippingData.put("eircode",txtEircode);
                    shippingRef.child(shippingID).setValue(shippingData);
                    Toast.makeText(Shipping.this, "Address Saved", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Shipping.this, Welcome.class);
                    startActivity(i);
                }
            }
        });
    }
}