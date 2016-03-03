
import java.util.Random;

public class Cards {
	private int[] card;
	private Random rand;
	private int position;
	
//	Constructor
	public Cards(){
		this.card =  new int[52];
//		Create an array with four of such sequences(2 to 11 with 4 of 10)
		for (int i = 0; i<=3; i++){
			for (int j = 1; j<=13; j++){
				int n = j;
				int numOfRepeat = i*13;
				int pos = numOfRepeat + j - 1;
				if (n > 10){
					n = 10;
				}
				if (n == 1){
					n = 11;
				}
				card[pos]=n;
			}
		}
//		shuffle the array
		this.rand = new Random();
		for (int a = 0; a < card.length; a++){
		     int pos = rand.nextInt(card.length);
		     int temp = card[a];
		     card[a] = card[pos];
		     card[pos] = temp;
		}
		
		this.position = 0;
		
	}
	
//	draw a card from the array, increment the position
	public int drawCard(){
		int result = this.card[this.position];
		this.position += 1;
		
		return result;
		
	}
	
//	return the value of the card in position i
	public int getCard(int i){
		return card[i];
	}
	
	public void makeCombination(int[] values, int n) {
	       Random generator = new Random();
	       int[] numbers = new int[values.length]; 
	       for (int i = 0; i < numbers.length; i++) {
	             numbers[i] = generator.nextInt(n); 
	       }
	       values = numbers; 
	}
	
	

}
