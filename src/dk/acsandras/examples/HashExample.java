package dk.acsandras.examples;// Java program to demonstrate working of HashTable
import java.util.*; 
  
class HashExample {
    public static void main(String args[]) 
    { 
  
        // Create a HashTable to store  
        // String values corresponding to integer keys 
        Hashtable<Integer, String> 
            hm = new Hashtable<Integer, String>(); 
  
        // Input the values 
        hm.put(1, "Geeks"); 
        hm.put(12, "forGeeks"); 
        hm.put(15, "A computer"); 
        hm.put(3, "Portal"); 
  
        // Printing the Hashtable 
        System.out.println(hm);


        // Hashcode
        String navn = "Laila";
        System.out.println("Laila's hash er: " + navn.hashCode());
        navn = "Tommy";
        System.out.println("Tommy's hash er: " + navn.hashCode());
        navn = "osidfyas98ysdoiuyfgsduoifygsoiudyzgdsuiygiosudyzgfsuogydf";
        System.out.println("Tommy's hash er: " + navn.hashCode());
        navn = "2";
        System.out.println("Tommy's hash er: " + navn.hashCode());


    }

    /**
     * Developed by András Ács (acsandras@gmail.com)
     * Zealand / www.zealand.dk
     * Licensed under the MIT License
     * 02/04/2020
     */

    public static class GameOfLifeDummy {

        static final int ANTAL_FELTER = 10;
        static Boolean[][] spilleplade = new Boolean[ANTAL_FELTER][ANTAL_FELTER];

        public static void main(String[] args) {

            for (int i = 0; i < ANTAL_FELTER; i++) {
                for (int j = 0; j < ANTAL_FELTER; j++) {
                    spilleplade[i][j] = false;
                }
            }

            spilleplade[5][5] = true;
            int naboAntal = 0;
            if (spilleplade[5-1][5-1]==true) naboAntal++; //Øverst TV
            if (spilleplade[5-1][5]==true) naboAntal++; // Op over
            if (spilleplade[5-1][5+1]==true) naboAntal++; //Øverst TH

            spilleplade[9][9] = true;

            visPladen();

        }

        static void visPladen() {

            for (int i = 0; i < ANTAL_FELTER; i++) {
                for (int j = 0; j < ANTAL_FELTER; j++) {
                    if (spilleplade[i][j] == false) {
                        System.out.print(".");
                    } else {
                        System.out.print("O");
                    }
                }
                System.out.println("");
            }
        }


    }
}