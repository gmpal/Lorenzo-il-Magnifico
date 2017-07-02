package it.polimi.ingsw.GC_24.model.cards;

import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class Requirements implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9126161293112185947L;
	private SetOfValues requirementSetOfVaue;
	private int requirementTerritories;
	private int requirementCharacters;
	private int requirementBuildings;
	private int requirementVentures;

	// constructor
	public Requirements(SetOfValues requirementSetOfVaue, int requirmentTerritories, int requirmentCharacters,
			int requirmentBuildings, int requirmentVentures) {
		super();
		this.requirementSetOfVaue = requirementSetOfVaue;
		this.requirementTerritories = requirmentTerritories;
		this.requirementCharacters = requirmentCharacters;
		this.requirementBuildings = requirmentBuildings;
		this.requirementVentures = requirmentVentures;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (!requirementSetOfVaue.isEmpty()) {
			sb.append(" "+requirementSetOfVaue.toString());
		}
		if (requirementTerritories != 0) {
			sb.append(" -Territory cards required = " + requirementTerritories);
		}
		if (requirementCharacters != 0) {
			sb.append(" -Character cards required = " + requirementCharacters);
		}
		if (requirementBuildings != 0) {
			sb.append(" -Buildings cards required = " + requirementBuildings);
		}
		if (requirementVentures != 0) {
			sb.append(" -Ventures cards required = " + requirementVentures);
		}
		return sb.toString();
	}

	// getters and setters
	public SetOfValues getRequirementSetOfVaue() {
		return requirementSetOfVaue;
	}

	public void setRequirementSetOfVaue(SetOfValues requirementSetOfVaue) {
		this.requirementSetOfVaue = requirementSetOfVaue;
	}

	public int getRequirmentTerritories() {
		return requirementTerritories;
	}

	public void setRequirmentTerritories(int requirmentTerritories) {
		this.requirementTerritories = requirmentTerritories;
	}

	public int getRequirmentCharacters() {
		return requirementCharacters;
	}

	public void setRequirmentCharacters(int requirmentCharacters) {
		this.requirementCharacters = requirmentCharacters;
	}

	public int getRequirmentBuildings() {
		return requirementBuildings;
	}

	public void setRequirmentBuildings(int requirmentBuildings) {
		this.requirementBuildings = requirmentBuildings;
	}

	public int getRequirmentVentures() {
		return requirementVentures;
	}

	public void setRequirmentVentures(int requirmentVentures) {
		this.requirementVentures = requirmentVentures;
	}

}
