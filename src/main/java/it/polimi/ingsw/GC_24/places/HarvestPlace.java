package it.polimi.ingsw.GC_24.places;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class HarvestPlace extends ActivityPlace{

	// constructor
	public HarvestPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value, additionalCostDice);
	}

	@Override
	public void doActivity(){
	Player player = getFamMemberOnPlace().getPlayer();
	player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	List<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
	for (Development  card:harvestCards){
		Territories territory = (Territories) card;
		if (territory.getCostDie() <= (getCostDice() + getAdditionalCostDice())){
				territory.getValueEffect().giveImmediateEffect(player);
			territory.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "HarvestPlace free: " + isAvailable());
		if (!isAvailable()){
			builder.append("extra die's cost: " + getAdditionalCostDice());
		}
		return builder.toString();
  }
}
