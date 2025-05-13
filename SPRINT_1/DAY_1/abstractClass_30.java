package SPRINT_1_DAY_1;

abstract class Shape {
    int sides;

    Shape(int sides) {
        this.sides = sides;
        System.out.println("This is Shape constructor");
    }

    abstract void draw();

    void displaySides() {
        System.out.println("Sides: " + sides);
    }
}

class Square extends Shape {
    Square() {
        super(4);
    }

    void draw() {
        System.out.println("Drawing square");
    }
}

public class abstractClass_30 {
    public static void main(String[] args) {
        Shape s = new Square();
        s.draw();
        s.displaySides();
    }
}
