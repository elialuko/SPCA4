package com.example.spca4.Interface;

import com.example.spca4.Discount;

public class ApplyDiscountCommand implements DiscountCommand {
    private Discount activity;

    public ApplyDiscountCommand(Discount activity) {
        this.activity = activity;
    }

    @Override
    public void execute() {
        activity.applyDiscount();
    }
}
