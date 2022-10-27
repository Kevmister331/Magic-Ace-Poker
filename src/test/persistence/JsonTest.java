package persistence;

import model.Game;
import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlayer(String playerName, int playerBalance, Player player) {
        assertEquals(playerName, player.getPlayerName());
        assertEquals(playerBalance, player.getBalance());
    }
}

