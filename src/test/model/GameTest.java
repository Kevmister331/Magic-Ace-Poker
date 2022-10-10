package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
    }

    @Test
    void testConstructor() {
        assertEquals(game.getPotBalance(), 0);
        assertEquals(game.getPlayers().size(), 0);
    }

    @Test
    void testAddPlayer() {
        game.addPlayer("Kevin", 0);

        assertEquals(game.getPlayers().size(), 1);

        game.addPlayer("Eric", 0);
        game.addPlayer("Allan", 0);

        assertEquals(game.getPlayers().size(), 3);

        // tests the name of the first player in the list
        assertEquals(game.getPlayers().get(0).getPlayerName(), "Kevin");
        assertEquals(game.getPlayers().get(0).getBalance(), 0);
    }

}
