import java.util.*;
import java.util.function.Consumer;

public class DoubleAndPrint23 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Consumer<Integer> doubleConsumer = num -> System.out.println("Double: " + (num * 2));
        numbers.forEach(doubleConsumer);
    }
}
