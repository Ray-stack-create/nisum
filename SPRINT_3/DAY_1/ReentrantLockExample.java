package nisum.SPRINT_3.DAY_1;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();  
        try {
            counter++;  
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        } finally {
            lock.unlock(); 
        }
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                example.increment();
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final counter value: " + example.getCounter());
    }
}
