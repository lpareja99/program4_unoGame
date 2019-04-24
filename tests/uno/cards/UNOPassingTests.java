package uno.cards;

import uno.player.*;
import uno.uno_game.Game_Engine;
import uno.cards.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests if Player passes and calls win correctly.
 * 
 * @author  S. Sigman
 * @version v 2.0 4/28/2017
 */
public class UNOPassingTests
{
    private Game_Engine dealer;
    
    /**
     * Default constructor for test class UNOPassingTests
     */
    public UNOPassingTests()
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
    public void testCallingUNO()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        
        // define the top of the discard pile
        Face_Card topOfDiscPile = new Face_Card(UNO_Card.RED,6);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.BLACK, Action_Card.WILD));
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("R2 or R3 Discarded", 
             (((Face_Card)dealer.discardedCard).getColor() == UNO_Card.RED) &&
             (((Face_Card)dealer.discardedCard).getFaceNumber() == 2 ||
               ((Face_Card)dealer.discardedCard).getFaceNumber() == 3));
        assertTrue("No Card Drawn", dealer.deck.size() == 1);
        assertTrue("UNO Reported", dealer.playerCallingUNO == me);
        assertTrue("1 Card left in Hand", numCards == 1 && me.numberOfCards() == 1);
    }
    
    @Test
    public void testReportWin()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        
        // define the top of the discard pile
        Face_Card topOfDiscPile = new Face_Card(UNO_Card.GREEN,2);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.BLACK, Action_Card.WILD));
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("R2 Discarded", 
             (((Face_Card)dealer.discardedCard).getColor() == UNO_Card.RED) &&
             ((Face_Card)dealer.discardedCard).getFaceNumber() == 2);
        assertTrue("No Card Drawn", dealer.deck.size() == 1);
        assertTrue("Win Reported", dealer.playerWinning == me);
        assertTrue("1 Card left in Hand", numCards == 0 && me.numberOfCards() == 0);
    }    
}
