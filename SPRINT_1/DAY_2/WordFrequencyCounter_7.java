package SPRINT_1_DAY_2;

import java.util.*;

public class WordFrequencyCounter_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = sc.nextLine();
        input = input.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        String[] words = input.split("\\s+");
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordMap.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue())); 
        System.out.println("\nWord Frequencies (sorted by frequency):");
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        sc.close();
    }
}
