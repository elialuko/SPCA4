package com.example.spca4.Interface;

import android.content.Context;

import androidx.cardview.widget.CardView;

public class BasketCardFactory implements CardFactory {
    @Override
    public CardView createCard(Context context) {
        // Create and configure basket card
        CardView basketCard = new CardView(context);
        // Configure other properties of the card
        return basketCard;
    }
}
