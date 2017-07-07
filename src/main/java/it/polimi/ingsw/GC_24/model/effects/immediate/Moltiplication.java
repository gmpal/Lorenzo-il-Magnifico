package it.polimi.ingsw.GC_24.model.effects.immediate;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.values.*;

public abstract class Moltiplication extends ImmediateEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7186806258388556423L;

	private Value value;

	// constructor
	public Moltiplication(String name, Value value) {
		super(name);
		this.value = value;
	}

	/**
	 * the player will receive the quantity of value for every quantity of value2 or
	 * specified cards in his possession
	 * 
	 * @param player
	 */
	public abstract void moltiplicationEffect(Player player);

	// getter and setter
	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
}