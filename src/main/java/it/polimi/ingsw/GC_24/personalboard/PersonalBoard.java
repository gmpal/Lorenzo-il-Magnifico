package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.PlayerColour;

public class PersonalBoard {
	
	private PersonalBuildings personalBuildings;
	private PersonalVentures personalVentures;
	private PersonalTerritories personalTerritories;
	private PersonalCharacters personalCharacters;
	private Player player;
	private PlayerColour playerColour;
	private BonusTile mybonusTile;
	
	//constructor
	public PersonalBoard(){
		this.personalBuildings = new PersonalBuildings(player.getMyColour());
		this.personalVentures = new PersonalVentures(playerColour);
		this.personalTerritories = new PersonalTerritories(player.getMyColour());
		this.personalCharacters = new PersonalCharacters(player.getMyColour());
	}
	
	//getters and setters
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BonusTile getBonusTile() {
		return mybonusTile;
	}

	public void setBonusTile(BonusTile bonusTile) {
		this.mybonusTile = mybonusTile;
	}

}
