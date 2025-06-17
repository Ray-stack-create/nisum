package nisum.SPRINT_3.DAY_1.TicketBooking;

class TicketBooking {
    private int availableTickets = 1; 

    public synchronized void bookTicket(String customerName) {
        if (availableTickets > 0) {
            System.out.println(customerName + " is booking a ticket...");
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            availableTickets--;
            System.out.println(customerName + " successfully booked the ticket!");
        } else {
            System.out.println(customerName + " failed to book. Ticket already booked.");
        }
    }
}
