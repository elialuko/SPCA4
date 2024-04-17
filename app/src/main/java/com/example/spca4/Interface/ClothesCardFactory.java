package com.example.spca4.Interface;

import android.content.Context;

import androidx.cardview.widget.CardView;

public class ClothesCardFactory implements CardFactory {
    @Override
    public CardView createCard(Context context) {
        CardView clothesCard = new CardView(context);
        return clothesCard;
    }
}

