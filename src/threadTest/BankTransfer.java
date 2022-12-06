package threadTest;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock（重入锁）+ Condition（条件变量）实现银行转账
 * 重入锁：获取该锁之后如果调用另一个需要获取该锁的方法，可以嵌套地获取锁，锁有一个持有计数来跟踪对lock的嵌套调用，每一个lock都要对应一个unlock
 * 条件变量：如果获取锁之后还需要在一定条件下才能执行方法，我们需要等待直至满足条件才继续执行。
 *         由于当前线程已拿到锁，其他线程无法修改条件，我们需要让出锁让其他线程运行。我们不是直接将该线程阻塞，而是引入一个条件变量
 * lock.newCondition() 可以获取一个当前锁的条件变量，当不满足条件时调用 await() 方法让出锁，而每当修改数据之后调用signalAll唤醒所有在等待中的线程。
 */
public class BankTransfer {

    double[] accounts;
    Lock lock;
    Condition condition;
    public static final double max = 1000.0;

    public BankTransfer(int n, int initVal) {
        this.accounts = new double[n];
        Arrays.fill(accounts, initVal);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        lock.lock();
        try {
            while (accounts[from] < amount) condition.await();
            System.out.println(Thread.currentThread().getName());
            accounts[from] -= amount;
            accounts[to] += amount;
            System.out.println(from + " transfered " + amount + " to " + to + ", total: " + getTotal());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public double getTotal() {
        lock.lock();
        try {
            double tot = 0.0;
            for (double d : accounts) tot += d;
            return tot;
        } finally {
            lock.unlock();
        }
    }

    public int size() {return accounts.length;}


    public static void main(String[] args) {
        BankTransfer bankTransfer = new BankTransfer(10, 10000);
        Runnable runnable = ()->{
            for (int i = 0; i < 1000; i++) {
                int size = bankTransfer.size();
                double amount = max * Math.random();
                try {
                    bankTransfer.transfer((int) (size * Math.random()), (int) (size * Math.random()), amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
