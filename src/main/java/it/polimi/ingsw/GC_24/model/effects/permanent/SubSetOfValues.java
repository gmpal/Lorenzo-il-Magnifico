package it.polimi.ingsw.GC_24.model.effects.permanent;

import it.polimi.ingsw.GC_24.model.values.SetOfValues;

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
		return name + ": subtracts "+subSet;
	}

	// getters and setters
	public SetOfValues getSubSet() {
		return subSet;
	}

	public void setSubSet(SetOfValues subSet) {
		this.subSet = subSet;
	}
}
