package it.polimi.ingsw.GC_24.personalboard;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.cards.Excommunication;
import it.polimi.ingsw.GC_24.values.VictoryPoint;

public class PersonalBoard implements java.io.Serializable {

	
	/**
	 * 
	 */

	private static final long serialVersionUID = -1010492492112081930L;

	private PersonalBuildings personalBuildings;
	private PersonalVentures personalVentures;
	private PersonalTerritories personalTerritories;
	private PersonalCharacters personalCharacters;
	private BonusTile mybonusTile;
	private List<Excommunication> personalExcommunication=new ArrayList<>();

	// constructor
	public PersonalBoard(int playerNumber) {
		this.personalBuildings = new PersonalBuildings();
		this.personalVentures = new PersonalVentures();
		this.personalTerritories = new PersonalTerritories();
		this.personalCharacters = new PersonalCharacters();
		this.mybonusTile = new BonusTile(true, playerNumber);
		this.personalExcommunication=new ArrayList<>();
	}

	@Override
	public String toString() {
		return "PERSONAL BOARD\n" + "CARDS\nPersonalBuildings = " + personalBuildings + "\n\nPersonalVentures = "
				+ personalVentures + "\n\nPersonalTerritories = " + personalTerritories + "\n\nPersonalCharacters = "
				+ personalCharacters + "\n\nBONUS TILE\n" + mybonusTile + "\n";
	}

	public VictoryPoint convertToVictoryPoints() {
		return new VictoryPoint(this.personalCharacters.convertCardToVictoryPoints().getQuantity()
				+ this.personalTerritories.convertCardToVictoryPoints().getQuantity()
				+ this.personalVentures.convertCardToVictoryPoints().getQuantity());
	}

	// getters and setters
	public PersonalBuildings getPersonalBuildings() {
		return personalBuildings;
	}

	public void setPersonalBuildings(PersonalBuildings personalBuildings) {
		this.personalBuildings = personalBuildings;
	}

	public PersonalVentures getPersonalVentures() {
		return personalVentures;
	}

	public void setPersonalVentures(PersonalVentures personalVentures) {
		this.personalVentures = personalVentures;
	}

	public PersonalTerritories getPersonalTerritories() {
		return personalTerritories;
	}

	public void setPersonalTerritories(PersonalTerritories personalTerritories) {
		this.personalTerritories = personalTerritories;
	}

	public PersonalCharacters getPersonalCharacters() {
		return personalCharacters;
	}

	public void setPersonalCharacters(PersonalCharacters personalCharacters) {
		this.personalCharacters = personalCharacters;
	}

	public BonusTile getBonusTile() {
		return mybonusTile;
	}

	public void setBonusTile(BonusTile mybonusTile) {
		this.mybonusTile = mybonusTile;
	}

	public List<Excommunication> getPersonalExcommunication() {
		return personalExcommunication;
	}

	public void setPersonalExcommunication(List<Excommunication> personalExcommunication) {
		this.personalExcommunication = personalExcommunication;
	}
}