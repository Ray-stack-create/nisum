package nisum.SPRINT_3.DAY_1.Q9;

@FunctionalInterface
public interface ValidationRule<T> {
    String validate(T object);
}
