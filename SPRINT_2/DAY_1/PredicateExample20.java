import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample20 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 6, 9, 12, 15, 18);

        Predicate<Integer> divisibleBy2 = x -> x % 2 == 0;
        Predicate<Integer> divisibleBy3 = x -> x % 3 == 0;

        List<Integer> divisibleBy2And3 = numbers.stream()
            .filter(divisibleBy2.and(divisibleBy3))
            .collect(Collectors.toList());

        List<Integer> divisibleBy2Or3 = numbers.stream()
            .filter(divisibleBy2.or(divisibleBy3))
            .collect(Collectors.toList());

        System.out.println("Divisible by both 2 and 3: " + divisibleBy2And3); 
        System.out.println("Divisible by 2 or 3: " + divisibleBy2Or3); 
    }
}
