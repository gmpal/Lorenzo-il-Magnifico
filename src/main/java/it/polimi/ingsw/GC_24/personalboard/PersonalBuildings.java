package it.polimi.ingsw.GC_24.personalboard;


public class PersonalBuildings extends PersonalCards{

	public PersonalBuildings() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalBuildings();
	}
}


