package nisum.SPRINT_3.DAY_1.Q6;

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        service.processPaymentMethod("CreditCard", "OFFER123"); 
        service.processPaymentMethod("Bitcoin", "OFFER123");     
        service.processPaymentMethod("UPI", "INVALIDCODE");      
        service.processPaymentMethod("Wallet", null);            
    }
}
