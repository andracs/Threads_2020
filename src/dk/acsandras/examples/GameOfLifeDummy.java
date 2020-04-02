package dk.acsandras.examples;

/**
 * Developed by András Ács (acsandras@gmail.com)
 * Zealand / www.zealand.dk
 * Licensed under the MIT License
 * 02/04/2020
 */

public class GameOfLifeDummy {

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
