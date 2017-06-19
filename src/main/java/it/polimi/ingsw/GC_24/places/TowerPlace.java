package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class TowerPlace extends Place {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2679672850968909758L;
	
	private Development correspondingCard;
	
	//constructor
	public TowerPlace(int costDice, Value value){
		super(costDice, value);
		this.correspondingCard=null;
	}
	
	//method to take a card without placing a family member
	public void takeWithoutPlacing(Player player){
		this.correspondingCard.setCardOnPersonalBoard(player.getMyBoard());
		this.correspondingCard.getImmediateEffect().giveImmediateEffect(player);
	}
	
	@Override
	public void giveEffects(Player player){
		correspondingCard.setCardOnPersonalBoard(player.getMyBoard());
		correspondingCard.getImmediateEffect();
		correspondingCard.getImmediateEffect1();
		this.correspondingCard = null;
	}

	@Override
	public String toString() {
		return "Card = " + correspondingCard;
	}

	public Development getCorrespondingCard() {
		return correspondingCard;
	}

	public void setCorrespondingCard(Development correspondingCard) {
		this.correspondingCard = correspondingCard;
	}
	
	

}
