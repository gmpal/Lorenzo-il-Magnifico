package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.model.Player;

import it.polimi.ingsw.GC_24.values.Value;

public class HarvestPlace extends Place {

	private int additionalCostDice;

	// constructor
	public HarvestPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value);
		this.additionalCostDice = additionalCostDice;
	}

	@Override // TODO serve???
	public void giveEffects() {
		doHarvest();
	}

	// gives the harvest value of the bonus tile and the effects of the
	// activated cards to the player
	public void doHarvest() {
		Player player = getFamMemberOnPlace().getPlayer();
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		ArrayList<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
		for (Development card : harvestCards) {
			Territories territory = (Territories) card;
			if (territory.getCostDie() <= (getCostDice() + additionalCostDice)) {
				territory.getValueEffect().giveImmediateEffect(player);
				territory.getImmediateEffect().giveImmediateEffect(player);
			}
		}
	} // TODO da provare

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HarvestPlace free: " + isAvailable());
		if (!isAvailable()) {
			builder.append("extra die cost: " + additionalCostDice);
		}
		return builder.toString();
	}

	// getter and setter
	public int getAdditionalCostDice() {
		return additionalCostDice;
	}

	public void setAdditionalCostDice(int additionalCostDice) {
		this.additionalCostDice = additionalCostDice;
	}

}
