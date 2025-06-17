package nisum.SPRINT_3.DAY_1.TicketBooking;
import nisum.SPRINT_3.DAY_1.TicketBooking.TicketBooking;
import nisum.SPRINT_3.DAY_1.TicketBooking.BookingThread;

public class Main {
    public static void main(String[] args) {
        TicketBooking booking = new TicketBooking();

        BookingThread t1 = new BookingThread(booking, "Alice");
        BookingThread t2 = new BookingThread(booking, "Bob");

        t1.start();
        t2.start();
    }
}

