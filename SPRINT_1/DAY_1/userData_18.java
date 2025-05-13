package SPRINT_1_DAY_1;

class User {
    private static User instance;
    private String name;
    private int age;

    private User() {
        this.name = "Sankha";
        this.age = 21;
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void display() {
        System.out.println("Name: " +name +", Age: "+age);
    }
}


public class userData_18 {
    public static void main(String[] args) {
        User user = User.getInstance();
        user.display();
    }
}