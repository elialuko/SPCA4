package com.example.spca4.Interface;

import android.content.Context;

import androidx.cardview.widget.CardView;

public class DiscountCardFactory implements CardFactory {
    @Override
    public CardView createCard(Context context) {
        CardView discountCard = new CardView(context);
        return discountCard;
    }
}
