package nisum.SPRINT_3.DAY_1.Q6;

import nisum.SPRINT_3.DAY_1.Q6.InvalidOfferAppliedException;

public class PaymentService {
    public void paymentMethod(String paymentType, String offerCode) throws PaymentException {
        if (!paymentType.equalsIgnoreCase("CreditCard") &&
            !paymentType.equalsIgnoreCase("UPI") &&
            !paymentType.equalsIgnoreCase("Wallet")) {
            throw new InvalidPaymentMethodException("Unsupported payment method: " + paymentType);
        }

        if (offerCode != null && !offerCode.matches("OFFER\\d{3}")) {
            throw new InvalidOfferAppliedException("Invalid offer code: " + offerCode);
        }

        System.out.println("Payment successful using " + paymentType +
                (offerCode != null ? " with offer " + offerCode : ""));
    }

    public void processPaymentMethod(String paymentType, String offerCode) {
        try {
            paymentMethod(paymentType, offerCode);
        } catch (PaymentException e) {
            // Pattern matching with instance-of (Java 16+)
            if (e instanceof InvalidPaymentMethodException ipme) {
                System.out.println("Payment Failed: " + ipme.getMessage());
            } else if (e instanceof InvalidOfferAppliedException ioae) {
                System.out.println("Payment Failed: " + ioae.getMessage());
            } else {
                System.out.println("Unknown Payment Error: " + e.getMessage());
            }
        }
    }
}


