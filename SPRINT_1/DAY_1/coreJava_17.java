package SPRINT_1_DAY_1;

class Parent {
    public int a = 1;
    protected int b = 2;
    int c = 3;
    private int d = 4;

    public void show() {
        System.out.println("Parent class show method");
        System.out.println("Public variable: " + a);
        System.out.println("Protected variable: " + b);
        System.out.println("Default variable: " + c);
        System.out.println("Private variable: " + d);
    }
}

class Child extends Parent {
    static {
        System.out.println("This is Static block in Child class");
    }

    public void display() {
        System.out.println("Child class display method");
        
        System.out.println("Public variable : " + a);
        System.out.println("Protected variable: " + b);
        System.out.println("Default variable: " + c);
        
    }
}

public class coreJava_17 {
    public static void main(String[] args) {
        Child obj = new Child();
        obj.show();
        obj.display();
    }
}
