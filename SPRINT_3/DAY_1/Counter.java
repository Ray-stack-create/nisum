package nisum.SPRINT_3.DAY_1;

public class Counter {

    static int counter = 0; 
    static class IncrementThread extends Thread {
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter++; 
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

        System.out.println("Final Counter (without synchronization): " + counter);
    }
}
