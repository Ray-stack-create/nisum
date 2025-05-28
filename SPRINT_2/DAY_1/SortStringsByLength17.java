import java.util.*;

public class SortStringsByLength17 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tom", "Elizabeth", "Ann", "Joe");
        names.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println(names);
    }
}
