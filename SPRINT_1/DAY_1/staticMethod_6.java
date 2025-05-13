package SPRINT_1_DAY_1;

class Parent{
    public static void print(){
        System.out.println("Hello from Parent Class");
    }
}


class child extends Parent{
    public void ParentMethod(){
        Parent.print();
    }
}

public class staticMethod_6{
    public static void main(String[] args) {
        child obj = new child();
        obj.ParentMethod();
        Parent.print();
        child.print();
    }
}