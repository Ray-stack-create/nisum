package com.kiit.calculator.logic;

public class PriceCalculator {
    private double calculateTax(double amount) {
        return amount * 0.15;
    }

    public double getPrice(double amount) {
        double tax = calculateTax(amount);
        return amount + tax;
    }
}

