package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class SubVicrotyPointsFromSetOfValue extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892422029061282092L;
	private SetOfValues setForSub;
	private Value valueToSub;

	// constructor
	public SubVicrotyPointsFromSetOfValue(String name, SetOfValues setForSub, Value valueToSub) {
		super(name);
		this.setForSub = setForSub;
		this.valueToSub = valueToSub;
	}

	// getters and setters
	public SetOfValues getSetForSub() {
		return setForSub;
	}

	public void setSetForSub(SetOfValues setForSub) {
		this.setForSub = setForSub;
	}

	public Value getValueToSub() {
		return valueToSub;
	}

	public void setValueToSub(Value valueToSub) {
		this.valueToSub = valueToSub;
	}
}
