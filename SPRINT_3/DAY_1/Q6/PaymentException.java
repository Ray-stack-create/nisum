package nisum.SPRINT_3.DAY_1.Q6;

import nisum.SPRINT_3.DAY_1.Q6.InvalidOfferAppliedException;

public sealed class PaymentException extends Exception
        permits InvalidPaymentMethodException, InvalidOfferAppliedException {

    public PaymentException(String message) {
        super(message);
    }
}