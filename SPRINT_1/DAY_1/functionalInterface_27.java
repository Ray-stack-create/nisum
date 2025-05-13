package SPRINT_1_DAY_1;


interface Calculate {
    int operate(int a, int b);
}

public class functionalInterface_27 {
    public static void main(String[] args) {
        Calculate add = (a, b) -> a + b;
        System.out.println("Sum: " + add.operate(456, 234));
    }
}






