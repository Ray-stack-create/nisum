package SPRINT_1_DAY_1;

public class reverseString_38 {
    public static void main(String[] args) {
        String original = "Hello from World";

        StringBuffer sb = new StringBuffer(original);
        String rev = sb.reverse().toString();

        System.out.println("Original String: " + original);
        System.out.println("Reversed String: " + rev);
    }
}
