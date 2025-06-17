package nisum.SPRINT_3.DAY_1;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class GlobalException {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            logExceptionToFile(thread, exception);
        });

       
        System.out.println("Starting application...");
        int result = 10 / 0; 
        System.out.println("Result: " + result);
    }

    
    private static void logExceptionToFile(Thread thread, Throwable exception) {
        String logFile = "error_log.txt";
        try (FileWriter fw = new FileWriter(logFile, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("=== Uncaught Exception ===");
            pw.println("Timestamp: " + LocalDateTime.now());
            pw.println("Thread: " + thread.getName());
            pw.println("Exception: " + exception);
            for (StackTraceElement element : exception.getStackTrace()) {
                pw.println("\tat " + element);
            }
            pw.println();

        } catch (IOException ioEx) {
            System.err.println("Failed to write to log file: " + ioEx.getMessage());
        }
    }
}
