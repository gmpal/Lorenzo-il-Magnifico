package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.PlayerColour;

public class PersonalCharacters extends PersonalCards{
	
	public PersonalCharacters() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalCharacters();
	}
}
