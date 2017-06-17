package it.polimi.ingsw.GC_24.personalboard;

public class PersonalBoard implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3714661515499864596L;
	
	private PersonalBuildings personalBuildings;
	private PersonalVentures personalVentures;
	private PersonalTerritories personalTerritories;
	private PersonalCharacters personalCharacters;
	private BonusTile mybonusTile;
	
	//constructor
	public PersonalBoard(){
		this.personalBuildings = new PersonalBuildings();
		this.personalVentures = new PersonalVentures();
		this.personalTerritories = new PersonalTerritories();
		this.personalCharacters = new PersonalCharacters();
		this.mybonusTile = new BonusTile(null, null);
	}
	
	
	
	@Override
	public String toString() {
		return "PersonalBoard \n"
				+ "CARDS: personalBuildings=" + personalBuildings + "\n personalVentures=" + personalVentures
				+ "\n personalTerritories=" + personalTerritories + "\n personalCharacters=" + personalCharacters
				+ "\n BONUS TILE: " + mybonusTile+"\n";
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

	public BonusTile getBonusTile() {
		return mybonusTile;
	}

	public void setBonusTile(BonusTile mybonusTile) {
		this.mybonusTile = mybonusTile;
	}
}