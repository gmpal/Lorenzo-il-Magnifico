package it.polimi.ingsw.GC_24.model.effects.immediate;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

/** this effect will give its resource to the player every time it's called */
public class ValueEffect extends ImmediateEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1376411059993068191L;

	private SetOfValues setOfValue;

	// constructor
	public ValueEffect(String name) {
		super(name);
		this.setOfValue = new SetOfValues();
	}

	/**
	 * gives the resources to the player
	 * 
	 * @param player
	 */
	@Override
	public void giveImmediateEffect(Player player) {
		;
		if (this.setOfValue != null) {
			this.setOfValue.addTwoSetsOfValues(player.getMyValues());
		}
		;
	}

	@Override
	public String toString() {
		return "Values: " + setOfValue;
	}

	// getters and setters
	public SetOfValues getEffectValues() {
		return setOfValue;
	}

	public void setEffectValues(SetOfValues effectValues) {
		this.setOfValue = effectValues;
	}
}