package SPRINT_1_DAY_1;

class Vehicle {
    public void tyre() {
        System.out.println("Vehicles has tyres");
    }
}

class Car extends Vehicle {
    @Override
    public void tyre() {
        System.out.println("Car has 4 tyres");
    }
}

public class methodOverriding_20 {
    public static void main(String[] args) {
        Vehicle v = new Car(); // Upcasting
        v.tyre(); // Runtime decision -> Dog's sound()
    }
}
