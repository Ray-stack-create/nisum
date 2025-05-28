import java.util.*;
import java.util.stream.Collectors;

public class FilterStrings19 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Arun", "Sankha", "Sohani", "Samridhi", "Diya");
        List<String> aNames = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());

        System.out.println("Names starting with A: " + aNames); 
    }
}
