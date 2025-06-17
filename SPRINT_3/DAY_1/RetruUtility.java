package nisum.SPRINT_3.DAY_1;
import java.util.Random;

public class RetryUtility {

    public static void main(String[] args) {
        try {
            String result = retryWithExponentialBackoff(() -> simulateNetworkCall(), 5, 1000);
            System.out.println("Success: " + result);
        } catch (Exception e) {
            System.out.println("Operation failed after retries: " + e.getMessage());
        }
    }

    /**
     * Generic retry method with exponential backoff.
     *
     * @param operation  A lambda or functional interface that performs the task.
     * @param maxRetries Maximum number of retry attempts.
     * @param baseDelay  Initial delay in milliseconds.
     * @param <T>        Return type of the operation.
     * @return Result of successful operation.
     * @throws Exception If all retries fail.
     */
    public static <T> T retryWithExponentialBackoff(RetriableOperation<T> operation, int maxRetries, int baseDelay) throws Exception {
        int attempts = 0;

        while (true) {
            try {
                return operation.execute();
            } catch (Exception e) {
                attempts++;
                if (attempts > maxRetries) {
                    throw new Exception("Exceeded max retries", e);
                }
                int delay = baseDelay * (int) Math.pow(2, attempts - 1); 
                System.out.println("Attempt " + attempts + " failed: " + e.getMessage());
                System.out.println("Retrying after " + delay + "ms...");

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new Exception("Retry interrupted", ie);
                }
            }
        }
    }

   

    @FunctionalInterface
    public interface RetriableOperation<T> {
        T execute() throws Exception;
    }

  
    public static String simulateNetworkCall() throws Exception {
        Random random = new Random();
        if (random.nextInt(4) != 0) {  
            throw new Exception("Simulated network failure");
        }
        return "Connected to remote service!";
    }
}
