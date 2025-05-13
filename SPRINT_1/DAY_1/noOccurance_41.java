package SPRINT_1_DAY_1;

import java.util.HashMap;
import java.util.Map;

public class noOccurance_41 {
    public static void main(String[] args) {
        String input = "Super Man Bat Man Spider Man";
        input = input.replace(" ", ""); 

        Map<Character, Integer> freqMap = new HashMap<>();

        for (char c : input.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        System.out.println("Character frequencies:");
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}
