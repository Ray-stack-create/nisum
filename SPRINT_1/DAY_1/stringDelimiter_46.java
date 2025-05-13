package SPRINT_1_DAY_1;



public class stringDelimiter_46 {
    public static void main(String[] args) {
        String s = "bus,car,bike,man,tree";

        String[] words = s.split(",");

        for (String word : words) {
            System.out.println(word);
        }
    }
}
