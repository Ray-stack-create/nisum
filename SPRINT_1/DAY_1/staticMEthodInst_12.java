package SPRINT_1_DAY_1;

public class staticMEthodInst_12 {
    int a=20;
    public static void print(){
        // System.out.println(a);   compiltion error
        System.out.println("Static Method can't access instance variable directly");

    }

    public static void main(String[] args) {

        print();
    }
}


//conclusion you nees an object to access instance variable
