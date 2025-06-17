package nisum.SPRINT_3.DAY_1;

import java.nio.channels.Pipe.SourceChannel;

class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class AgeValidator {
    public static void validateAge(int age) {
        try {
            if (age <= 0) {
                throw new InvalidAgeException("Invalid age: " + age + ". Age must be positive.");
            }
            System.out.println("Age " + age + " is valid.");
        } catch (InvalidAgeException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        int[] testAges = {25, -5, 0, 18};
        for (int age : testAges) {
            try {
                validateAge(age);
            } catch (InvalidAgeException e) {
                System.out.println("Caught InvalidAgeException: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Caught Exception: " + e.getMessage());
            }
        }
    }
}


