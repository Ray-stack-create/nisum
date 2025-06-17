package nisum.SPRINT_3.DAY_1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class AgeValidationWithFile {


    public static void validateAge(int age, BufferedWriter writer) throws IOException {
        try {
            if (age <= 0) {
                throw new InvalidAgeException("Invalid age: " + age + ". Age must be greater than 0.");
            } else {
                writer.write("Valid age: " + age);
                writer.newLine();
                System.out.println("Valid age: " + age);
            }
        } catch (InvalidAgeException e) {
            writer.write("Caught InvalidAgeException: " + e.getMessage());
            writer.newLine();
            throw e;
        }
    }

    public static void main(String[] args) {
        int[] testAges = {25, -5, 0, 18};
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("age_validation_log.txt"))) {
            for (int age : testAges) {
                try {
                    validateAge(age, writer);
                } catch (InvalidAgeException e) {
                    System.out.println("Caught InvalidAgeException: " + e.getMessage());
                } catch (Exception e) {
                    writer.write("Caught General Exception: " + e.getMessage());
                    writer.newLine();
                    System.out.println("Caught General Exception: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("IOException during file writing: " + e.getMessage());
        }
    }
}
