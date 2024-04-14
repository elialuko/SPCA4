package com.example.spca4.Interface;

import com.example.spca4.Discount;

public class InvalidCodeCommand implements DiscountCommand {
    private Discount activity;

    public InvalidCodeCommand(Discount activity) {
        this.activity = activity;
    }

    @Override
    public void execute() {
        activity.displayInvalidCodeMessage();
    }
}
