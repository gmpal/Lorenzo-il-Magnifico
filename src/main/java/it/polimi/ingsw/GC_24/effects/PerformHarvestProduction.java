package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.Die;
import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class PerformHarvestProduction extends ImmediateEffect{
	
	private Die dieValue;

	//constructor
	public PerformHarvestProduction(String name, SetOfValues effectValues, Die dieValue) {
		super(name, effectValues);
		this.setDieValue(dieValue);
	}
	
	public void performHarvest(Player player){
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	}
	
	public void performProduction(Player player){
		player.getMyBoard().getBonusTile().giveProductiontValues(player.getMyValues());
	}
	
	//getter and setter
	public Die getDieValue() {
		return dieValue;
	}

	public void setDieValue(Die dieValue) {
		this.dieValue = dieValue;
	}

}
