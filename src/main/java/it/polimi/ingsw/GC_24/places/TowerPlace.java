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
	}
	
	@Override
	public void giveEffects(Player player){
		player.setMyValues(this.getValue().addValueToSet(player.getMyValues()));
		correspondingCard.setCardOnPersonalBoard(player.getMyBoard());
		correspondingCard.getValueEffect().giveImmediateEffect(player);
		correspondingCard.getImmediateEffect().giveImmediateEffect(player);
		this.correspondingCard = null;
	}

	@Override
	public String toString() {
		return "TowerPlace Card=" + correspondingCard;
	}

}
