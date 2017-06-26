package it.polimi.ingsw.GC_24.places;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;

public class ProductionPlace extends ActivityPlace{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1488467430023982193L;

	//constructor
	public ProductionPlace(int costDice, int additionalCostDice) {
		super(costDice, additionalCostDice);
	}
	

	//gives the production value of the bonus tile and the effects of the activated cards to the player 
/*	@Override
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
*/
	@Override
	public ValueEffect getValue() {
		return null;
	}
}
