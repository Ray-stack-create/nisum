package nisum.SPRINT_3.DAY_1;

public class CounterSync {

    static int counter = 0;

    // Synchronized method to safely increment the counter
    public static synchronized void increment() {
        counter++;
    }

    static class IncrementThread extends Thread {
        public void run() {
            for (int i = 0; i < 1000; i++) {
                increment(); // Safe call
            }
        }
    }

    public static void main(String[] args) {
        IncrementThread t1 = new IncrementThread();
        IncrementThread t2 = new IncrementThread();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter (with synchronization): " + counter);
    }
}
