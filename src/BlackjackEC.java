/**
 * This class is an optimized strategy to help player win as much as possible
 * @author kevin
 *
 */

public class BlackjackEC {
	private Cards card;
	private Deal start;
	private int position;
	private int playerValue;
	private int firstP;
	private int secondP;
	private int firstD;
	private int secondD;
	private int dealerValue;
	private boolean isWin;
	private boolean isPush;
	
	public BlackjackEC(){
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
		
//		init win to false
		this.isWin = false;
		this.isPush = false;

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
	   boolean result = false;
	   if (playerValue == 21){
		   if (dealerValue == 21){
		      result = true;
		      this.isPush = true;
		   } else {
			  result = true;
			  this.isWin = true;
		   }
	   } else if (dealerValue == 21){
		   result = true;
	   }
		   
	   return result;
   }
   
//    main game
   public void gamePlay(){
	   
	   boolean hit = true;
	   while (this.playerValue < 21 && hit){
//		   Player will definitely hit if its value less than 12
		   if (this.playerValue < 12){
			   int firstCard = deal();
			   this.playerValue += firstCard;
//			   if player has 11, and gets a Ace, it should have 12 instead of 22
			   if (this.playerValue > 21){
				   this.playerValue -= 10;
			   }
//			   Player hits with 12 when dealer showing 2,3,ACE
		   } else if (this.playerValue == 12 && this.firstD < 4){
			   int secondCard = deal();
			   this.playerValue += secondCard;

			   if (this.playerValue > 21){
			       	if (secondCard == 11){
			       	   this.playerValue -= 10;
			       	} else if (this.firstP == 11){
			       		this.firstP -= 10;
			       		this.playerValue -= 10;
			       	} else if (this.secondP == 11){
			       		this.secondP -= 10;
			       		this.playerValue -= 10;
			       	}
			    }
//			   dealer showing 7,8,9,10,ace, player will hit till 17
	         } else if (this.playerValue < 17 && this.firstD >= 7){
	                 int thirdCard = deal();
	                 this.playerValue += thirdCard;
	                 if (this.playerValue > 21){
	 			       	if (thirdCard == 11){
	 			       	   this.playerValue -= 10;
	 			       	} else if (this.firstP == 11){
	 			       		this.firstP -= 10;
	 			       		this.playerValue -= 10;
	 			       	} else if (this.secondP == 11){
	 			       		this.secondP -= 10;
	 			       		this.playerValue -= 10;
	 			       	}
	 			    }
	           } else {
	        	 hit = false;
	         }
	      
	   }
	   
//	   Check if player bust
	   if (this.playerValue <= 21) {
		   dealerPlay();
//		   check if dealer bust, if not, check who wins.
		   if (this.dealerValue <=21){
		        if (this.dealerValue < this.playerValue){
			       this.isWin = true;
		        } else if (this.dealerValue == this.playerValue){
		        	this.isPush = true;
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
				   this.isWin = true;
			   }
		   }
	   }
   }
   
//   check if player win by accessing the win boolean
   public boolean checkIfWin(){
	   return this.isWin;
   }

//   check if it is a tie
   public boolean checkIfPush(){
	   return this.isPush;
   }
	

}
