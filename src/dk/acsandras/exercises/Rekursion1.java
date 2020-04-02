/**
 * Developed by András Ács (acsandras@gmail.com)
 * Zealand / www.zealand.dk
 * Licensed under the MIT License
 * 02/04/2020
 */
package dk.acsandras.exercises;

public class Rekursion1 {

    static int n = 0;

    public static void main(String[] args) {
        selvSving();
    }

    private static void selvSving() {
        System.out.println("Jeg er i selvsving!" + n++);
        // Base case / exit condition
        if (n>4000) {return;} else {selvSving();}
    }
}
