 package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.dice.Die;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class PerformHarvest extends ImmediateEffect{
	
	private Die dieValue;

	//constructor
	public PerformHarvest(String name, SetOfValues effectValues, Die dieValue) {
		super(name, effectValues);
		this.setDieValue(dieValue);
	}
	
	public void giveImmediateEffect(Player player){
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	}
	
	//getter and setter
	public Die getDieValue() {
		return dieValue;
	}

	public void setDieValue(Die dieValue) {
		this.dieValue = dieValue;
	}

}
