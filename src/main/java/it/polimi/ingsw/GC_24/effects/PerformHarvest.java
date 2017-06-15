package it.polimi.ingsw.GC_24.effects;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.model.Player;

public class PerformHarvest extends PerformActivity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7757590984515720227L;

	//constructor
	public PerformHarvest(String name, int dieValue) {
		super(name, dieValue);
	}

	@Override
	public void giveImmediateEffect(Player player){
	player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	List<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
	for (Development  card:harvestCards){
		Territories territory = (Territories) card;
		if (territory.getCostDie() <= getDieValue()){
			territory.getImmediateEffect1().giveImmediateEffect(player);
			territory.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	}
}
