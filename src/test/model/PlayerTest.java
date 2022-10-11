package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Player class
class PlayerTest {
    private Player player1; // starting bal: 0
    private Player player2; // starting bal: 100
    private Player player3; // starting bal: 500

    @BeforeEach
    void runBefore() {
        player1 = new Player("Billy", 0);
        player2 = new Player("Joseph", 100);
        player3 = new Player("Hecarim", 500);
    }

    @Test
    void testConstructor() {
        assertEquals(player1.getPlayerName(), "Billy");
        assertEquals(player2.getPlayerName(), "Joseph");
        assertEquals(player3.getPlayerName(), "Hecarim");

        assertEquals(player1.getBalance(), 0);
        assertEquals(player2.getBalance(), 100);
        assertEquals(player3.getBalance(), 500);
    }

    @Test
    void testAddBalance() {
        player1.addBalance(0);
        player2.addBalance(100);

        assertEquals(player1.getBalance(), 0);
        assertEquals(player2.getBalance(), 200);
    }

    @Test
    void testSubtractBalance() {
        player2.subtractBalance(100);
        player3.subtractBalance(250);

        assertEquals(player2.getBalance(), 0);
        assertEquals(player3.getBalance(), 250);
    }

}

