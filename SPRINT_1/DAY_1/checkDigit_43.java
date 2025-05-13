package SPRINT_1_DAY_1;


public class checkDigit_43 {
    public static void main(String[] args) {
        String input = "123456";

        if (input.matches("\\d+")) {
            System.out.println("The string contains only digits.");
        } else {
            System.out.println("The string contains non-digit characters.");
        }
    }
}
