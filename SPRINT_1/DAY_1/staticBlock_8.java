package SPRINT_1_DAY_1;

public class staticBlock_8 {
    static String s;

    static{
        System.out.println("This is static block");
        s="abc";
    }
    public static void main(String[] args) {
        System.out.println("Main Method is executed");
        System.out.println("Value of S is "+s);
    }
}
