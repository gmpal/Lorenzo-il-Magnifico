package it.polimi.ingsw.GC_24.places;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class HarvestPlace extends ActivityPlace{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5589820895011868990L;

	// constructor
	public HarvestPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value, additionalCostDice);
	}

	@Override
	public void doActivity(Player player){
	player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	List<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
	for (Development  card:harvestCards){
		Territories territory = (Territories) card;
		if (territory.getCostDie() <= (getFamMemberOnPlace().getMemberValue() + getAdditionalCostDice())){
			territory.getImmediateEffect1().giveImmediateEffect(player);
			territory.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	}
}
