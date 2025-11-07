import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


/**
* This file is used for testing your code. You can ignore the entire file during your development
* You can click the button next to src.test.TestClass to test your code.
*
* This is also how we are going to grade your work! Of course, there will be more test cases
*/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestClass {
   private ByteArrayOutputStream out;
   private ByteArrayInputStream in;


   @BeforeEach
   public void setup() {
       out = new ByteArrayOutputStream();
       System.setOut(new PrintStream(out));

   }

    @Order(1)
    @Test
    void testPlayCardToString() {
        // Test toString method with valid card types
        PlayCard redPlusOne = new PlayCard("red", "+1");
        PlayCard bluePlusTwo = new PlayCard("blue", "+2");
        PlayCard greenMinusOne = new PlayCard("green", "-1");
        PlayCard yellowMinusTwo = new PlayCard("yellow", "-2");

        Assertions.assertEquals("PlayCard{red,+1}", redPlusOne.toString());
        Assertions.assertEquals("PlayCard{blue,+2}", bluePlusTwo.toString());
        Assertions.assertEquals("PlayCard{green,-1}", greenMinusOne.toString());
        Assertions.assertEquals("PlayCard{yellow,-2}", yellowMinusTwo.toString());
    }

    // ========== PLAYER CLASS TESTS ==========

    @Order(2)
    @Test
    void testPlayerConstructor() {
        // Test Player constructor and basic getters
        Player player1 = new Player("Alice", "red");
        Player player2 = new Player("Bob", "blue");

        Assertions.assertEquals("Alice", player1.getName());
        Assertions.assertEquals("red", player1.revealColor());
        Assertions.assertEquals("Bob", player2.getName());
        Assertions.assertEquals("blue", player2.revealColor());
    }

    @Order(3)
    @Test
    void testPlayerDrawCard() {
        // Test drawing cards functionality
        Player player = new Player("TestPlayer", "green");

        // Draw first card
        PlayCard card1 = new PlayCard("red", "+1");
        player.drawCard(card1);

        // Draw second card
        PlayCard card2 = new PlayCard("blue", "+2");
        player.drawCard(card2);

        // Verify cards were drawn
        PlayCard playedCard1 = player.playCard(0);
        PlayCard playedCard2 = player.playCard(1);

        Assertions.assertEquals(card1, playedCard1);
        Assertions.assertEquals(card2, playedCard2);
    }

    @Order(4)
    @Test
    void testPlayerDrawCardFullHand() {
        // Test drawing cards when hand is full
        Player player = new Player("TestPlayer", "yellow");

        // Fill the hand with 5 cards
        for (int i = 0; i < 5; i++) {
            PlayCard card = new PlayCard("red", "+1");
            player.drawCard(card);
        }

        // Try to draw one more card (should fail)
        PlayCard extraCard = new PlayCard("blue", "+2");
        player.drawCard(extraCard);

        // Verify the extra card wasn't added
        assertNull(player.playCard(5)); // Index 5 should be invalid
    }

    @Order(5)
    @Test
    void testPlayerPlayCardValidIndex() {
        // Test playing cards with valid indices
        Player player = new Player("TestPlayer", "purple");

        // Draw a card
        PlayCard card = new PlayCard("red", "+1");
        player.drawCard(card);

        // Play the card
        PlayCard playedCard = player.playCard(0);

        Assertions.assertEquals(card, playedCard);
        assertNull(player.playCard(0)); // Card should be removed
    }

    @Order(6)
    @Test
    void testPlayerPlayCardInvalidIndex() {
        // Test playing cards with invalid indices
        Player player = new Player("TestPlayer", "red");

        // Try to play card at invalid indices
        assertNull(player.playCard(-1)); // Negative index
        assertNull(player.playCard(5));  // Index out of bounds
        assertNull(player.playCard(0));  // Empty slot
    }

    @Order(7)
    @Test
    void testPlayerPlayCardNullSlot() {
        // Test playing from a null slot
        Player player = new Player("TestPlayer", "blue");

        // Draw a card at index 0
        PlayCard card = new PlayCard("green", "+2");
        player.drawCard(card);

        // Play the card
        player.playCard(0);

        // Try to play from the now-empty slot
        assertNull(player.playCard(0));
    }

    @Order(8)
    @Test
    void testPlayerToString() {
        // Test toString method
        Player player = new Player("TestPlayer", "red");

        // Draw some cards
        player.drawCard(new PlayCard("red", "+1"));
        player.drawCard(new PlayCard("blue", "+2"));

        String result = player.toString();

        // Verify the format
        assertTrue(result.startsWith("TestPlayer has the following cards:"));
        assertTrue(result.contains("PlayCard{red,+1}"));
        assertTrue(result.contains("PlayCard{blue,+2}"));
        assertFalse(result.contains("2:")); // Index 2 should not be present
    }


    @Order(10)
    @Test
    void testPlayerToStringFullHand() {
        // Test toString with full hand
        Player player = new Player("FullPlayer", "yellow");

        // Fill the hand
        for (int i = 0; i < 5; i++) {
            PlayCard card = new PlayCard("red", "+1");
            player.drawCard(card);
        }

        String result = player.toString();

        // Verify all indices are present
        for (int i = 0; i < 5; i++) {
            assertTrue(result.contains(i + ": PlayCard{red,+1}"));
        }
    }

    @Order(11)
    @Test
    void testPlayerCardManagement() {
        // Test comprehensive card management
        Player player = new Player("CardManager", "purple");

        // Draw cards
        PlayCard card1 = new PlayCard("red", "+1");
        PlayCard card2 = new PlayCard("blue", "+2");
        PlayCard card3 = new PlayCard("green", "-1");

        player.drawCard(card1);
        player.drawCard(card2);
        player.drawCard(card3);

        // Play middle card
        PlayCard playedCard = player.playCard(1);
        Assertions.assertEquals(card2, playedCard);

        // Draw new card in the middle
        PlayCard card4 = new PlayCard("yellow", "-2");
        player.drawCard(card4);

        // Verify the new card is at index 1
        PlayCard retrievedCard = player.playCard(1);
        Assertions.assertEquals(card4, retrievedCard);
    }

    @Order(12)
    @Test
    void testPlayerImmutability() {
        // Test that player name and color are immutable
        Player player = new Player("OriginalName", "originalColor");

        String originalName = player.getName();
        String originalColor = player.revealColor();

        // Verify the values remain the same
        Assertions.assertEquals(originalName, player.getName());
        Assertions.assertEquals(originalColor, player.revealColor());
    }

}


