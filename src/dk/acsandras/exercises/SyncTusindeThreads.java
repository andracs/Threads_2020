package dk.acsandras.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// 30.4  (Synchronize threads) Write a program that launches 1,000 threads. Each thread adds 1 to a variable sum that initially is 0. Define an Integer wrapper object to hold sum. Run the program with and without synchronization to see its effect.
public class SyncTusindeThreads {

    static Integer sumUdenSyncInt = 0;
    static Integer sumMedSyncInt = 0;
    final static int N = 10000;  // antal tråde
    final static int SLEEP_MS = 5;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        // Create and launch N threads
        for (int i = 0; i < N; i++) {
            executor.execute(new sumUdenSync());
            executor.execute(new sumMedSync());

        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("Summen uden sync er " + sumUdenSyncInt);
        System.out.println("Summen med sync er " + sumMedSyncInt);


    }

    /**
     * Denne inner-class har en enkelt run() metode, som lægger +1 til summen (sum er instansvariable i parent class.)
     * <p>
     * Implementationen er ikke thread safe, og derfor risikerer vi at få forkert resultat, hvis flere tråde læser
     * og skriver samtidigt i variablen.
     */
    private static class sumUdenSync implements Runnable {

        @Override
        public void run() {
            sumUdenSyncInt += 1;

            // Vi forsinker trådene en smule, så de bliver godt og grundigt viklet sammen
            try {
                Thread.sleep(SLEEP_MS);
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Denne inner-class har en enkelt run metode, som lægger +1 til summen (instansvariable i parent class)
     * <p>
     * Metoden synkroniseres vha. en lock (det kunne også være en semaphore eller andet obbjekt.)
     */
    private static class sumMedSync implements Runnable {

        static final Semaphore lock = new Semaphore(1);

        @Override
        public void run() {

            // Denne kodeblok bliver synchronized dvs, thread safe
            synchronized (lock) {
                sumMedSyncInt += 1;
            }

            // Vi forsinker trådene en smule, så de bliver godt og grundigt viklet sammen
            try {
                Thread.sleep(SLEEP_MS);
            } catch (InterruptedException ex) {
            }
        }
    }

}
