package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Game class
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

    @Test
    void testRemovePlayer() {
        game.addPlayer("Kevin", 0);
        game.addPlayer("Eric", 0);
        game.addPlayer("Allan", 0);

        assertEquals(game.getPlayers().size(), 3);

        game.removePlayer("Kevin");
        assertEquals(game.getPlayers().size(), 2);

        game.removePlayer("Jordan"); // NAME NOT IN LIST
        assertEquals(game.getPlayers().size(), 2);

        game.removePlayer("Eric");
        assertEquals(game.getPlayers().size(), 1);
    }

    @Test
    void testMakeBet() {
        game.addPlayer("Kevin", 1000);
        game.addPlayer("Eric", 1000);

        game.makeBet(game.getPlayerByName("Kevin"), 300);

        assertEquals(game.getPotBalance(), 300);
        assertEquals(game.getPlayerByName("Kevin").getBalance(), 700);
        assertEquals(game.getPlayerByName("Eric").getBalance(), 1000);

        game.makeBet(game.getPlayerByName("Kevin"), 1001);
        assertEquals(game.getPlayerByName("Eric").getBalance(), 1000);
        assertEquals(game.getPotBalance(), 300);
    }

    @Test
    void testClaimPot() {
        game.addPlayer("Kevin", 1000);
        game.addPlayer("Eric", 1000);

        game.makeBet(game.getPlayerByName("Kevin"), 400);
        game.makeBet(game.getPlayerByName("Eric"), 800);

        assertEquals(game.getPotBalance(), 1200);
        assertEquals(game.getPlayerByName("Kevin").getBalance(), 600);
        assertEquals(game.getPlayerByName("Eric").getBalance(), 200);

        game.claimPot(game.getPlayerByName("Kevin"));

        assertEquals(game.getPotBalance(), 0);
        assertEquals(game.getPlayerByName("Kevin").getBalance(), 1800);
        assertEquals(game.getPlayerByName("Eric").getBalance(), 200);
    }

    @Test
    void testGetPlayerByName() {
        game.addPlayer("Kevin", 0);
        game.addPlayer("Eric", 0);
        assertEquals(game.getPlayerByName("Eric"), game.getPlayers().get(1));
        assertEquals(game.getPlayerByName("Jordan"), null);
    }


}
