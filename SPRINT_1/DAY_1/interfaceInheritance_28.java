package SPRINT_1_DAY_1;


interface Animal {
    void eat();
}

interface Cat extends Animal {
    void meow();
}

class Kitten implements Cat {
    public void eat() {
        System.out.println("Kitten eats");
    }

    public void meow() {
        System.out.println("Kitten Meow");
    }
}

public class interfaceInheritance_28 {
    public static void main(String[] args) {
        Kitten k = new Kitten();
        k.eat();
        k.meow();
    }
}

