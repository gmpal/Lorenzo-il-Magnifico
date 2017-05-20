package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.PlayerColour;

public class PersonalTerritories extends PersonalCards{
	
	public PersonalTerritories() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalTerritories();
	}

}
