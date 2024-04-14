package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AdminWelcome extends AppCompatActivity implements View.OnClickListener {

    CardView clothes, discount, customer, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        clothes = findViewById(R.id.clothes_card);
        discount = findViewById(R.id.discount_card);
        customer = findViewById(R.id.customer_card);
        view = findViewById(R.id.view_card);

        clothes.setOnClickListener(this);
        discount.setOnClickListener(this);
        customer.setOnClickListener(this);
        view.setOnClickListener(this);
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
            case "customer":
                i = new Intent(AdminWelcome.this, ViewCustomers.class);
                startActivity(i);
                break;
            case "view":
                i = new Intent(AdminWelcome.this, List.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Intent intent = new Intent(AdminWelcome.this, Logout.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}