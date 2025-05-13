package SPRINT_1_DAY_1;


public class objectMethod_2 {
    String name;

    public objectMethod_2(String name){
        this.name=name;
    }
    public static void main(String[] args) {
        objectMethod_2 obj1 = new objectMethod_2("Sankha");
        objectMethod_2 obj2 = new objectMethod_2("Sankha");
        objectMethod_2 obj3 = obj1;

        System.out.println("toString(): "+obj1.toString());
        
        System.out.println("equals(): "+obj1.equals(obj2));
        System.out.println("equals(): "+obj1.equals(obj3));

        System.out.println("Hashcode for obj1: " + obj1.hashCode());
        System.out.println("Hashcode for obj2: " + obj2.hashCode());
        System.out.println("Hashcode for obj3: " + obj3.hashCode());
    }
}
