package uno.cards;

import uno.player.*;
import uno.uno_game.Game_Engine;
import uno.cards.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the Basic play functions.
 *
 * @author  S. Sigman
 * @version v 2.0 4/27/2017
 */
public class BasicPlayTests
{
    private Game_Engine dealer;
    
    /**
     * Default constructor for test class BasicPlayTests
     */
    public BasicPlayTests()
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
    public void testSelectPlayableCard()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Face_Card topOfDiscPile = new Face_Card(UNO_Card.BLUE, 9);
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_Scott(hand, dealer);

        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("Card Discarded", dealer.discardedCard != null);
        assertTrue("Blue 2 Discarded", (((Face_Card)dealer.discardedCard).getColor()== UNO_Card.BLUE)
                                      && (((Face_Card)dealer.discardedCard).getFaceNumber() == 2));
        assertTrue("3 Cards in Hand", numCards == 3 && me.numberOfCards() == 3);
    }
    
    @Test
    public void testPassWhenNoPlayableCard()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // Place Card in Deck
        dealer.deck.addLast(new Action_Card(UNO_Card.RED, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Face_Card topOfDiscPile = new Face_Card(UNO_Card.GREEN, 7);
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player(hand, dealer) {
            @Override
            public int play(UNO_Card card, boolean useColorInstead) {
                return 0;
            }

            @Override
            public int scoreOnCards() {
                return 0;
            }
        };
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("Card Drawn", dealer.deck.isEmpty());
        assertTrue("I Passed", (dealer.playerPassing == me));
        assertTrue("5 Cards in Hand", numCards == 5 && me.numberOfCards() == 5);
    }
}
