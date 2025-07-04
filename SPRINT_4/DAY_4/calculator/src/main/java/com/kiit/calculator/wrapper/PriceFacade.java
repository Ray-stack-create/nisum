package com.kiit.calculator.wrapper;

import com.kiit.calculator.logic.PriceCalculator;

public class PriceFacade {
    private final PriceCalculator calculator;

    public PriceFacade(PriceCalculator calculator) {
        this.calculator = calculator;
    }

    public double getPrice(double amount) {
        try {
            return calculator.getPrice(amount);
        } catch (ArithmeticException ex) {
            return -1.0;
        }
    }
}

