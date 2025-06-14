package nisum.SPRINT_3.DAY_1.TicketBooking;
import nisum.SPRINT_3.DAY_1.TicketBooking.TicketBooking;

class BookingThread extends Thread {
    private TicketBooking booking;
    private String customerName;

    public BookingThread(TicketBooking booking, String customerName) {
        this.booking = booking;
        this.customerName = customerName;
    }

    public void run() {
        booking.bookTicket(customerName);
    }
}
