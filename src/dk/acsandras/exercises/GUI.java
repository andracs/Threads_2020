package dk.acsandras.exercises;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// 30.1 (Revise Listing 30.1) Rewrite Listing 30.1 to display the output in a text area, as shown in Figure 30.30.
public class GUI extends Application {

    // De her 2 objekter skal vi bruge i alle sub-klasser også, derfor instansvariabler
    TextArea textArea = new TextArea();
    static String text2Show = "";
    private static final Semaphore semaphore = new Semaphore(1);
    static int clickCounter = 0;


    @Override
    public void start(Stage stage) {

        // Lidt gejl, som kan vise java og javafx version
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        // Jeg propper mine grafiske elementer i en vertical box
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 50, 50, 50));
        vBox.setSpacing(10);

        // Label der viser Java og JavaFX version, tilføjes til VBoxen
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        vBox.getChildren().add(l);

        // Textarea er allerede erklæret og instansieret på objektet, her tilføjes tekst, og det hele tilføjes til VBoxen
        textArea.setText("Her kommer resultat fra trådene.");
        textArea.wrapTextProperty().setValue(true);
        vBox.getChildren().add(textArea);

        // Vi skal jo også have en knap med en lækker event-handler lambda expression, der starter trådene
        Button button = new Button("Giv mig tråde!");
        button.setOnAction((EventHandler) event -> {
            textArea.setText("Resultat fra tråde (" + clickCounter++ + "): \n");
            startTraade();
        });
        vBox.getChildren().add(button);

        // Nu er elementerne på plads, vi viser Scenen med VBoxen indeni
        Scene scene = new Scene(new StackPane(vBox), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoden, der starter 3 konkurrerende tråde
     */
    public void startTraade() {

        text2Show = "";

        // Create tasks
        Runnable printA = new PrintCharGUI('a', 100);
        Runnable printB = new PrintCharGUI('b', 100);
        Runnable print100 = new PrintNumGUI(100);

        // Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);


        // Using ExecutorService to manage thread execution
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(thread1);
        executor.execute(thread2);
        executor.execute(thread3);

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        textArea.appendText(text2Show);

    }

    // Den skal bare være her, synes JavaFX
    public static void main(String[] args) {
        launch(args);
    }

    // The task for printing a specified character in specified times
    class PrintCharGUI implements Runnable {
        private char charToPrint; // The character to print
        private int times; // The times to repeat

        /**
         * Construct a task with specified character and number of
         * times to print the character
         */
        public PrintCharGUI(char c, int t) {
            charToPrint = c;
            times = t;
        }

        @Override
        /** Override the run() method to tell the system
         *  what the task to perform
         */
        public void run() {
            for (int i = 0; i < times; i++) {
                synchronized (semaphore) {
                    text2Show += String.valueOf(charToPrint);
                }
            }
        }
    }

    // The task class for printing number from 1 to n for a given n
    class PrintNumGUI implements Runnable {
        private int lastNum;

        /**
         * Construct a task for printing 1, 2, ... i
         */
        public PrintNumGUI(int n) {
            lastNum = n;
        }

        /** Tell the thread how to run */
        @Override
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                synchronized (semaphore) {
                    text2Show = text2Show + String.valueOf(i);
                }
            }
        }
    }

}