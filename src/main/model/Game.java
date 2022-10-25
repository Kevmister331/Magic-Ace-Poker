package model;


import java.util.ArrayList;
import java.util.List;

// Represents a game with players and a pot

public class Game {
    private List<Player> players;
    private int potBalance;

    // EFFECTS: Constructs an empty list of players and an empty pot
    public Game() {
        this.players = new ArrayList<>();
        this.potBalance = 0;
    }

    // REQUIRES: balance >= 0
    // MODIFIES: this
    // EFFECTS: adds a new player to the game with a starting balance
    public void addPlayer(String playerName, int balance) {
        this.players.add(new Player(playerName, balance));
    }

    // MODIFIES: this
    // EFFECTS: removes a specified player from the list of players
    public void removePlayer(String name) {
        Player playerChosen = null;
        for (Player player : this.players) {
            if (player.getPlayerName().equals(name)) {
                playerChosen = player;
                break;
            }
        }
        if (playerChosen != null) {
            this.players.remove(playerChosen);
        }
    }

    // REQUIRES: num > 0, num <= player balance
    // MODIFIES: this and pot balance
    // EFFECTS: takes num out of player balance and transfers it into pot balance
    public void makeBet(Player player, int num) {
        if (num < player.getBalance()) {
            player.subtractBalance(num);
            potBalance += num;
        }
    }

    // REQUIRES: player name must exist in the game
    // MODIFIES: this and pot balance
    // EFFECTS: transfers pot balance to player balance, and resets pot balance to 0
    public void claimPot(Player player) {
        player.addBalance(potBalance);
        potBalance = 0;
    }


    // Getters
    public List<Player> getPlayers() {
        return players;
    }

    public int getPotBalance() {
        return potBalance;
    }

    // EFFECTS: searches a player by their name and returns the player, otherwise null
    public Player getPlayerByName(String name) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayerName().equals(name)) {
                return players.get(i);
            }
        }
        return null;
    }


}
