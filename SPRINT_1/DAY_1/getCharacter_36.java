package SPRINT_1_DAY_1;

import java.util.Scanner;

public class getCharacter_36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        System.out.print("Enter index: ");
        int index = sc.nextInt();

        if (index >= 0 && index < s.length()) {
            char ch = s.charAt(index);
            System.out.println("Character at index "+index+": "+ch);
        } else {
            System.out.println("Invalid index. Must be between 0 and "+(s.length()-1));
        }

        sc.close();
    }
}
