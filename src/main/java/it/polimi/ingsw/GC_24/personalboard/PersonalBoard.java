package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.Player;
//import it.polimi.ingsw.GC_24.PlayerColour;

public class PersonalBoard {
	
	private PersonalBuildings personalBuildings;
	private PersonalVentures personalVentures;
	private PersonalTerritories personalTerritories;
	private PersonalCharacters personalCharacters;
	private Player player;
	private BonusTile mybonusTile;
	
	//constructor
	public PersonalBoard(Player player){

		this.player = player;
		this.personalBuildings = new PersonalBuildings();
		this.personalVentures = new PersonalVentures();
		this.personalTerritories = new PersonalTerritories();
		this.personalCharacters = new PersonalCharacters();


	/*	this.personalBuildings = new PersonalBuildings(player.getMyColour());
		this.personalVentures = new PersonalVentures(player.getMyColour());
		this.personalTerritories = new PersonalTerritories(player.getMyColour());
		this.personalCharacters = new PersonalCharacters(player.getMyColour());
		this.player=player;*/

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
