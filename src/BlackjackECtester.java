
public class BlackjackECtester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfWin = 0;
		int numOfPush = 0;
		int numOfLose = 0;
		for (int i = 0; i<1000; i++){
			BlackjackEC newGame = new BlackjackEC();

			if (!newGame.checkBlackjack()){
			    newGame.gamePlay();
		    }
			
			if (newGame.checkIfWin()){
				numOfWin ++;
			} else if (newGame.checkIfPush()){
				numOfPush ++;
			} else {
				numOfLose ++;
			}
		}
		
		System.out.println("Win: "+ numOfWin +", Push: " + numOfPush +
				", Lose: " + numOfLose);

	}

}
