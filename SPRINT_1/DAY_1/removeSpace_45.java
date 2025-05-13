package SPRINT_1_DAY_1;


public class removeSpace_45 {
    public static void main(String[] args) {
        String s = "Java   is   awesome   and   very usefull  !";
        String result = s.replaceAll("\\s+", " ");

        System.out.println("Before: " + s);
        System.out.println("After: " + result);
    }
}
