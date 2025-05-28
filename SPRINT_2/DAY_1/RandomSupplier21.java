import java.util.function.Supplier;

public class RandomSupplier21 {
    public static void main(String[] args) {
        Supplier<Double> randomSupplier = () -> Math.random();

        System.out.println("Random numbers:");
        for (int i = 0; i < 5; i++) {
            System.out.println(randomSupplier.get());
        }
    }
}
