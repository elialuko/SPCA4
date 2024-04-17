package com.example.spca4.Interface;

import android.content.Context;

import androidx.cardview.widget.CardView;

public class ViewClothingCardFactory implements CardFactory {
    @Override
    public CardView createCard(Context context) {
        CardView viewClothingCard = new CardView(context);
        return viewClothingCard;
    }
}
