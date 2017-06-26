package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

public abstract class Moltiplication extends ImmediateEffect{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7186806258388556423L;
	
	private Value value;
	private Player player;
	
	//constructor
	public Moltiplication(String name, Value value) {
		super(name);
		this.value = value;
		}
	
	//gives the effect to the player
	public abstract void moltiplicationEffect(Player player); //redefined for every subclass

	//getter and setter
	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
}
