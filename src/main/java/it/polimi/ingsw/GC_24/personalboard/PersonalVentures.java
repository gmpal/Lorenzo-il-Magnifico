package it.polimi.ingsw.GC_24.personalboard;

public class PersonalVentures extends PersonalCards{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 473515650189053083L;

	//constructor
	public PersonalVentures() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalVentures();
	}

	@Override
	public String toString() {
		return "Ventures " + getCards();
	}
}
