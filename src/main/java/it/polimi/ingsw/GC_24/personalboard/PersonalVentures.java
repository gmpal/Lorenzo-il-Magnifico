package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.PlayerColour;

public class PersonalVentures extends PersonalCards{

	public PersonalVentures() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public PersonalCards FindCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalVentures();
	}

}
