package com.example.spca4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Clothes extends AppCompatActivity {
    EditText manufacturer, price, title, quantity;
    Button add;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        manufacturer = findViewById(R.id.manufacturer);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        add = findViewById(R.id.btnAdd);

        Spinner mySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(Clothes.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.category));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        category = mySpinner.getSelectedItem().toString();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textManufacturer = manufacturer.getText().toString();
                String textTitle = title.getText().toString();
                String textPrice = price.getText().toString();
                String textQuantity = quantity.getText().toString();

                if (TextUtils.isEmpty(textManufacturer)){
                    Toast.makeText(Clothes.this, "Please Enter a Manufacturer", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(textTitle)){
                    Toast.makeText(Clothes.this, "Please Enter a Title", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(textPrice)){
                    Toast.makeText(Clothes.this, "Please Enter a Price", Toast.LENGTH_LONG).show();
                }else{
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                    DatabaseReference clothesRef = userRef.child("Clothes");
                    String clothesID = clothesRef.push().getKey();
                    Map<String, Object> clothesData = new HashMap<>();
                    category = mySpinner.getSelectedItem().toString();
                    clothesData.put("title",textTitle);
                    clothesData.put("manufacturer",textManufacturer);
                    clothesData.put("price",textPrice);
                    clothesData.put("category",category);
                    clothesData.put("quantity",textQuantity);
                    clothesRef.child(clothesID).setValue(clothesData);


                    Intent intent = new Intent(Clothes.this, AdminWelcome.class);
                    startActivity(intent);
                }
            }
        });
    }
}