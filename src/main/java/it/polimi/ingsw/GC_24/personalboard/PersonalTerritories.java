package it.polimi.ingsw.GC_24.personalboard;

public class PersonalTerritories extends PersonalCards {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9186662745127500426L;

	//constructor
	public PersonalTerritories() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalTerritories();
	}

	@Override
	public String toString() {
		return "Territories " + getCards();
	}
}
