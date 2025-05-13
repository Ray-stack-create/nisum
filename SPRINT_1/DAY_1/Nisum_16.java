package SPRINT_1_DAY_1;

class Nisum{
    String name = "Nisum Technology";
}

class Employee extends Nisum{
    void display(){
        System.out.println("Company name: "+name);
    }

}

public class Nisum_16{
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.display();
    }
}
