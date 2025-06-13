package nisum.SPRINT_3.DAY_1.Q6;

import nisum.SPRINT_3.DAY_1.Q6.PaymentException;

public final class InvalidOfferAppliedException extends PaymentException {
    public InvalidOfferAppliedException(String message) {
        super(message);
    }
}