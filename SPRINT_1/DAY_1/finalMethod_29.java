package SPRINT_1_DAY_1;


class Animal {
    final void sound() {
        System.out.println("Animal Produces sound");
    }

    void eat() {
        System.out.println("Animal Eat food");
    }
}

class Dog extends Animal {
    // Cannot override 
}

public class finalMethod_29 {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.sound();
        d.eat();
    }
}

