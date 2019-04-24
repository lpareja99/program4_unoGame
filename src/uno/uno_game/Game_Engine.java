package uno.uno_game;

import uno.cards.*;
import uno.player.*;
import java.util.ArrayDeque;

/**
 * <p>Stub for the Game_Engine class used for testing purposes. This
 * documentation represents the documentation for stubbed version
 * of the Game_Engine used for testing.  Documentation for the
 * production Game_Engine class may be found at: </p>
 *
 * <a href="http://mcs.drury.edu/UNO">Official UNO Documentation</a>
 * 
 * @author S. Sigman 
 * @version 2.0 April 27, 2017
 */
public class Game_Engine
{
    /**
     * Holds the card discarded, null if not called.
     */
    public UNO_Card discardedCard;

    /**
     * Drawing Status, false by default.
     */
    public boolean drawStatus;

    /**
     * Signals an inquiry to draw a card, default false.
     */
    public boolean drawInquiry;

    /**
     * The simulated deck Pile, initially empty.
     */
    public ArrayDeque<UNO_Card> deck;

    /**
     *  Current playing color, default UNO_Card.BLACK.
     */
    public int currentColor;

    /**
     * Player calling UNO, default null.
     */
    public Player playerCallingUNO;

    /**
     * Player who won, default null.
     */
    public Player playerWinning;

    /**
     * Player who passes, default null.
     */
    public Player playerPassing;

    /**
     * Signals a call to check playing color.
     */
    public boolean colorInquiry;

    /**
     * Array of 4 players values: 1, 4, 2, 10
     */
    public int [] numCardsInHands;

    /**
     * <p>Simulated Constructor for Game_Engine.  Testers must modify
     * the code by replacing the Player class Player_ScottS with the
     * name of their own player class.</p>
     *
     * @param numGames  The number of games to play.
     */
    public Game_Engine(int numGames) 
    {
        discardedCard = null;
        drawStatus = false;
        drawInquiry = false;
        deck = new ArrayDeque();
        currentColor = UNO_Card.BLACK;
        playerCallingUNO = null;
        playerWinning = null;
        playerPassing = null;
        colorInquiry = false;
        numCardsInHands = new int[] {1,4, 2, 10};
    }

    /**
     * Method to call to discard a card.
     *
     * @param card Card to discard.
     */
    public void discardCard(UNO_Card card) 
    {
       discardedCard = card;
    }

    /**
     * <p>Method to query the game engine to see if the player should
     * draw a card.  Must be called when the player sees a WILD_DRAW_4
     * or a DRAW_2. In general, either of these cards on the top of the
     * discard pile could indicate that the previous player drew or that
     * you must draw.  A call to this method clears up the ambiguity.</p>
     *
     * @return True if the player must draw cards.  False, if the player
     * does not draw, but must check on the current color.
     */
    public boolean doIDraw() {
        drawInquiry = true;
        return drawStatus;
    }

    /**
     * <p>Method draws a single card from the deck and returns the
     * card to the player.</p>
     *
     * @return The top card of the deck.
     */
    public UNO_Card drawCard() 
    {
        return deck.removeFirst(); 
    }

    /**
     * <p>Method to return the current play color.  It will need be called
     * when a WILD_DRAW_4 and DRAW_2 is passed to the player.</p>
     *
     * @return The current playing color.
     */
    public int getCurrent_Color()
    {
        colorInquiry = true;
        return currentColor;
    }

    /**
     * <p>This method should be called when the player has one
     * card left in their hand.</p>
     *
     * @param player Me, a player with a single card left in my hand.
     */
    public void notifyUNO(Player player)
    {
        playerCallingUNO = player;
    }

    /**
     * <p>This method must be called when a player has played the
     * last card in their hand.  It notifies the game controller that
     * a round has been won.</p>
     *
     * @param player Me, the player who just won the game.
     */
    public void notifyWon(Player player)
    {
        playerWinning = player;
    }

    /**
     * <p>notifyPass must be called by a player who is passing on
     * a turn.  Passing means the player is not playing a card.  A
     * player passing on a turn must follow official UNO rules in
     * deciding when to pass, namely they do not have a card they
     * can play.</p>
     *
     * @param player Me, the player with no card to play.
     */
    public void notifyPass(Player player)
    {
        playerPassing = player;
    }

    /**
     * <p>setPlayColor is called by a player who needs to set the
     * playing color.  This will happen when the player plays a
     * WILD card or a WILD_DRAW_4 card.</p>
     *
     * @param clr The new playing color.
     */
    public void setPlayColor(int clr)
    {
        currentColor = clr;
    }

    /**
     * <p>The method numCards4EachPlayer returns an array containing
     * the number of cards in each player's hand with the exception of
     * the player making the call. Hence, the size of the array will
     * be one less than the number of players in the game. The card
     * count at position 0 corresponds to the number of cards in the
     * hand of the next player to play and represents the counts for
     * players in order of play at the time the call is made. </p>
     *
     * @return An array containing the number of cards in each player's
     * hand.
     */
    public int[] numCards4EachPlayer()
    {
        return numCardsInHands;
    }
}
