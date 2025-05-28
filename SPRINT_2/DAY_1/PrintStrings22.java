import java.util.*;
import java.util.function.Consumer;

public class PrintStrings22 {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");

        Consumer<String> printConsumer = fruit -> System.out.println("Fruit: " + fruit);
        fruits.forEach(printConsumer);
    }
}
