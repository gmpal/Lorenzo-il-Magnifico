package it.polimi.ingsw.GC_24.model.personalboard;

import it.polimi.ingsw.GC_24.model.cards.Development;
import it.polimi.ingsw.GC_24.model.cards.Ventures;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

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
		Ventures v;
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
