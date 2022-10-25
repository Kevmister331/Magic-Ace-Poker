package ui;

import model.Game;
import model.Player;

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

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("7")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doViewList();
        } else if (command.equals("2")) {
            doAddPlayer();
        } else if (command.equals("3")) {
            doRemovePlayer();
        } else if (command.equals("4")) {
            doMakeBet();
        } else if (command.equals("5")) {
            doClaimPot();
        } else if (command.equals("6")) {
            doViewPot();
        } else {
            System.out.println("The key inputted is not valid!");
        }
    }


    // EFFECTS: initializes poker game
    private void init() {
        game = new Game();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays the programs menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 --> View List of Players & Balance");
        System.out.println("\t2 --> Add Player");
        System.out.println("\t3 --> Remove Player");
        System.out.println("\t4 --> Make Bet");
        System.out.println("\t5 --> Claim Pot");
        System.out.println("\t6 --> View Pot Balance");
        System.out.println("\t7 --> Quit");
    }


    // EFFECTS: processes user command
    private void doViewList() {
        if (game.getPlayers().size() == 0) {
            System.out.println("There are no players!");
        } else {
            System.out.println("List of Players and their Balance:");
            for (Player player : this.game.getPlayers()) {
                System.out.println("Name: " + player.getPlayerName() + " ~~~ " + "Bal: " + player.getBalance());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds player to game
    private void doAddPlayer() {
        System.out.println("\nInsert player name:");
        String name = input.next();
        System.out.println("\nInsert player starting balance:");
        int bal = Integer.parseInt(input.next());

        Player p = new Player(name, bal);
        game.addPlayer(p.getPlayerName(), p.getBalance());
        System.out.println(p.getPlayerName() + " has been added with starting balance " + p.getBalance());
    }
    // MODIFIES: this
    // EFFECTS: removes player from game
    private void doRemovePlayer() {
        System.out.println("\nInsert player name you would like to remove:");
        String name = input.next();

        int i = 0;

        for (Player player : this.game.getPlayers()) {
            if (player.getPlayerName().equals(name)) {
                i++;
                game.removePlayer(name);
                System.out.println("Player " + name + " has been removed.");
                break;
            }
        }
        if (i == 0) {
            System.out.println("Player not found!");
        }

    }
    // MODIFIES: this
    // EFFECTS: select a player to make a bet towards the pot
    private void doMakeBet() {
        System.out.println("\nSelect player to bet from:");
        String name = input.next();
        System.out.println("\nInsert amount you would like to bet");
        String temp = input.next();
        int amount = Integer.parseInt(temp);

        int i = 0;

        for (Player player : this.game.getPlayers()) {
            if (player.getPlayerName().equals(name)) {
                i++;
                if (amount < player.getBalance()) {
                    game.makeBet(player, amount);
                    System.out.println(name + " has bet " + amount + " chips!");
                } else {
                    System.out.println(name + " does not have enough chips!");
                }
            }
            break;
        }
        if (i == 0) {
            System.out.println("Player named " + name + " could not be found!");
        }
    }

    // MODIFIES: this
    // EFFECTS: select a player to claim winnings from the pot
    private void doClaimPot() {
        System.out.println("\nWho's our lucky winner?");
        String name = input.next();

        int i = 0;

        for (Player player : this.game.getPlayers()) {
            if (player.getPlayerName().equals(name)) {
                i++;
                System.out.println("Congrats! " + name + " just got " + game.getPotBalance() + " chips richer!");
                game.claimPot(game.getPlayerByName(name));
                break;
            }
        }
        if (i == 0) {
            System.out.println("Player named " + name + " could not be found!");
        }
    }


    // EFFECTS: view the balance of the pot
    private void doViewPot() {
        System.out.println("Pot balance: " + game.getPotBalance());
    }

}


