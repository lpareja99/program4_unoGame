package uno.cards;

import uno.player.*;
import uno.uno_game.Game_Engine;
import uno.cards.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Player's response to a Wild card on top of draw pile.
 *
 * @author  S. Sigman
 * @version v 2.0 April, 28, 2017
 */
public class WildCardTests
{
    Game_Engine dealer;
    /**
     * Default constructor for test class WildCardTests
     */
    public WildCardTests()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        dealer = new Game_Engine(1);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testWildWhenPlayingCard()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Action_Card topOfDiscPile = new Action_Card(UNO_Card.BLACK,
                                                  Action_Card.WILD);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.BLUE, Action_Card.SKIP));
        
        // set current color to Blue
        dealer.currentColor = UNO_Card.BLUE;
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("Color Inquiry", dealer.colorInquiry);
        assertTrue("B2 Discarded", 
             (((Face_Card)dealer.discardedCard).getColor() == UNO_Card.BLUE) &&
             (((Face_Card)dealer.discardedCard).getFaceNumber() == 2));
        assertTrue("No Card Drawn", dealer.deck.size() == 1);
        assertTrue("3 Cards in Hand", numCards == 3 && me.numberOfCards() == 3);
    }
    
    @Test
    public void testWildWhenNoCard2Play()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Action_Card topOfDiscPile = new Action_Card(UNO_Card.BLACK,
                                                  Action_Card.WILD);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.BLUE, Action_Card.SKIP));
        
        // set current color to Blue
        dealer.currentColor = UNO_Card.GREEN;
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("Color Inquiry", dealer.colorInquiry);
        assertTrue("Card Drawn", dealer.deck.isEmpty());
        assertTrue("Player Passes", dealer.playerPassing == me);
        assertTrue("3 Cards in Hand", numCards == 5 && me.numberOfCards() == 5);
    }
}
