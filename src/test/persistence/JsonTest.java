package persistence;

import model.Game;
import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

// JsonTest class used for testing
// Some of this code in this class is sourced from the provided CPSC 210 JSON program
public class JsonTest {
    protected void checkPlayer(String playerName, int playerBalance, Player player) {
        assertEquals(playerName, player.getPlayerName());
        assertEquals(playerBalance, player.getBalance());
    }
}

