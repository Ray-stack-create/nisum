package SPRINT_1_DAY_1;


public class staticOrder_13 {
    
    static {
        System.out.println("Static Block Executed");
    }

    public static void staticMethod() {
        System.out.println("Static Method Executed");
    }

    public static void main(String[] args) {
        System.out.println("Inside Main method");
        staticMethod();
    }
}
