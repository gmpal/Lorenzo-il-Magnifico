package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public abstract class PerformActivity  extends ImmediateEffect{

	private int dieValue;

	//constructor
	public PerformActivity(String name, SetOfValues effectValues, int dieValue) {
		super(name, effectValues);
		this.dieValue = dieValue;	
	}
	
	@Override
	public abstract void giveImmediateEffect(Player player);
	
	//getter and setter
	public int getDieValue() {
		return dieValue;
	}

	public void setDieValue(int dieValue) {
		this.dieValue = dieValue;
	}
		
}
