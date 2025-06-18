package com.nisum.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(10, calculator.add(6, 4));
        assertNotEquals(11, calculator.add(6, 4));
    }

    @Test
    void testSubtraction() {
        assertEquals(2, calculator.subtract(6, 4));
        assertNotEquals(3, calculator.subtract(6, 4));
    }

    @Test
    void testMultiplication() {
        assertEquals(24, calculator.multiply(6, 4));
        assertNotEquals(25, calculator.multiply(6, 4));
    }

    @Test
    void testDivision() {
        assertEquals(2, calculator.divide(8, 4));
        assertNotEquals(3, calculator.divide(8, 4));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(8, 0));
    }
}

