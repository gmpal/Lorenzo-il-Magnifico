package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

public abstract class Moltiplication extends ImmediateEffect{
	
	protected Value value;
	protected Player player;
	
	//constructor
	public Moltiplication(String name, Value value) {
		super(name);
		this.value = value;
		}
	
	//gives the effect to the player
	public abstract void moltiplicationEffect(Player player); //redefined for every subclass
}
