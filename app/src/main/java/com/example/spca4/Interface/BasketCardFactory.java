package com.example.spca4.Interface;

import android.content.Context;

import androidx.cardview.widget.CardView;

public class BasketCardFactory implements CardFactory {
    @Override
    public CardView createCard(Context context) {
        CardView basketCard = new CardView(context);
        return basketCard;
    }
}
