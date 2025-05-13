package SPRINT_1_DAY_1;

interface A {
    void methodA();
}

interface B {
    void methodB();
}

class MyClass implements A, B {
    public void methodA() {
        System.out.println("This is Method A");
    }

    public void methodB() {
        System.out.println("This is Method B");
    }
}

public class multipleInterface_23 {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.methodA();
        obj.methodB();
    }
}
