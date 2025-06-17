package nisum.SPRINT_3.DAY_1.Q9;

public class Main {
    public static void main(String[] args) {
        User user = new User("", -5, "invalid_email");

        ValidationProcessor<User> engine = new ValidationProcessor<User>()
            .addRule(u -> (u.name() == null || u.name().isBlank()) ? "Name cannot be blank" : null)
            .addRule(u -> u.age() < 0 ? "Age cannot be negative" : null)
            .addRule(u -> !u.email().contains("@") ? "Invalid email address" : null);

        try {
            engine.validate(user);
        } catch (ValidationException ve) {
            System.out.println("Validation failed:");
            ve.getErrors().forEach(System.out::println);
        }
    }
}
