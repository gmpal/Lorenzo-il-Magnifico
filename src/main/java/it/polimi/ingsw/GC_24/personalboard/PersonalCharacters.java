package it.polimi.ingsw.GC_24.personalboard;

public class PersonalCharacters extends PersonalCards {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1248796869597555144L;

	//constructor
	public PersonalCharacters() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalCharacters();
	}
	
	@Override
	public String toString() {
		return "Characters " + getCards();
	}
}
