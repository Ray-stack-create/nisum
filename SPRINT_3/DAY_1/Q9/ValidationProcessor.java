package nisum.SPRINT_3.DAY_1.Q9;

import java.util.ArrayList;
import java.util.List;

public class ValidationProcessor<T> {
    private final List<ValidationRule<T>> rules = new ArrayList<>();

    public ValidationProcessor<T> addRule(ValidationRule<T> rule) {
        rules.add(rule);
        return this;
    }

    public void validate(T object) {
        List<String> errors = new ArrayList<>();
        for (ValidationRule<T> rule : rules) {
            String error = rule.validate(object);
            if (error != null) {
                errors.add(error);
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
