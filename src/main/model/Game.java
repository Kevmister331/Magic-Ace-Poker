package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a game with players and a pot

public class Game implements Writable {
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
    //          and tracks it in the event log
    public void addPlayer(String playerName, int balance) {
        this.players.add(new Player(playerName, balance));
        EventLog.getInstance().logEvent(new Event("Added " + playerName
                + " to the game with a starting balance of " + balance + "!"));
    }

    // MODIFIES: this
    // EFFECTS: removes a specified player from the list of players
    //          and tracks the action in the event log
    public void removePlayer(String name) {
        Player playerChosen = null;
        for (Player player : this.players) {
            if (player.getPlayerName().equals(name)) {
                playerChosen = player;
                break;
            }
        }
        if (playerChosen != null) {
            EventLog.getInstance().logEvent(new Event("Removed " + playerChosen.getPlayerName() + " from the game!"));
            this.players.remove(playerChosen);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a specified player from the list of players through index
    //          and tracks the action in the event log
    public void removePlayerByIndex(int index) {
        EventLog.getInstance().logEvent(new Event("Removed "
                + this.players.get(index).getPlayerName() + " from the game!"));
        this.players.remove(index);
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
        EventLog.getInstance().logEvent(new Event(player.getPlayerName()
                + " has claimed a jackpot of " + potBalance + "!"));

        player.addBalance(potBalance);
        potBalance = 0;
    }

    // REQUIRES: bal >= 0
    // MODIFIES: this
    // EFFECTS: set a pot balance value
    public void setPotBalance(int bal) {
        potBalance = bal;
    }


    // Getters
    public List<Player> getPlayers() {
        return players;
    }

    public int getPotBalance() {
        return potBalance;
    }

    public Player getPlayerByIndex(int index) {
        return players.get(index);
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

    // EFFECTS: Feeds information as JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        json.put("potBalance", potBalance);
        return json;
    }

    // EFFECTS: returns players in this game as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player player : players) {
            jsonArray.put(player.toJson());
        }

        return jsonArray;
    }

}

