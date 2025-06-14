package nisum.SPRINT_3.DAY_1;
import java.util.concurrent.atomic.AtomicLong;

public class PrimeSumMultiThreaded {
    private static final AtomicLong primeSum = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        int limit = 1_000_000; 
        int numThreads = 4; 

        Thread[] threads = new Thread[numThreads];

        int chunkSize = limit / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize + 1;
            int end = (i == numThreads - 1) ? limit : (i + 1) * chunkSize;

            threads[i] = new Thread(new PrimeWorker(start, end));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Sum of all prime numbers up to " + limit + " is: " + primeSum.get());
    }
    static class PrimeWorker implements Runnable {
        private final int start;
        private final int end;

        public PrimeWorker(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            long localSum = 0;
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    localSum += i;
                }
            }
            primeSum.addAndGet(localSum); 
        }
        private boolean isPrime(int n) {
            if (n <= 1) return false;
            if (n == 2 || n == 3) return true;
            if (n % 2 == 0 || n % 3 == 0) return false;
            for (int i = 5; i * i <= n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) return false;
            }
            return true;
        }
    }
}
