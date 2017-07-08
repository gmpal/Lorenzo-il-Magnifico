package it.polimi.ingsw.GC_24.model.effects.permanent;

import it.polimi.ingsw.GC_24.model.values.SetOfValues;

/**
 * this effect subtracts the resources as parameter from the player's resources.
 * exception --> it is also used for a leader card that makes a discount on the
 * cost of the card, and in this case the resources are subtracted from the cost
 * of the card
 */
public class SubSetOfValues extends PermanentEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9073957541456181644L;
	private SetOfValues subSet;

	// constructor
	public SubSetOfValues(String name, SetOfValues subSet) {
		super(name);
		this.subSet = subSet;
	}

	@Override
	public String toString() {
		return name + ": subtracts " + subSet;
	}

	// getters and setters
	public SetOfValues getSubSet() {
		return subSet;
	}

	public void setSubSet(SetOfValues subSet) {
		this.subSet = subSet;
	}
}
