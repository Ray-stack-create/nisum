package nisum.SPRINT_3.DAY_1.Transaction;


class Transfer extends Thread {
    private final Account from;
    private final Account to;
    private final int amount;

    public Transfer(Account from, Account to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        Account firstLock = from.getId() < to.getId() ? from : to;
        Account secondLock = from.getId() < to.getId() ? to : from;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
            
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    System.out.println(Thread.currentThread().getName() + 
                        " transferred ₹" + amount + 
                        " from Account-" + from.getId() + 
                        " to Account-" + to.getId());
                } else {
                    System.out.println(Thread.currentThread().getName() + 
                        " failed to transfer ₹" + amount + 
                        " from Account-" + from.getId() + 
                        " to Account-" + to.getId() + " due to insufficient balance.");
                }
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }
    }
}
