package it.polimi.ingsw.GC_24.personalboard;

public class PersonalVentures extends PersonalCards{

	//constructor
	public PersonalVentures() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalVentures();
	}

}
