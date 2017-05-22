package it.polimi.ingsw.GC_24.personalboard;

public class PersonalCharacters extends PersonalCards{
	
	//constructor
	public PersonalCharacters() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalCharacters();
	}
}
