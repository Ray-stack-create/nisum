package nisum.SPRINT_3.DAY_1.API;
import nisum.SPRINT_3.DAY_1.API.LegacyFileService;
import nisum.SPRINT_3.DAY_1.API.IOFunctionWrapper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LegacyFileService service = new LegacyFileService();

        List<String> files = List.of("data.txt", "log.txt", "");

        files.stream()
                .map(IOFunctionWrapper.unchecked(service::readFile)) 
                .forEach(System.out::println);
    }
}
