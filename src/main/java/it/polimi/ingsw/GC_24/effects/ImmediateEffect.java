package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public abstract class ImmediateEffect extends Effect{
	
	private SetOfValues effectValues;
	
	public ImmediateEffect(String name, SetOfValues effectValues) {
		super(name);
		this.effectValues = effectValues;
		// TODO Auto-generated constructor stub
	}
	
						
}
