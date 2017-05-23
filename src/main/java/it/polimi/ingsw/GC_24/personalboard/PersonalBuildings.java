package it.polimi.ingsw.GC_24.personalboard;

public class PersonalBuildings extends PersonalCards{

	//constructor
	public PersonalBuildings() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalBuildings();
	}
}


