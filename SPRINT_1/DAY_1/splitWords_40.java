package SPRINT_1_DAY_1;


public class splitWords_40 {
    public static void main(String[] args) {
        String sentence = "Hi there lets have talk";

        String[] words = sentence.split(" ");  

        System.out.println("Words in the sentence are:");
        for (String word : words) {
            System.out.println(word);
        }
    }
}

