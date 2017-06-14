package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Requirements {

	private SetOfValues requirementSetOfVaue;
	private int requirementTerritories;
	private int requirementCharacters;
	private int requirementBuildigns;
	private int requirementVentures;

	// constructor
	public Requirements(SetOfValues requirementSetOfVaue, int requirmentTerritories, int requirmentCharacters,
			int requirmentBuildigns, int requirmentVentures) {
		super();
		this.requirementSetOfVaue = requirementSetOfVaue;
		this.requirementTerritories = requirmentTerritories;
		this.requirementCharacters = requirmentCharacters;
		this.requirementBuildigns = requirmentBuildigns;
		this.requirementVentures = requirmentVentures;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Requirements: ");
		if (!requirementSetOfVaue.toString().equals("{}")) {
			sb.append(requirementSetOfVaue.toString());
		}
		if (requirementTerritories != 0) {
			sb.append("\nTerritory cards required: " + requirementTerritories);
		}
		if (requirementCharacters != 0) {
			sb.append("\nCharacter cards required: " + requirementCharacters);
		}
		if (requirementBuildigns != 0) {
			sb.append("\nBuildings cards required: " + requirementBuildigns);
		}
		if (requirementVentures != 0) {
			sb.append("\nVentures cards required: " + requirementVentures);
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

	public int getRequirmentBuildigns() {
		return requirementBuildigns;
	}

	public void setRequirmentBuildigns(int requirmentBuildigns) {
		this.requirementBuildigns = requirmentBuildigns;
	}

	public int getRequirmentVentures() {
		return requirementVentures;
	}

	public void setRequirmentVentures(int requirmentVentures) {
		this.requirementVentures = requirmentVentures;
	}

}
