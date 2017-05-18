package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.values.Value;

public class TowerPlaceCharacters extends TowerPlace {
	
	private Characters correspondingCard;
	
	public TowerPlaceCharacters(int costDice, Value value, Characters correspondingCard) {
		super(costDice, value);
		this.correspondingCard=correspondingCard;
	}
	
	@Override
	public void takeWithoutPlacing(){
		this.correspondingCard.setCardOnPersonalBoard(this.getFamMemberOnPlace().getPlayer().getPersonalBoard());
	}
}
