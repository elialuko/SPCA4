package com.example.spca4.Interface;

import android.content.Context;

import androidx.cardview.widget.CardView;

public class ClothesCardFactory implements CardFactory {
    @Override
    public CardView createCard(Context context) {
        // Create and configure clothes card
        CardView clothesCard = new CardView(context);
        // Configure other properties of the card
        return clothesCard;
    }
}
