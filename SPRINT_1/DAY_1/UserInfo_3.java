package SPRINT_1_DAY_1;
import java.util.*;
public class UserInfo_3{
    private String name;
    private int age;
    private String email;
    private String empId;

    //Constructor
    public UserInfo_3(String name, int age, String email, String empId){
        this.name=name;
        this.age=age;
        this.email=email;
        this.empId=empId;
    }

    public void printDetails(){
        System.out.println("User Name: "+name);
        System.out.println("User Age: "+age);
        System.out.println("User Emial: "+email);
        System.out.println("User EmpId: "+empId);
    }

    public static void main(String[] args) {
        UserInfo_3 user1 = new UserInfo_3("Snakhadip", 21, "sankha@gmial", "12A");
        UserInfo_3 user2 = new UserInfo_3("Sohani", 21, "sohani@gmail", "23Q");

        user1.printDetails();
        user2.printDetails();
    }
}