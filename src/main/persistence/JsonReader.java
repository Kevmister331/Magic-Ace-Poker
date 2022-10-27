package persistence;

import model.Game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Player;
import org.json.*;

// Some of this code in this class is sourced from the provided CPSC 210 JSON program
// Represents a reader that reads the Game from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Game read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return returnGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game from JSON object and returns it
    private Game returnGame(JSONObject jsonObject) {
        Game game = new Game();
        addPlayers(game, jsonObject);
        addPotBalance(game, jsonObject);
        return game;
    }

    // MODIFIES: game
    // EFFECTS: parses players from JSON object and adds them to the game
    private void addPlayers(Game game, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(game, nextPlayer);
        }
    }

    // MODIFIES: game
    // EFFECTS: parses player from JSON object and adds it to the game
    private void addPlayer(Game game, JSONObject jsonObject) {
        String playerName = jsonObject.getString("playerName");
        int playerBalance = jsonObject.getInt("playerBalance");
        game.addPlayer(playerName, playerBalance);
    }

    // MODIFIES: game
    // EFFECTS: parses pot balance from JSON object and adds it to the game
    private void addPotBalance(Game game, JSONObject jsonObject) {
        int potBalance = jsonObject.getInt("potBalance");
        game.setPotBalance(potBalance);
    }
}