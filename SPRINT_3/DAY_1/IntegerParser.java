package nisum.SPRINT_3.DAY_1;
import java.io.*;
import java.util.*;

public class IntegerParser {

    public static void readAndParseIntegers(String fileName) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println("Parsed integers from file:");

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    int number = Integer.parseInt(token);
                    System.out.println(number);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("An error occurred while reading or parsing the file:");
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                System.out.println("Failed to close reader: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        
        readAndParseIntegers("numbers.txt");
    }
}
