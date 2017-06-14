package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class TowerPlace extends Place {
	
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
	}

	@Override
	public String toString() {
		return "TowerPlace Card=" + correspondingCard;
	}
	
}
