 package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.Die;
import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class PerformHarvest_Production extends ImmediateEffect{
	
	private Die dieValue;

	public PerformHarvest_Production(String name, SetOfValues effectValues, Die dieValue) {
		super(name, effectValues);
		this.dieValue = dieValue;
		// TODO Auto-generated constructor stub
	}
	
	public void performHarvest(Player player){
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	}
	
	public void performProduction(Player player){
		player.getMyBoard().getBonusTile().giveProductiontValues(player.getMyValues());
	}

}
