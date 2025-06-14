package nisum.SPRINT_3.DAY_1.Transaction;
import nisum.SPRINT_3.DAY_1.Transaction.Transfer;

public class BankSimulation {
    public static void main(String[] args) {
        Account acc1 = new Account(1, 1000);
        Account acc2 = new Account(2, 1000);
        Account acc3 = new Account(3, 1000);

        Thread t1 = new Transfer(acc1, acc2, 300);
        Thread t2 = new Transfer(acc2, acc3, 500);
        Thread t3 = new Transfer(acc3, acc1, 200);
        Thread t4 = new Transfer(acc1, acc3, 400);
        Thread t5 = new Transfer(acc2, acc1, 100);


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final Balances:");
        System.out.println("Account-1: ₹" + acc1.getBalance());
        System.out.println("Account-2: ₹" + acc2.getBalance());
        System.out.println("Account-3: ₹" + acc3.getBalance());
    }
}
