package com.example.spca4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        finish();
        Toast.makeText(Logout.this, "Logged Out", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}