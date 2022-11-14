package ui;

import java.io.FileNotFoundException;

// Runs the poker game
public class Main {
    public static void main(String[] args) {
        try {
            new PokerGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
