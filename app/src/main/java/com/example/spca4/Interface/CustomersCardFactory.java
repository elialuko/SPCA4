package com.example.spca4.Interface;

import android.content.Context;

import androidx.cardview.widget.CardView;

public class CustomersCardFactory implements CardFactory {
    @Override
    public CardView createCard(Context context) {
        CardView customersCard = new CardView(context);
        return customersCard;
    }
}
