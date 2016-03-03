
public class Blackjacktester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			Blackjack game = new Blackjack();
			
//			If there is blackjack, game ends. 
			if (!game.checkBlackjack()){
				game.gamePlay();
			}
			
			


		

	}

}


