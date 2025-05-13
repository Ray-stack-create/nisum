package SPRINT_1_DAY_1;

class Vehicle{
    String brand="Vehicle";
    int wheels;
    void display(){
        System.out.println("Brand: "+brand);
        System.out.println("Wheels: "+wheels);
    }
}

class Car extends Vehicle{
    Car(){
        wheels=4;
        brand="Car";
    }
}

class Bus extends Vehicle{
    Bus(){
        wheels=6;
        brand="Bus";
    }
}

public class vehicleInfo_14 {
    public static void main(String[] args) {
    Car c1 = new Car();
    Bus b1 = new Bus();

    c1.display();
    b1.display();   
    }
}
