package dk.acsandras.examples;// Java program to demonstrate working of HashTable
import java.util.*; 
  
class GFG { 
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
} 