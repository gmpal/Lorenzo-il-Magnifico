package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Ventures extends Development{
	
	private MilitaryPoint requiredMilitaryPoints;
	
	public Ventures(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost, MilitaryPoint requiredMilitaryPoints) {
		super(name, permeff, immeff, speceff, cost);
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}
	
	public void setCardOnPersonalBoard(PersonalBoard personalBoard){
		personalBoard.getPersonalVentures().setCards(this);
	}

	public MilitaryPoint getRequiredMilitaryPoints() {
		return requiredMilitaryPoints;
	}

	public void setRequiredMilitaryPoints(MilitaryPoint requiredMilitaryPoints) {
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}

}
