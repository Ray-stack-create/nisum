package SPRINT_1_DAY_2;
import java.util.TreeSet;

public class sortedElem_12 {
    public static TreeSet<Integer> getSortedUnique(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : arr) {
            set.add(num);
        }
        return set;
    }

    public static void main(String[] args) {
        int[] numbers = {376,36,22,98,23,373};
        System.out.println("Sorted Unique Elements: " + getSortedUnique(numbers));
    }
}
