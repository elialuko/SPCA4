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

public class Welcome extends AppCompatActivity implements View.OnClickListener{
    CardView clothes, basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        clothes = findViewById(R.id.clothes_card);
        basket = findViewById(R.id.basket_card);

        clothes.setOnClickListener(this);
        basket.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        String tag = (String) v.getTag();

        switch (tag) {
            case "clothes":
                i = new Intent(Welcome.this, List.class);
                startActivity(i);
                break;
            case "basket" : i = new Intent(Welcome.this, Checkout.class);
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
            // Handle logout action here
            Intent intent = new Intent(Welcome.this, Logout.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}