package it.polimi.ingsw.GC_24.effects;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.model.Player;

public class PerformHarvest extends ImmediateEffect {

	private int dieValue;

	// constructor
	public PerformHarvest(String name, int dieValue) {
		super(name);
		this.dieValue = dieValue;
	}

	// e se il player vuole aumentare l'azione??
	@Override
	public void giveImmediateEffect(Player player) {
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		ArrayList<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
		for (Development card : harvestCards) {
			Territories territory = (Territories) card;
			if (territory.getCostDie() <= dieValue) {
				territory.getValueEffect().giveImmediateEffect(player);
				territory.getImmediateEffect().giveImmediateEffect(player);
			}
		}
	}

	// getter and setter
	public int getDieValue() {
		return dieValue;
	}

	public void setDieValue(int dieValue) {
		this.dieValue = dieValue;
	}

}
