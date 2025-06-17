package nisum.SPRINT_3.DAY_1.API;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Function;

public class IOFunctionWrapper {
    @FunctionalInterface
    public interface IOFunction<T, R> {
        R apply(T t) throws IOException;
    }


    public static <T, R> Function<T, R> unchecked(IOFunction<T, R> ioFunction) {
        return input -> {
            try {
                return ioFunction.apply(input);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
}
