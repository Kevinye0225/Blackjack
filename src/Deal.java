/**
 * This class is created to deal two cards to both player and dealer
 * @author kevin
 *
 */
public class Deal {
	private Cards card;
	private int firstP;
	private int secondP;
	private int firstD;
	private int secondD;
	private int playerValue;
	private int dealerValue;
	
//  constructor 
	public Deal(){
		this.card = new Cards();
		this.firstP = card.drawCard();
		this.firstD = card.drawCard();
		this.secondP = card.drawCard();
		this.secondD = card.drawCard();
		
//		check if there are two Aces
		if (this.firstP + this.secondP == 22){
			this.firstP -= 10;
		}
		this.playerValue = this.firstP + this.secondP;
		
//		check if there are two Aces
		if (this.firstD + this.secondD == 22){
			this.firstD -= 10;
		}
		this.dealerValue = this.firstD + this.secondD;
	}
	
//	get player first card value
	public int firstPlayer(){
		return this.firstP;
	}
	
//	get player second card value
	public int secondPlayer(){
		return this.secondP;
	}
	
//	get dealer first card value
	public int firstDealer(){
		return this.firstD;
	}
	
//	get dealer second card value
	public int secondDealer(){
		return this.secondD;
	}
	
//	get player card value
	public int player(){
		return this.playerValue;
	}
	
//	get dealer card value
	public int dealer(){
		return this.dealerValue;
	}
	
//	get the order of this card
	public Cards getCard(){
		return this.card;
	}

}
