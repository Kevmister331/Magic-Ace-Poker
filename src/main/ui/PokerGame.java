package ui;

import model.Game;

import java.util.Scanner;

// Poker chip counter application
public class PokerGame {
    private Game game;
    private Scanner input;

    // EFFECTS: runs the poker game
    public PokerGame() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean keepGoing = true;
        String command = null;

        init();
/*
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
*/
        System.out.println("\nGoodbye!");
    }
/*
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("d")) {
            doDeposit();
        } else if (command.equals("w")) {
            doWithdrawal();
        } else if (command.equals("t")) {
            doTransfer();
        } else {
            System.out.println("Selection not valid...");
        }
    }
*/
    private void init() {
        new Game();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> View List of Players & Balance");
        System.out.println("\tv -> ");
        System.out.println("\tq -> Quit");
    }
}

