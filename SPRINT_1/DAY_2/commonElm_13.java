package SPRINT_1_DAY_2;
import java.util.*;

public class commonElm_13 {
    public static <T> List<T> findCommon(List<T> list1, List<T> list2) {
        Set<T> set1 = new HashSet<>(list1);
        List<T> result = new ArrayList<>();

        for (T item : list2) {
            if (set1.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        System.out.println("Common Elements: " + findCommon(list1, list2));
    }
}
