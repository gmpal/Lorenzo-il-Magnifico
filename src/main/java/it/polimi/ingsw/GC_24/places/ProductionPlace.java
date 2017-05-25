package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class ProductionPlace extends Place{
	
	private int additionalCostDice;
	
	//constructor
	public ProductionPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value);			
		this.additionalCostDice = additionalCostDice;
	}
	
	@Override
	public void giveEffects(){
		doProduction();
	}
	
	//gives the production value of the bonus tile and the effects of the activated cards to the player 
	public void doProduction(){
		Player player = getFamMemberOnPlace().getPlayer();
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		ArrayList<Development> harvestCards = player.getMyBoard().getPersonalBuildings().getCards();
		for (Development  card:harvestCards){
			Buildings buildings = (Buildings) card;
			if (buildings.getCostDie() <= (getCostDice()+additionalCostDice)){
				buildings.getImmediateEffect().giveEffectValues(player);
				buildings.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	} //TODO da provare
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ProductionPlace free: " + isAvailable());
		if (!isAvailable()){
			builder.append("extra die cost: " + additionalCostDice);
		}
		return builder.toString();
	}

	//getter and setter
	public int getAdditionalCostDice() {
		return additionalCostDice;
	}

	public void setAdditionalCostDice(int additionalCostDice) {
		this.additionalCostDice = additionalCostDice;
	}	
	
}
