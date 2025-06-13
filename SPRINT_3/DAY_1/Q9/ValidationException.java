package nisum.SPRINT_3.DAY_1.Q9;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<String> errors;

    public ValidationException(List<String> errors) {
        super("Validation failed with " + errors.size() + " error(s).");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ValidationException: " + String.join(", ", errors);
    }
}
