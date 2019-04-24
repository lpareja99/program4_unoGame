package uno.cards;

import uno.player.*;
import uno.uno_game.Game_Engine;
import uno.cards.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test response to Draw 2 on top of discard pile.
 *
 * @author  S. Sigman
 * @version v 2.0 4/27/2017
 */
public class Draw2Tests
{
    private Game_Engine dealer;
    
    /**
     * Default constructor for test class Draw2Tests
     */
    public Draw2Tests()
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
    public void testDraw2ByDrawingCard()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Action_Card topOfDiscPile = new Action_Card(UNO_Card.GREEN,
                                                  Action_Card.DRAW_2);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.BLUE, Action_Card.SKIP));
        dealer.deck.addLast(new Face_Card(UNO_Card.RED, 7));
        
        // signal player's action is to draw
        dealer.drawStatus = true;
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("DrawInquiry", dealer.drawInquiry);
        assertTrue("2 Cards Drawn", dealer.deck.isEmpty()); 
        assertTrue("Player passes", dealer.playerPassing == me);
        assertTrue("3 Cards in Hand", numCards == 6 && me.numberOfCards() == 6);
    }
    
    @Test
    public void testDraw2NoCardsDrawn()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Action_Card topOfDiscPile = new Action_Card(UNO_Card.GREEN,
                                                  Action_Card.DRAW_2);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.BLUE, Action_Card.SKIP));
        
        // signal player's action is to draw
        dealer.drawStatus = false;
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("DrawInquiry", dealer.drawInquiry);
        assertTrue("No Card Drawn", dealer.deck.size() == 1); 
        assertTrue("Discard card", dealer.discardedCard != null);
        assertTrue("YD2 Discarded", 
             (((Action_Card)dealer.discardedCard).getColor() == UNO_Card.YELLOW) &&
             (((Action_Card)dealer.discardedCard).getAction() == Action_Card.DRAW_2));
        assertTrue("3 Cards in Hand", numCards == 3 && me.numberOfCards() == 3);
    }
}
