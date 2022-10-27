package persistence;

import model.Game;
import model.Player;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Game game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGame() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGame.json");
        try {
            Game game = reader.read();
            assertEquals(0, game.getPotBalance());
            assertEquals(0, game.getPlayers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGame() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGame.json");
        try {
            Game game = reader.read();
            List<Player> players = game.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Reginald", 300, players.get(0));
            checkPlayer("Jordan", 200, players.get(1));
            assertEquals(500, game.getPotBalance());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}