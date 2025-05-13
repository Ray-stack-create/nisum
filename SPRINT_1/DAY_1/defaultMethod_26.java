package SPRINT_1_DAY_1;

interface A {
    default void print() {
        System.out.println("Hello from default method");
    }
}

class B implements A {
   
}

public class defaultMethod_26 {
    public static void main(String[] args) {
        A a = new B();
        a.print();
    }
}
