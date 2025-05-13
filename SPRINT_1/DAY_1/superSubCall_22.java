package SPRINT_1_DAY_1;


class SuperClass {
    public void show() {
        System.out.println("SuperClass Method");
    }
}

class SubClass extends SuperClass {
    public void display() {
        System.out.println("SubClass Method");
    }
}

public class superSubCall_22 {
    public static void main(String[] args) {
        SuperClass sup = new SubClass(); 
        sup.show();                      
        if (sup instanceof SubClass) {
            ((SubClass) sup).display(); 
        }
    }
}
