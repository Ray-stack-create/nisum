package SPRINT_1_DAY_2;

import java.util.Scanner;
import java.util.Stack;

public class BrowserHistory_5 {
    private Stack<String> history;
    private String currentPage;

    public BrowserHistory_5() {
        history = new Stack<>();
        currentPage = "Home"; 
    }


    public void visit(String url) {
        if (currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;
        System.out.println("Visited: " + url);
    }


    public void goBack() {
        if (!history.isEmpty()) {
            currentPage = history.pop();
            System.out.println("Went back to: " + currentPage);
        } else {
            System.out.println("No history to go back to.");
        }
    }
    public void viewCurrentPage() {
        System.out.println("Current Page: " + currentPage);
    }

    public void viewHistory() {
        if (history.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            System.out.println("\nBrowser History:");
            for (int i = history.size() - 1; i >= 0; i--) {
                System.out.println(history.get(i));
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BrowserHistory_5 browser = new BrowserHistory_5();
        int choice;

        do {
            System.out.println("1. Visit new site");
            System.out.println("2. Go back");
            System.out.println("3. View current page");
            System.out.println("4. View entire history");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter URL to visit: ");
                    String url = sc.nextLine();
                    browser.visit(url);
                    break;

                case 2:
                    browser.goBack();
                    break;

                case 3:
                    browser.viewCurrentPage();
                    break;

                case 4:
                    browser.viewHistory();
                    break;

                case 5:
                    System.out.println("Exiting browser...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
