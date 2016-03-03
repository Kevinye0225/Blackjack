
import java.util.Scanner;

/**
 * This is the main blackjack class
 * @author kevin
 *
 */
public class Blackjack {
	private Cards card;
	private Deal start;
	private int position;
	private int playerValue;
	private int firstP;
	private int secondP;
	private int firstD;
	private int secondD;
	private int dealerValue;
	
	
//	Constructor
	public Blackjack(){
		this.start = new Deal();
		this.card = start.getCard();
      // player and dealer each get two cards 
		this.position = 4;
		this.playerValue = start.player();
		this.dealerValue = start.dealer();
		this.firstP = start.firstPlayer();
		this.firstD = start.firstDealer();
		this.secondD = start.secondDealer();
		this.secondP = start.secondPlayer();
	}
	
    public void greeting(){
    	System.out.println("Hello, welcome to Blackjack");
    }
    
//    deal card
    public int deal(){
	   int value = card.getCard(this.position);
	   this.position += 1;
	   return value;
   }
   
//    check if either dealer or player has blackjack
    public boolean checkBlackjack(){
 	   greeting();
	   boolean result = false;
	   if (playerValue == 21){
		   if (dealerValue == 21){
		      System.out.println("Push");
		      result = true;
		   } else {
			  System.out.println("Blackjack! You win");
			  result = true;
		   }
	   } else if (dealerValue == 21){
		   System.out.println("Dealer has blackjack, you lose");
		   result = true;
	   }
		   
	   return result;
   }
   
//    main game
   public void gamePlay(){

	   
	   Scanner in = new Scanner(System.in);
	   

	   boolean hit = true;
	   while (this.playerValue < 21 && hit){
		   if(this.firstD == 11 || this.firstD == 1){
		     System.out.println("Dealer showing Ace, "
			   		+ "You have " + this.firstP + " and " +
		    		 this.secondP + ", hit or stand:");
		   } else {
			   System.out.println("Dealer showing " + this.firstD + ", "
				   		+ "You have " + this.firstP + " and " +
			    		 this.secondP + ", hit or stand:");
		   }		   
		     String player = in.nextLine();
	         if (player.equalsIgnoreCase("hit")){
		        int draw = deal();
		        this.playerValue += draw;
//		        check if there are Aces, change the value to 1 if over 21
		        if (this.playerValue > 21){
		        	if (draw == 11){
		        	   this.playerValue -= 10;
		        	} else if (this.firstP == 11){
		        		this.firstP -= 10;
		        		this.playerValue -= 10;
		        	} else if (this.secondP == 11){	
		        		this.secondP -= 10;
		        		this.playerValue -= 10;
		        	} else {
		        		System.out.println(" You draw " + draw + 
		        				" Now you have " + this.playerValue
		    					 + " Busted");
		        		break;
		        	}
		        }
		        System.out.println("You draw a " + 
		        draw + " Now You have " + this.playerValue);
	         } else {
	        	 hit = false;
	         }
	   }
	   
//	   If player bust, dealer doesn't need to play
	   if (this.playerValue <= 21) {
		   System.out.println("Dealer flips the card, now has " + this.dealerValue);
		   dealerPlay();
//		   if dealer bust, player wins, so no comparison is needed
		   if (this.dealerValue <=21){
		        if (this.dealerValue < this.playerValue){
			       System.out.println("You win");
		        } else if (this.dealerValue > this.playerValue){
			       System.out.println("Dealer win");
		        } else {
			       System.out.println("Push");
		        }
		   } 
	   }
	   
	   
   }
   

//   The way dealer plays
   public void dealerPlay(){
	   while (this.dealerValue < 17){
		   int newCard = deal();
		   this.dealerValue += newCard;
		   if (this.dealerValue > 21) {
			   if (newCard ==11){
				   this.dealerValue -= 10;
			   } else if (this.firstD == 11){
				   this.firstD -= 10;
				   this.dealerValue -= 10;
			   } else if (this.secondD == 11){
				   this.secondD -= 10; 
				   this.dealerValue -= 10;
			   } else {
				   System.out.println("Dealer bust, you win");
			   }
		   } System.out.println("Dealer draw " + newCard);
	   }
	   System.out.println("Dealer has " + this.dealerValue);
   }
   

	

}
