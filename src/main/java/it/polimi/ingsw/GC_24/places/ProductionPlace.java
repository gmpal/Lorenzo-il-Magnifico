package it.polimi.ingsw.GC_24.places;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class ProductionPlace extends ActivityPlace{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3520071878963185433L;

	//constructor
	public ProductionPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value, additionalCostDice);
	}
	
	//gives the production value of the bonus tile and the effects of the activated cards to the player 
	@Override
	public void doActivity(Player player){
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		List<Development> productionCards = player.getMyBoard().getPersonalBuildings().getCards();
		for (Development card : productionCards) {
			Buildings buildings = (Buildings) card;
			if (buildings.getDieValueProduction() <= (getFamMemberOnPlace().getMemberValue() + getAdditionalCostDice())) {
				buildings.getImmediateEffect1().giveImmediateEffect(player);
				buildings.getImmediateEffect().giveImmediateEffect(player);
			}
		}

	}
}
