package SPRINT_1_DAY_2;
import java.util.HashSet;
import java.util.Scanner;

public class duplicateRemove_3 {
    public static void main(String[] args) {

        HashSet<String> emailSet = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter email addresses (type 'done' to finish):");

        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            if (email.equalsIgnoreCase("done")) {
                break;
            }

            if (emailSet.add(email.toLowerCase())) {
                System.out.println("Added: " + email);
            } else {
                System.out.println("Duplicate email ignored.");
            }
        }
        System.out.println("\nUnique Email Addresses:");
        for (String uniqueEmail : emailSet) {
            System.out.println(uniqueEmail);
        }

        scanner.close();
    }
}
