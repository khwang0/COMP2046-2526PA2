import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Gameboard {
    /**
     * The finish line is 10 steps away from the start line.
     */
    public static final int GOAL_LINE = 10;
    
    
    /**
     * The types of play cards. In this version we don't have the rainbow card
     */
    public static final String[] TYPES = {"+1", "+1","+1","+1", "+1","+2", "-1", "-1" };

    /**
     * The colors of the turtles and the players.
     */
    public static final String[] COLORS = {"red", "blue", "green", "yellow", "purple"};
    /**
     * The number of turtles is actually the same as the number of colors.
     */
    public static final int MAX_TURTLES = COLORS.length;


    /**
     * The total number of play cards.
     */
    public static final int MAX_PLAY_CARDS = COLORS.length * TYPES.length;

    /**
     * The play cards.
     */
    public final PlayCard[] playCards = new PlayCard[MAX_PLAY_CARDS];
    /**
     * The discard pile.
     */
    private final PlayCard[] discardPile = new PlayCard[MAX_PLAY_CARDS];

    /**
     * The players.
     */
    private final Player[] players;
    /**
     * This is the board which logically has 5 by 10 squares that exactly five sqaures are occupied by the turtles.
     * .
     */
    private final Turtle[][] turtles;

    /**
     * Print the board.
     * 
     * It shall prints the turtles on the board where the starting line is at the left
     * and the finish line is at the right.
     * 
     * For example, it may look like:
     * 
     -----------------------------------------
    |   |   |   |   |   |   |   |   |   |   |
    |   |   |   |   |   |   |   |   |   |   |
    |   |   |   |   |   |   |   |   |   |   |
    |   |   |   |   |   | Y | B |   |   |   |
    |   |   |   |   |   | P | R | G |   |   |
    -----------------------------------------
     *
     * The turtles shall be represented by their color symbols Y P B R G, and the empty squares 
     * shall be represented by spaces.
     * 
     * No space shall be under a turtle.
     * 
     * You should use the symbol dash - and pine | to draw the board.
     */
    public void printBoard() {
        //TODO
    }

    /**
     * Default constructor.
     * 
     * It creates a gameboard with 4 players.
     */
    public Gameboard() {
        //TODO
    }

    /**
     * Constructor with the number of players.
     * 
     * It creates a gameboard with the given number of players.
     * 
     * @param numberOfPlayers the number of players
     */
    public Gameboard(int numberOfPlayers) {
        //TODO        
    }

    /**
     * Start the game.
     * 
     * It starts the game by drawing cards for each player and placing the turtles on the board.
     * 
     * This method is partially finish. Complete the TODO part.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        for (int p = 0; !isGameOver(); p = (p + 1) % players.length) {
            Player player = players[p];
            System.out.println("This is the player's turn: " + player.getName());
            System.out.println(player);
            PlayCard card = null;
            debugPlayCards();

            do {
                System.out.println("Enter the index of the card you want to play: ");
                try {
                    int index = scanner.nextInt();
                    card = player.playCard(index);
                } catch (Exception e) {
                    //invalid read of index
                    scanner.nextLine();
                }

                if (card == null) {
                    System.out.println("Invalid card index. Please try again.");
                }
            } while (card == null);

            //TODO
            //Complete the remaining logic of each player's turn.


            //no more card, move discardPile to playCards and shuffle the playCards again
        }
        System.out.println("\n\nGame over!");
        Player winner = getWinner();
        if (winner != null) {
            System.out.println("The winner is " + winner.getName());
            System.out.println("The winner color is " + winner.revealColor());
        } else {
            System.out.println("No winner found. The game is a draw.");
        }

    }

    /**
     * To print out the structure of your playcards and discardPile for debug purpose
     *
     * This method has been completed for you. Don't change.
     */
    private void debugPlayCards() {
        System.out.print("playcards  :");
        for (PlayCard c : playCards)
            System.out.print(c == null ? "o" : "x");
        System.out.println();
        System.out.print("discardPile:");
        for (PlayCard c : discardPile)
            System.out.print(c == null ? "o" : "x");
        System.out.println();
        //check duplicate
        for (PlayCard c : playCards) {
            for (PlayCard d : discardPile)
                if (c == d && c != null)
                    System.out.println("Error! a card in both discardPile and playCards");
        }
    }


    /**
     * Get the winner of the game.
     * 
     * It returns the player who has the turtle on the finish line.
     * 
     * Player who wins the game must have the turtle on the finish line.
     * When the games end if no player has the turtle on the finish line, there will be no winner 
     * and the method shall return null.
     * 
     * If there are multiple players who have the turtle on the finish line, the player who have a 
     * lower turtle of the stack wins.
     */
    public Player getWinner() {
        //TODO
    }


    /**
     * Main method to run the game.
     * 
     * This has been completed for you. Don't change.
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                int numberOfPlayers = Integer.parseInt(args[0]);
                if (numberOfPlayers < 2 || numberOfPlayers > MAX_TURTLES) {
                    System.out.println("Number of players must be between 2 and " + MAX_TURTLES);
                    return;
                }
                Gameboard gameboard = new Gameboard(numberOfPlayers);
                gameboard.startGame();
            } catch (NumberFormatException e) {
                System.out.println("run it by \"java Gameboard NO_OF_PLAYERS\", e.g. java Gameboard 4");

            }
            return;
        } 
        Gameboard gameboard = new Gameboard();
        gameboard.startGame();
    }    
    
    /**
     * Prepare the play cards.
     * 
     * It prepares the play cards by creating a play card for each color and type and put them into the playCards array.
     * 
     * Noted, throughout the assignment you cannot use any API such as Arrays, Vector, List, etc. to manipulate the array
     * You are required to use built-in java array only, i.e., int[], String[], Turtle[], etc.
     * 
     */
    public void preparePlayCard() {
        //TODO
    }

    /**
     * Shuffle the play cards.
     * 
     * It shuffles the play cards by randomly swapping the cards in the playCards array.
     * 
     * Noted, throughout the assignment you cannot use any API such as Arrays, Vector, List, etc. to manipulate the array
     * You are required to use built-in java array only, i.e., int[], String[], Turtle[], etc.
     * 
     * A random shuffle - it should be random, running the program twice should give you different results.
     * ThreadLocalRandom.current().nextInt() must be used in this method.
     * 
     * Whenever this method is called, the number of non-null cards in the playCards should remains the same.
     * No non-null card should be preceded by a null card in the array playCard.
     */
    public void shufflePlayCards() {
        //TODO
    }

    /**
     * To insert a card to the last position of a cardPile.
     * This method is completed for you. Don't change.
     *
     * @param card
     * @param cardPile
     */
    private void insert(PlayCard card, PlayCard[] cardPile) {
        if (card == null)
            return;
        for (int i = 0; i < cardPile.length; i++) {
            if (cardPile[i] == null) {
                cardPile[i] = card;
                return;
            }
        }
        System.out.println("Card pile is full, cannot insert card: " + card);
    }
    /**
     * Draw a card from the playCards array and give it to the player.
     * 
     * It assigns the top card of the playCards array to the player. The card is removed from the playCards array.
     * Every card in the playCards array is shifted to the left by one position.
     * 
     * i.e. if the cardPile originally contains [A, B, C, D, E], after drawing A, the cardPile will be [B, C, D, E, null].
     * 
     * @param player
     */
    public void drawCard(Player player) {
        //TODO
    }

    /**
     * Move a turtle on the board.
     * 
     * It moves the turtle on the board by the given number of steps. 
     * According to the rules of the game, the turtle will carry other turtles on its back.
     * The relative vertical position of the turtles being moved needs to be preserved, i.e.
     * if Blue is on the top of Red, after moving Red, Blue should still be on the top of Red.
     * 
     * A turtle at the start line will stay at the start line if it is moved backward.
     * A turtle will arrive the finish line if it move beyond the finish line.
     * 
     * Note: in this assignment, there is no rainbow card.
     * 
     * @param turtle
     * @param steps
     */
    public void moveTurtle(Turtle turtle, int steps) {
        //TODO
    }
    /**
     * Check if the game is over.
     * 
     * It checks if the game is over by checking if there is a turtle on the finish line.
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        //TODO
    }
}