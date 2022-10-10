package model;


import java.util.ArrayList;
import java.util.List;

// Represents a game with players

public class Game {
    private List<Player> players;
    private int potBalance;

    // Constructs an empty list of players and an empty pot
    public Game() {
        this.players = new ArrayList<>();
        this.potBalance = 0;
    }

    // REQUIRES: initialBalance >= 0
    // MODIFIES: this
    // EFFECTS: adds a new player to the game with a starting balance
    public void addPlayer(String playerName, int balance) {
        this.players.add(new Player(playerName, balance));
    }

    // REQUIRES: player name must be on the list, thus list size > 0
    // MODIFIES: this
    // EFFECTS: removes a specified player from the list of players based on index
    public void removePlayer(int index) {
        this.players.remove(index);
    }

    // REQUIRES: num > 0
    // MODIFIES: player balance and pot balance
    // EFFECTS: takes num out of player balance and transfers it into pot balance
    public void makeBet(Player player, int num) {
        player.subtractBalance(num);
        potBalance += num;
    }

    // REQUIRES: num > 0
    // MODIFIES: player balance and pot balance
    // EFFECTS: transfers pot balance to player balance, and resets pot balance to 0
    public void claimPot(Player player) {
        player.addBalance(potBalance);
        potBalance = 0;
    }

    // Setters

    // Getters
    public List<Player> getPlayers() {
        return players;
    }

    public int getPotBalance() {
        return potBalance;
    }

    // REQUIRES: valid player name that already exists in the list of players
    public Player getPlayerByName(String name) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName() == name) {
                return players.get(i);
            }
        }
        return null;
    }



}
