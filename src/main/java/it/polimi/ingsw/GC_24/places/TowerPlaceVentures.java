package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.values.Value;

public class TowerPlaceVentures extends TowerPlace {
	
	private Ventures correspondingCard;
	
	public TowerPlaceVentures(int costDice, Value value, Ventures correspondingCard) {
		super(costDice, value);
		this.correspondingCard=correspondingCard;
	}
	
	@Override
	public void takeWithoutPlacing(){
		this.correspondingCard.setCardOnPersonalBoard(this.getFamMemberOnPlace().getPlayer().getMyBoard());
	}
}
