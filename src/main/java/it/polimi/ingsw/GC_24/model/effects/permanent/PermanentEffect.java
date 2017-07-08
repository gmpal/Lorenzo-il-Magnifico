package it.polimi.ingsw.GC_24.model.effects.permanent;

import it.polimi.ingsw.GC_24.model.effects.Effect;

/**
 * this is the abstract class for the effects that are activated by the player
 * or the Vatican only one time during the game, but form that point on its
 * effect will always be active
 */
public abstract class PermanentEffect extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3813458914699745615L;

	// constructor
	public PermanentEffect(String name) {
		super(name);
	}
}
