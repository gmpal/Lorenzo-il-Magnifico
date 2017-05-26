package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class ProductionPlace extends ActivityPlace{
	
	//constructor
	public ProductionPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value, additionalCostDice);
	}
	
	//gives the production value of the bonus tile and the effects of the activated cards to the player 
	public void doActivity(){
		Player player = getFamMemberOnPlace().getPlayer();
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		ArrayList<Development> harvestCards = player.getMyBoard().getPersonalBuildings().getCards();
		for (Development  card:harvestCards){
			Buildings buildings = (Buildings) card;
			if (buildings.getCostDie() <= (getCostDice() + getAdditionalCostDice())){
				buildings.getImmediateEffect().giveEffectValues(player);
				buildings.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ProductionPlace free: " + isAvailable());
		if (!isAvailable()){
			builder.append("extra die's cost: " + getAdditionalCostDice());
		}
		return builder.toString();
	}

}
