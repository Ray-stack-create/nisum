package SPRINT_1_DAY_1;

public class methodOverloading_19 {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        methodOverloading_19 obj = new methodOverloading_19();
        System.out.println("Sum of 2 Integers: " + obj.add(2, 3));
        System.out.println("Sum of 2 doubles: " + obj.add(57.4, 33.5));
        System.out.println("Sum of 3 Integers : " + obj.add(1, 2, 3));
    }
}
