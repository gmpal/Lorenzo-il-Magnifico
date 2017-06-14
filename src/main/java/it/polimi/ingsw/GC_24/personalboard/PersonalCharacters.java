package it.polimi.ingsw.GC_24.personalboard;

public class PersonalCharacters extends PersonalCards {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8730181705703973672L;

	//constructor
	public PersonalCharacters() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalCharacters();
	}
}
