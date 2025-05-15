package SPRINT_1_DAY_2;
import java.util.*;

class Event {
    private String title;
    private String time;
    private String description;

    public Event(String title, String time, String description) {
        this.title = title;
        this.time = time;
        this.description = description;
    }

    public String toString() {
        return time + " - " + title + ": " + description;
    }
}

public class eventCalender_9 {
    private TreeMap<String, List<Event>> calendar;

    public eventCalender_9() {
        calendar = new TreeMap<>();
    }

    public void addEvent(String date, String title, String time, String description) {
        Event event = new Event(title, time, description);
        calendar.putIfAbsent(date, new ArrayList<>());
        calendar.get(date).add(event);
        System.out.println("Event added on " + date);
    }

    public void removeEvent(String date, String title) {
        if (calendar.containsKey(date)) {
            List<Event> events = calendar.get(date);
            boolean removed = events.removeIf(e -> e.toString().contains(title));
            if (removed) {
                System.out.println("Event removed.");
            } else {
                System.out.println("Event not found.");
            }
            if (events.isEmpty()) {
                calendar.remove(date);
            }
        } else {
            System.out.println("No events found for the date.");
        }
    }

    public void displayEventsForDate(String date) {
        if (calendar.containsKey(date)) {
            System.out.println("\nEvents on " + date + ":");
            for (Event event : calendar.get(date)) {
                System.out.println(event);
            }
        } else {
            System.out.println("No events scheduled for this date.");
        }
    }

    public void displayAllUpcomingEvents() {
        if (calendar.isEmpty()) {
            System.out.println("No upcoming events.");
        } else {
            System.out.println("\nAll Upcoming Events:");
            for (Map.Entry<String, List<Event>> entry : calendar.entrySet()) {
                System.out.println("\nDate: " + entry.getKey());
                for (Event event : entry.getValue()) {
                    System.out.println(event);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        eventCalender_9 ec = new eventCalender_9();
        int choice;

        do {
            System.out.println("1. Add Event");
            System.out.println("2. Remove Event");
            System.out.println("3. View Events for a Date");
            System.out.println("4. View All Upcoming Events");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter event date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter time: ");
                    String time = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    ec.addEvent(date, title, time, desc);
                    break;

                case 2:
                    System.out.print("Enter event date (yyyy-mm-dd): ");
                    String remDate = sc.nextLine();
                    System.out.print("Enter event title to remove: ");
                    String remTitle = sc.nextLine();
                    ec.removeEvent(remDate, remTitle);
                    break;

                case 3:
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String viewDate = sc.nextLine();
                    ec.displayEventsForDate(viewDate);
                    break;

                case 4:
                    ec.displayAllUpcomingEvents();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
