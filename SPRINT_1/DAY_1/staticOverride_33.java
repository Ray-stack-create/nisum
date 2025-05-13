package SPRINT_1_DAY_1;

class Parent {
    static void show() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Static method in Child");
    }
}

public class staticOverride_33 {
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.show();  // Output: Static method in Parent
    }
}
