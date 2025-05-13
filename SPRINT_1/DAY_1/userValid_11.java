package SPRINT_1_DAY_1;

public class userValid_11 {
    public static boolean isValidEmail(String email){
        String emailReg = "^[\\w.-]+@[\\w.-]+\\.\\w+$";  //using regex to match the email format
        return email.matches(emailReg);
    }

    public static boolean isValidPass(String pass){
        String passReg = "^(?=.[A-Za-z])(?=.\\d)(?=.*[@#$%^&+=!]).{6,}$";
        return pass.matches(passReg);
    }

    public static void main(String[] args) {
        String username = "sankha@gmail.com";
        String pass = "Hello@123";

        if (isValidEmail(username) && isValidPass(pass)) {
            System.out.println("Valid credentials");
        } else {
            System.out.println("Invalid credentials");
        }

    }
}
