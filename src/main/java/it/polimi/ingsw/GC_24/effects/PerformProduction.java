package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.Die;
import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class PerformProduction extends ImmediateEffect{

	private Die dieValue;

	//constructor
	public PerformProduction(String name, SetOfValues effectValues, Die dieValue) {
		super(name, effectValues);
		this.setDieValue(dieValue);
	}
	
	public void giveImmediateEffect(Player player){
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	}

	public Die getDieValue() {
		return dieValue;
	}

	public void setDieValue(Die dieValue) {
		this.dieValue = dieValue;
	}
}
