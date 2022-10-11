package model;

// Represents a player's name and poker chips
public class Player {
    private String playerName;
    private int playerBalance;

    // REQUIRES: initialBalance >= 0
    // EFFECTS: Constructs a new player with given initial poker chip balance in dollars
    public Player(String playerName, int initialBalance) {
        this.playerName = playerName;
        this.playerBalance = initialBalance;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount into player balance
    public void addBalance(int amount) {
        playerBalance += amount;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: subtracts amount from player balance
    public void subtractBalance(int amount) {
        playerBalance -= amount;
    }


    // Getter methods
    public String getPlayerName() {
        return playerName;
    }

    public int getBalance() {
        return playerBalance;
    }


}
