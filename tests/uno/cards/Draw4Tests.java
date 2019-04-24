package uno.cards;

import uno.player.*;
import uno.uno_game.Game_Engine;
import uno.cards.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Player's respons to Wild Draw 4 on top of discard pile.
 *
 * @author  S. Sigman
 * @version v 2.0 4/27/2017
 */
public class Draw4Tests
{
    private Game_Engine dealer;
    /**
     * Default constructor for test class Draw4Tests
     */
    public Draw4Tests()
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
    public void testDraw4ByDrawingCard()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Action_Card topOfDiscPile = new Action_Card(UNO_Card.BLACK,
                                                  Action_Card.WILD_DRAW_4);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.BLUE, Action_Card.SKIP));
        dealer.deck.addLast(new Face_Card(UNO_Card.RED, 7));
        dealer.deck.addLast(new Face_Card(UNO_Card.YELLOW, 5));        
        dealer.deck.addLast(new Face_Card(UNO_Card.GREEN, 2));
        
        // signal player's action is to draw
        dealer.drawStatus = true;
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("DrawInquiry", dealer.drawInquiry);
        assertTrue("4 Cards Drawn", dealer.deck.isEmpty()); 
        assertTrue("Player passes", dealer.playerPassing == me);
        assertTrue("3 Cards in Hand", numCards == 8 && me.numberOfCards() == 8);
    }
    
    @Test
    public void testDraw4NoCardsDrawn()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        
        // define the top of the discard pile
        Action_Card topOfDiscPile = new Action_Card(UNO_Card.BLACK,
                                                  Action_Card.WILD_DRAW_4);
                                                  
        // set up deck to draw
        dealer.deck.addLast(new Action_Card(UNO_Card.GREEN, Action_Card.SKIP));
        dealer.deck.addLast(new Face_Card(UNO_Card.RED, 7));
        dealer.deck.addLast(new Face_Card(UNO_Card.YELLOW, 5));        
        dealer.deck.addLast(new Face_Card(UNO_Card.GREEN, 2));
        
        // set up the currrent playing color
        dealer.currentColor = UNO_Card.BLUE;
        
        // signal player's action is to draw
        dealer.drawStatus = false;
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // ask player to play
        int numCards = me.play(topOfDiscPile, useColorInstead);
       
        assertTrue("DrawInquiry", dealer.drawInquiry);
        assertTrue("Check of Current Color", dealer.colorInquiry); 
        assertTrue("No cards drawn", dealer.deck.size() == 4);
        assertTrue("YD2 Discarded", 
             (((Face_Card)dealer.discardedCard).getColor() == UNO_Card.BLUE) &&
             (((Face_Card)dealer.discardedCard).getFaceNumber() == 2));
        assertTrue("3 Cards in Hand", numCards == 3 && me.numberOfCards() == 3);
    }
}
