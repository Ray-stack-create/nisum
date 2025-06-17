package nisum.SPRINT_3.DAY_1.Q6;
import nisum.SPRINT_3.DAY_1.Q6.PaymentException;
public final class InvalidPaymentMethodException extends PaymentException {
    public InvalidPaymentMethodException(String message) {
        super(message);
    }
}