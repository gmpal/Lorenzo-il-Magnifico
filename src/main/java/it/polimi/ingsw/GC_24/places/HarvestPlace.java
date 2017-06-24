package it.polimi.ingsw.GC_24.places;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class HarvestPlace extends ActivityPlace{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1219130341018758993L;

	// constructor
	public HarvestPlace(int costDice, int additionalCostDice) {
		super(costDice, additionalCostDice);
	}

	@Override
	public void doActivity(Player player){
	player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	List<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
	for (Development  card:harvestCards){
		Territories territory = (Territories) card;
		if (territory.getDieValueHarvest() <= (getFamMemberOnPlace().getMemberValue() + getAdditionalCostDice())){
			territory.getImmediateEffect1().giveImmediateEffect(player);
			territory.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	}

	@Override
	public ValueEffect getValue() {
		return null;
	}
}
