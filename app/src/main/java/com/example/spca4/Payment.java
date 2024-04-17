package com.example.spca4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment extends AppCompatActivity {

    CardForm cardForm;
    Button payment;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cardForm = findViewById(R.id.card_form);
        payment = findViewById(R.id.payment);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(Payment.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardForm.isValid()){
                    createAlertDialog();
                } else {
                    Toast.makeText(Payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createAlertDialog() {
        // Create AlertDialog using Builder pattern
        alertBuilder = new AlertDialog.Builder(Payment.this)
                .setTitle("Confirm before purchase")
                .setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                        "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                        "Card CVV: " + cardForm.getCvv() + "\n" +
                        "Postal code: " + cardForm.getPostalCode() + "\n" +
                        "Phone number: " + cardForm.getMobileNumber())
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        clearBasket();
                        Toast.makeText(Payment.this, "Thank you for your purchase", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Payment.this, Welcome.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    private void clearBasket() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


        DatabaseReference basketRef = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(userId).child("Basket");


        basketRef.removeValue();
    }
}
