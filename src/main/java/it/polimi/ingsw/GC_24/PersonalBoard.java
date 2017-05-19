package it.polimi.ingsw.GC_24;

public class PersonalBoard {
	
	private PersonalBuildings personalBuildings;
	private PersonalVentures personalVentures;
	private PersonalTerritories personalTerritories;
	private PersonalCharacters personalCharacters;
	private PlayerColour playerColour; 
	private BonusTile mybonusTile;
	
	//constructor
	public PersonalBoard(){
		this.personalBuildings = new PersonalBuildings(playerColour);
		this.personalVentures = new PersonalVentures(playerColour);
		this.personalTerritories = new PersonalTerritories(playerColour);
		this.personalCharacters = new PersonalCharacters(playerColour);
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

	public PlayerColour getPlayerColour() {
		return playerColour;
	}

	public void setPlayerColour(PlayerColour playerColour) {
		this.playerColour = playerColour;
	}

	public BonusTile getBonusTile() {
		return mybonusTile;
	}

	public void setBonusTile(BonusTile bonusTile) {
		this.mybonusTile = mybonusTile;
	}

}
