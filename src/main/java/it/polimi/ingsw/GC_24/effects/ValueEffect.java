package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

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

	// adds the immediate effect related set of values to the player's set
	// (given as parameter)
	@Override
	public void giveImmediateEffect(Player player) {
		if (this.setOfValue != null) {
			this.setOfValue.addTwoSetsOfValues(player.getMyValues());
		}
	}

	@Override
	public String toString() {
			return this.setOfValue.toString();
	}

	// getters and setters
	public SetOfValues getEffectValues() {
		return setOfValue;
	}

	public void setEffectValues(SetOfValues effectValues) {
		this.setOfValue = effectValues;
	}

	@Override
	public String getRequestedParametersName() {
		return null;
	}

}
