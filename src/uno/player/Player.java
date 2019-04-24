package uno.player;

import uno.uno_game.Game_Engine;
import uno.cards.*;
import java.util.Scanner;

/**
 * The abstract class Player provides a base class from which all
 * player classes must be derived.  Each of the abstract methods must
 * be implemented.
 * 
 * @author S. Sigman
 * @version v1.0 April 8, 2015
 */
public abstract class Player
{
    protected Game_Engine dealer;
    protected Hand hand;

    //Scanner to get name
    private Scanner in;


    
    /**
     * This method constructs s a new player object.  The player
     * object constructed has the specified hand of cards and
     * a reference to the dealer who dealt the hand.
     * 
     * @param hand The hand of UNO_Cards.
     * @param dealer A reference to the Game_Engine object that is controling
     * the game play.
     */
    public Player(Hand hand, Game_Engine dealer) 
    {
        this.dealer = dealer;
        this.hand = hand;
    }
    
    /**
     * The play method must be implemented by the derived player class. It must
     * conform to the rules specified in the assignment design.
     * 
     * @param card The top card on the discard pile.
     * @param useColorInstead If true this flag indicates that the player should
     * query the dealer for the current color and use this information instead of 
     * card on top of the draw pile to make their play. If false, use the card passed
     * via the card parameter as the top card on the draw pile.
     * @return The number of cards remaining in the players hand after the player
     * object has played.
     * @deprecated  The useColorInstead parameter is deprecated.  It is currently
     * supported, but will go away in later releases.
     */
    public int play(UNO_Card card, boolean useColorInstead){

        return 0;
    }
    
    /**
     * The whoAreYou method reports the player object's name.
     *
     * @return The name of the player.
     */
    public String whoAreYou(){

        in = new Scanner(System.in);
        System.out.println("Enter your name and press Enter: ");
        String name = in.nextLine();
        return name;
    }
    
    /**
     * The scoreOnCards method reports the score of the cards in the player's
     * hand.  It should only be called after a game has been one.  The score
     * should be calculated using standard UNO scoring rules.
     * 
     * @return The value of the cards in the player's hand when the method is
     * called.
     */
    public abstract int scoreOnCards();
    
    /**
     * The numberOfCards method reports the number of cards in the player's hand.
     * 
     * @return The number of cards in the player's hand.
     */
    public int numberOfCards()
    {
        return hand.numberOfCards();
    }
}
