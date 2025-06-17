package nisum.SPRINT_3.DAY_1;

public class Division {
    public static int divide(int num1, int num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return num1 / num2;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {10, 2},
            {15, 0},
            {20, 5},
            {9, 3},
            {8, 0}
        };

        for (int[] testCase : testCases) {
            int num = testCase[0];
            int den = testCase[1];
            try {
                int result = divide(num, den);
                System.out.println(num + " / " + den + " = " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage() + " for inputs " + num + " and " + den);
            }
        }
    }
}
