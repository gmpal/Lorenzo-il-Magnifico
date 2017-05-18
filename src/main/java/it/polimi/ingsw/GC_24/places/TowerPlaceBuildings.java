package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.values.Value;

public class TowerPlaceBuildings extends TowerPlace {
	
	private Buildings correspondingCard;
	
	public TowerPlaceBuildings(int costDice, Value value, Buildings correspondingCard) {
		super(costDice, value);
		this.correspondingCard=correspondingCard;
	}
	
	@Override
	public void takeWithoutPlacing(){
		this.correspondingCard.setCardOnPersonalBoard(this.getFamMemberOnPlace().getPlayer().getPersonalBoard());
	}
}
