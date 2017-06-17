package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;

public abstract class PerformActivity  extends ImmediateEffect{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2475182339045729171L;
	
	protected int dieValue;

	//constructor
	public PerformActivity(String name, int dieValue) {
		super(name);
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
