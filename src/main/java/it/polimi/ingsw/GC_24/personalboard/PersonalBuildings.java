package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.values.VictoryPoint;

public class PersonalBuildings extends PersonalCards {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5691660182911164428L;

	//constructor
	public PersonalBuildings() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalBuildings();
	}

	@Override
	public String toString() {
		return "Buildings " + getCards();
	}

	@Override
	public VictoryPoint convertCardToVictoryPoints() {
		return new VictoryPoint(0);
	}
	
	
}


