package SPRINT_1_DAY_1;

public class staticMethodOverload_10 {

    public static void print(){
        System.out.println("This is a Method with no Parameters");
    }

    public static void print(String s){
        System.out.println("This is a Method with String Paramenter "+s);
    }

    public static void print(String s, int num){
        System.out.println("This is a Method with two parameters "+s+" and "+num);
    }

    public static void main(String[] args) {
        print();
        print("Sankha");
        print("Sankha",23);
    }
}