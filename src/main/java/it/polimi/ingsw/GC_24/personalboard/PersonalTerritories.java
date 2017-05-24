package it.polimi.ingsw.GC_24.personalboard;

public class PersonalTerritories extends PersonalCards{
	
	//constructor
	public PersonalTerritories() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalTerritories();
	}

	
}
