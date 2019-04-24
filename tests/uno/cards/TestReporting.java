package uno.cards;

import uno.player.*;
import uno.uno_game.Game_Engine;
import uno.cards.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the reporting functions of the Player Class.
 *
 * @author S. Sigman
 * @version v 2.0 4/28/2017
 */
public class TestReporting
{
    private Game_Engine dealer;
    /**
     * Default constructor for test class TestReporting
     */
    public TestReporting()
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
    public void testNameReporting()
    {
        // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // check the ask for name
       
        assertTrue("Ask for Name", me.whoAreYou() != null);
    }
    
    @Test
    public void testReportScore()
    {
                // set up the hand for input
        Hand hand = new Hand();
        hand.addCard(new Face_Card(UNO_Card.RED, 2));
        hand.addCard(new Face_Card(UNO_Card.RED, 3));
        hand.addCard(new Face_Card(UNO_Card.BLUE, 2));
        hand.addCard(new Action_Card(UNO_Card.YELLOW, Action_Card.DRAW_2));
        hand.addCard(new Action_Card(UNO_Card.BLACK, Action_Card.WILD));
        hand.addCard(new Action_Card(UNO_Card.BLACK, Action_Card.WILD));      
            
        // set useColorInstead - deprecated - used only for compatibility
        boolean useColorInstead = false;
        
        // create the Player
        Player me = new Player_ScottS(hand, dealer);
       
        // check the ask for name
       
        assertTrue("Ask for Name", me.scoreOnCards() == 127);
    }
        
}
