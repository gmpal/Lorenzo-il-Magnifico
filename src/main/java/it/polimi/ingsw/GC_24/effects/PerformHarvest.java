package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.Die;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class PerformHarvest extends ImmediateEffect{
	
	private Die dieValue;

	public PerformHarvest(String name, SetOfValues effectValues, Die dieValue) {
		super(name, effectValues);
		this.dieValue = dieValue;
		// TODO Auto-generated constructor stub
	}
	
	public void performHarvest(){
		
	}

}
