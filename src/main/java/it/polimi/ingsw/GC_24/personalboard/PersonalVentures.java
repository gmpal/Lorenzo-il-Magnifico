package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.values.VictoryPoint;

public class PersonalVentures extends PersonalCards{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 473515650189053083L;

	//constructor
	public PersonalVentures() {
		super();
	}
	
	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard){
		return playersBoard.getPersonalVentures();
	}

	@Override
	public String toString() {
		return "Ventures " + getCards();
	}

	@Override
	public VictoryPoint convertCardToVictoryPoints() {
		Ventures v = new Ventures(null, null, null, null, null, null, null, null, 0);
		VictoryPoint point = new VictoryPoint(0);
		for (Development d : this.getCards()) {
			v = (Ventures) d;
			point.addQuantity(v.getPointsAtTheEnd().getQuantity());
		}
		return point;
	}

	@Override
	public String getType() {
		return "Ventures";
	}
	
	
}
