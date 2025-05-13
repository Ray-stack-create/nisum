package SPRINT_1_DAY_1;


interface first {
    void firstMethod();

    interface second {
        void secondMethod();
    }
}

class NestedImpl implements first.second {
    public void secondMethod() {
        System.out.println("Inner interface method");
    }
}

public class nestedInterface_25 {
    public static void main(String[] args) {
        first.second obj = new NestedImpl();
        obj.secondMethod();
    }
}
