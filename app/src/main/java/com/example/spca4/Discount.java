package com.example.spca4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Discount extends AppCompatActivity {
    EditText code;
    Button apply, btnContinue;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        code = findViewById(R.id.inputCode);
        apply = findViewById(R.id.btnSave);
        btnContinue = findViewById(R.id.continueButton);
        database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("Users");

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredCode = code.getText().toString();

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean codeMatch = false;
                        for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                            DataSnapshot codesSnapshot = userSnapshot.child("Codes");
                            for (DataSnapshot codeSnapshot : codesSnapshot.getChildren()){
                                String code = codeSnapshot.child("code").getValue(String.class);
                            if (code != null && code.equals(enteredCode)) {
                                codeMatch = true;
                                break;
                            }
                        }
                            if (codeMatch) {
                                // Code match found
                                break;
                            }
                        }
                        if (codeMatch) {
                            // Code match found
                            Toast.makeText(Discount.this, "Code applied successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Code not found
                            Toast.makeText(Discount.this, "Invalid code", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Discount.this, Shipping.class);
                startActivity(i);
            }
        });
    }
}