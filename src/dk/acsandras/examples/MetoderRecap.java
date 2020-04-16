/**
 * Developed by András Ács (acsandras@gmail.com)
 * Zealand / www.zealand.dk
 * Licensed under the MIT License
 * 16/04/2020
 */
package dk.acsandras.examples;

import java.util.Date;

public class MetoderRecap implements KanSigeHej {

    private String objektNavn = "x";

    public  MetoderRecap() {
        System.out.println("Et nyt MetoderRecap objekt instansieret.");
    }

    // accessmodifier returtype navn ( parametre )
    public int givEtTal(int tal) {
        return 2*tal;
    }

    static void  startServer() {
        // Start server
        System.out.println("Server kører.");
    }

    static void startServer(Date dato) {
        // Start server
        System.out.println("Server kører.");
    }

    static void visObjekt(MetoderRecap o) {
        System.out.println(o.toString());
    }

    public static void main(String[] args) {

        OverrideEksempel m = new OverrideEksempel();
        MetoderRecap m2 = new MetoderRecap();
        try {
            OverrideEksempel m3 = (OverrideEksempel) m.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        visObjekt((MetoderRecap) m);
        // visObjekt((OverrideEksempel) m2);
        if (m instanceof OverrideEksempel) {
            System.out.println("Her er en type MetodeRecap");
        }

    }

    @Override
    public void hello() {
        System.out.println("Hello from the other side. ");
    }
}

class OverrideEksempel extends MetoderRecap implements KanSigeHej, Comparable {

    public OverrideEksempel() {
        System.out.println("Et nyt OverrideEksempel objekt instansieret.");
    }

    static void startServer() {
        // Start server
        System.out.println("En bedre server kører.");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

interface KanSigeHej {

    void hello();

        }