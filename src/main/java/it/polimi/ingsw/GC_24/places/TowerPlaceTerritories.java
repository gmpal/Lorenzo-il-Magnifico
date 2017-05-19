package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.values.Value;

public class TowerPlaceTerritories extends TowerPlace {
	
	private Territories correspondingCard;
	
	public TowerPlaceTerritories(int costDice, Value value, Territories correspondingCard) {
		super(costDice, value);
		this.correspondingCard=correspondingCard;
	}
	
	@Override
	public void takeWithoutPlacing(){
		this.correspondingCard.setCardOnPersonalBoard(this.getFamMemberOnPlace().getPlayer().getMyBoard());
	}
}
