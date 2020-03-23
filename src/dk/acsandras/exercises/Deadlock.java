package dk.acsandras.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 30.11 (Demonstrate deadlock) Write a program that demonstrates deadlock.
public class Deadlock {

    static Integer N = 10;
    static Lock aLock = new ReentrantLock();
    static Lock bLock = new ReentrantLock();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        // Create and launch N threads
        for (int i = 0; i < N; i++) {
            executor.execute(new Dummy());
            executor.execute(new Dummy2());
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

    }

    static class Dummy implements Runnable {
        @Override
        public void run() {

            long threadId = Thread.currentThread().getId();

            synchronized (aLock) {
                bLock.lock();
                System.out.println(threadId + " har fået låst B.");
                synchronized (bLock) {
                    aLock.lock();
                    System.out.println(threadId + " har fået låst A.");
                    aLock.unlock();
                    bLock.unlock();
                }
            }
        }
    }

    static class Dummy2 implements Runnable {
        @Override
        public void run() {

            long threadId = Thread.currentThread().getId();

            synchronized (bLock) {
                bLock.lock();
                System.out.println(threadId + " har fået låst A.");
                synchronized (aLock) {
                    aLock.lock();
                    System.out.println(threadId + " har fået låst B.");
                    aLock.unlock();
                    bLock.unlock();
                }
            }
        }
    }
}
