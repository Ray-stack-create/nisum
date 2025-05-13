package SPRINT_1_DAY_1;
import java.util.*;
public class UserDetails_5{
    public String name;
    public String Id;
    public String email;
    private int creditBalance;
    private String creditId;
    private String password;
    //Constructor
    public UserDetails_5(String name, String email, String Id, String password, int creditBalance, String creditId){
        this.name=name;
        this.email=email;
        this.Id=Id;
        this.password=password;
        this.creditBalance = creditBalance;
        this.creditId = creditId;
    }

    public void printDetails(){
        System.out.println("User Name: "+name);
        System.out.println("User Id: "+Id);
        System.out.println("User Email: "+email);
        
    }

    public static void main(String[] args) {
        UserDetails_5 user1 = new UserDetails_5("Snakhadip", "sankha@gmial","12A", "brluga8", 50000, "67ASD4");
        UserDetails_5 user2 = new UserDetails_5("Sohani", "sohani@gmail","56R", "jg6gv##", 98770, "ASD443");

        user1.printDetails();
        user2.printDetails();
    }
}