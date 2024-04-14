package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.spca4.Interface.CardFactory;
import com.example.spca4.Interface.ClothesCardFactory;

public class Welcome extends AppCompatActivity implements View.OnClickListener{
    CardView clothes, basket;
    CardFactory cardFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        clothes = findViewById(R.id.clothes_card);
        basket = findViewById(R.id.basket_card);
        cardFactory = new ClothesCardFactory();

        clothes.setOnClickListener(this);
        basket.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        String tag = (String) v.getTag();

        if (v.getId() == R.id.clothes_card) {
            CardView clothesCard = cardFactory.createCard(Welcome.this);
            i = new Intent(Welcome.this, List.class);
            startActivity(i);
        } else if (v.getId() == R.id.basket_card) {
            CardView basketCard = cardFactory.createCard(Welcome.this);
            i = new Intent(Welcome.this, Checkout.class);
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
            // Handle logout action here
            Intent intent = new Intent(Welcome.this, Logout.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}