import java.util.Scanner;

public class BlackjackChallenge {
	private Cards card;
	private Deal start;
	private int position;
	private int playerValue;
	private int firstP;
	private int secondP;
	private int firstD;
	private int secondD;
	private int dealerValue;
	private double totalMoney;
//	the money you bet 
	private double bet;
	private boolean doubleDown;
	
	public BlackjackChallenge(){
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
		this.totalMoney = 1000;
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
 	   System.out.println("You have " + this.totalMoney + " left, "
 	   		+ "How much would you like to bet: ");
 	   Scanner in = new Scanner(System.in);
 	   
 	   this.bet = in.nextDouble();
 	   
// 	   You cannot place more bet than what you have
 	   if (bet>this.totalMoney){
 		   bet = this.totalMoney;
 	   }
 	   this.totalMoney -= bet;
	   boolean result = false;
	   if (this.playerValue == 21){
		   if (this.dealerValue == 21){
		      System.out.println("Two Blackjack wow! Push");
		      result = true;
//		      You get your bet back
		      this.totalMoney += bet;
	
		   } else {
			  System.out.println("Blackjack! You win");
			  result = true;
			  double winAmount = bet*1.5;
		      this.totalMoney += (winAmount + bet);
		   }
	   } else if (this.dealerValue == 21){
		   System.out.println("Dealer has blackjack, you lose");
		   result = true;
	   }
		   
	   return result;
   }
   
//    main game
   public void gamePlay(){


	   Scanner in = new Scanner(System.in);
	   
	   if (this.firstP == this.secondP || this.secondP - this.firstP ==10 ){
		   System.out.println("Dealer showing " + this.firstD + ", "
			   		+ "You have " + this.firstP + " and " +
		    		 this.secondP +", split?");
		   String isSplit = in.nextLine();
		   if(isSplit.equalsIgnoreCase("Yes")){
			   split();
		   }
	   }
	   
	   
	   System.out.println("Dealer showing " + this.firstD + ", "
			   		+ "You have " + this.firstP + " and " +
		    		 this.secondP);
	   boolean hit = true;
	   while (this.playerValue < 21 && hit){
		     System.out.println("You are at " + this.playerValue 
		    		 + " hit or stand or double down:");
		     String player = in.nextLine();
	         if (player.equalsIgnoreCase("hit")){
		        int firstDraw = deal();
		        this.playerValue += firstDraw;
		        if (this.playerValue > 21){
		        	if (firstDraw == 11){
		        	   this.playerValue -= 10;
		        	} else if (this.firstP == 11){
		        		this.firstP -= 10;
		        		this.playerValue -= 10;
		        	} else if (this.secondP == 11){
		        		this.secondP -= 10;
		        		this.playerValue -= 10;
		        	} else {
		        		System.out.println(" You draw " + firstDraw + 
		        				" Now you have " + this.playerValue
		    					 + " Busted");
		        		break;
		        	}
		        }
		        System.out.println("You draw a " + 
		        firstDraw + " Now You have " + this.playerValue);
	         } else if (player.equalsIgnoreCase("double")){
	                   int draw = deal();
	                   this.playerValue += draw;
	                   this.totalMoney -= this.bet;
	                   hit = false;
	                   System.out.println("You draw a " + draw);
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
	   		        		    System.out.println("Now you have " + this.playerValue
	   		    					 + " Busted");
	   		        		    break;
	   		        	     }
	   		          } else {
	   		        	  System.out.println("You have " + this.playerValue);
	   		          }
	            } else {
    	            hit = false;
                  }
	   }
	   
	   if (this.playerValue <= 21) {
		   System.out.println("Dealer flips the card " + this.secondD +
				   ", now has " + this.dealerValue);
		   dealerPlay();
		   if (this.dealerValue <=21){
		        if (this.dealerValue < this.playerValue){
			       if (this.doubleDown){
			           this.totalMoney += (this.bet*4);
			           System.out.println("You win the double, Now you have " + this.totalMoney);
			       } else {
			    	   this.totalMoney += (this.bet*2);
			           System.out.println("You win Now you have " + this.totalMoney);
			       }
		        } else if (this.dealerValue > this.playerValue){
			       System.out.println("Dealer win");
		        } else {
			       System.out.println("Push");
			       this.totalMoney += this.bet;
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
				   System.out.println("Dealer draw " + newCard+ " "
				   		+ "Dealer bust, you win");
				   this.totalMoney += (this.bet*2);
				   break;
			   }
		   } System.out.println("Dealer draw " + newCard);
	   }
	   System.out.println("Dealer has " + this.dealerValue);
   }
   
   
   public void split(){
	   if (this.firstP == 1){
		   this.firstP += 10;
	   }
	   this.playerValue = this.firstP;
	   this.secondP = 0;
	   
	   int secondValue = this.firstP;
	   System.out.println("Dealer showing " + this.firstD + ", "
		   		+ "You have " + this.firstP);
       boolean hit = true;
       

	    
   }
   


}
