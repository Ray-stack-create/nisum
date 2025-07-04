package com.kiit.calculator.wrapper;

import com.kiit.calculator.logic.PriceCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceFacadeTest {

    @Spy
    private PriceCalculator calculator = new PriceCalculator();

    private PriceFacade facade;

    @Before
    public void setup() {
        facade = new PriceFacade(calculator);
    }

    @Test
    public void testCalculateTaxThrowsException_returnsDefault() throws Exception {

        doThrow(new ArithmeticException("Divide by zero"))
                .when(calculator).getPrice(anyDouble());

        double price = facade.getPrice(100);

        assertEquals(-1.0, price, 0.001);

        verify(calculator).getPrice(100);
    }

    @Test
    public void testSpyReset_andRealMethodInvocation() {

        doThrow(new ArithmeticException("Error")).when(calculator).getPrice(anyDouble());

        double price1 = facade.getPrice(200);
        assertEquals(-1.0, price1, 0.001);

        reset(calculator);


        double price2 = facade.getPrice(200);
        
        assertEquals(230.0, price2, 0.001);
    }
}
