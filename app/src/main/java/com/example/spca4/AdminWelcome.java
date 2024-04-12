package com.example.spca4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminWelcome extends AppCompatActivity implements View.OnClickListener {

    CardView clothes, discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        clothes = findViewById(R.id.clothes_card);
        discount = findViewById(R.id.discount_card);

        clothes.setOnClickListener(this);
        discount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        String tag = (String) v.getTag();

        switch (tag) {
            case "clothes":
                i = new Intent(AdminWelcome.this, Clothes.class);
                startActivity(i);
                break;
            case "discount":
                i = new Intent(AdminWelcome.this, AdminDiscount.class);
                startActivity(i);
                break;
        }
    }
}