package model;

// Represents a player's poker chips
public class Player {
    private String playerName;
    private int balance;

    // REQUIRES: initialBalance >= 0
    // EFFECTS: Constructs a new player with given initial poker chip balance in dollars
    public Player(String playerName, int initialBalance) {
        this.playerName = playerName;
        this.balance = initialBalance;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount of dollars into chip balance
    public void addBalance(int amount) {
        balance += amount;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: subtracts amount of dollars from the chip balance
    public void subtractBalance(int amount) {
        balance -= amount;
    }


    // Getter methods
    public String getPlayerName() {
        return playerName;
    }

    public int getBalance() {
        return balance;
    }

    // Setter methods
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
