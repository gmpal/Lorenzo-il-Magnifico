package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.values.VictoryPoint;

public class PersonalTerritories extends PersonalCards {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9186662745127500426L;

	//constructor
	public PersonalTerritories() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalTerritories();
	}

	@Override
	public String toString() {
		return "Territories " + getCards();
	}
	
	@Override
	public VictoryPoint convertCardToVictoryPoints() {
		int numTerritories = this.getCards().size();
		if (numTerritories == 3) {
			return new VictoryPoint(1);
		} else if (numTerritories == 4) {
			return new VictoryPoint(4);
		} else if (numTerritories == 5) {
			return new VictoryPoint(10);
		} else if (numTerritories == 6) {
			return new VictoryPoint(20);
		} else {
			return new VictoryPoint(0);
		}
	}
}
