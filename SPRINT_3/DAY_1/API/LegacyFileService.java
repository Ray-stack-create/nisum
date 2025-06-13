package nisum.SPRINT_3.DAY_1.API;
import java.io.IOException;

public class LegacyFileService {
    public String readFile(String filename) throws IOException {
        if (filename == null || filename.isBlank()) {
            throw new IOException("Filename is invalid");
        }
        return "Contents of " + filename;
    }
}
