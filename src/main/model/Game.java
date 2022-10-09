package model;

import java.util.ArrayList;
import java.util.List;

// Represents a game with players

public class Game {
    private List<Player> players;

    // Constructs an empty list of players
    public Game() {
        this.players = new ArrayList<>();
    }

    // REQUIRES: initialBalance >= 0
    // MODIFIES: this
    // EFFECTS: adds a new player to the game with a starting balance
    public void addPlayer(String playerName, int initialBalance) {
        this.players.add(new Player(playerName, initialBalance));
    }

    // REQUIRES: player name must be on the list, thus list size > 0
    // MODIFIES: this
    // EFFECTS: removes a specified player from the list of players
    public void removeThePlayer(String playerName) {

    }


}
