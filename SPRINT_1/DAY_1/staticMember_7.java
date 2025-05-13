package SPRINT_1_DAY_1;

public class staticMember_7 {
    static int a = initializeA();  // Step 1
    static int b;

    static {
        System.out.println("Inside Static Block 1");
        b = 20;
    }

    static {
        System.out.println("Inside Static Block 2");
        System.out.println("a = "+a+ ", b = "+b);
    }

    static int initializeA() {
        System.out.println("First Static variable is executed");
        return 10;
    }

    public static void main(String[] args) {
        System.out.println("Inside main()");
        System.out.println("Final a = " +a+ ", b = "+b);
    }
}
