 package it.polimi.ingsw.GC_24.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.Die;
import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class PerformHarvest extends ImmediateEffect{
	
	private int dieValue;
	
	//constructor
	public PerformHarvest(String name, SetOfValues effectValues, int dieValue) {
		super(name, effectValues);
		this.dieValue = dieValue;
	}
	
	//e se il player vuole aumentare l'azione??
	public void giveImmediateEffect(Player player){
	player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		ArrayList<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
		for (Development  card:harvestCards){
			Territories territory = (Territories) card;
			if (territory.getCostDie() <= dieValue){
				territory.getImmediateEffect().giveEffectValues(player);
				territory.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	}
	
	//getter and setter
	public int getDieValue() {
		return dieValue;
	}

	public void setDieValue(int dieValue) {
		this.dieValue = dieValue;
	}

}
