package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.spca4.Interface.CardFactory;
import com.example.spca4.Interface.ClothesCardFactory;

public class AdminWelcome extends AppCompatActivity implements View.OnClickListener {

    CardView clothes, discount, customer, view;
    CardFactory cardFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);
        clothes = findViewById(R.id.clothes_card);
        discount = findViewById(R.id.discount_card);
        customer = findViewById(R.id.customer_card);
        view = findViewById(R.id.view_card);
        cardFactory = new ClothesCardFactory();

        clothes.setOnClickListener(this);
        discount.setOnClickListener(this);
        customer.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        String tag = (String) v.getTag();

        if (v.getId() == R.id.clothes_card) {
            CardView clothesCard = cardFactory.createCard(AdminWelcome.this);
            i = new Intent(AdminWelcome.this, Clothes.class);
            startActivity(i);
        } else if (v.getId() == R.id.discount_card) {
            CardView basketCard = cardFactory.createCard(AdminWelcome.this);
            i = new Intent(AdminWelcome.this, AdminDiscount.class);
            startActivity(i);
        }else if (v.getId() == R.id.customer_card) {
            CardView basketCard = cardFactory.createCard(AdminWelcome.this);
            i = new Intent(AdminWelcome.this, ViewCustomers.class);
            startActivity(i);
        }else if (v.getId() == R.id.view_card) {
            CardView basketCard = cardFactory.createCard(AdminWelcome.this);
            i = new Intent(AdminWelcome.this, List.class);
            startActivity(i);
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