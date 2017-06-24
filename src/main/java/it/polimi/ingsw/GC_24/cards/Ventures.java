package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.*;

public class Ventures extends Development {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4970264749976478608L;
	private MilitaryPoint requiredMilitaryPoints;
	private SetOfValues alternativeCost;
	private Value pointsAtTheEnd;

	// constructor
	public Ventures(String name, String type, SetOfValues cost, SetOfValues alternativeCost, Value pointsAtTheEnd,
			MilitaryPoint requiredMilitaryPoints, ImmediateEffect immediateEffects, ImmediateEffect immediateEffects1,
			int round) {
		super(name, type, cost, immediateEffects, immediateEffects1, round);
		this.alternativeCost = alternativeCost;
		this.pointsAtTheEnd = pointsAtTheEnd;
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalVentures().setCards(this);
	}

	@Override
	public String toString() {
		return "Venture: Name = " + name + " Cost = " + getCost() + " Required Military Points = " +requiredMilitaryPoints+
				"\n Immediate Effects = " + getImmediateEffect()+" "+getImmediateEffect1()+
				"\nPoints at the end = " + getPointsAtTheEnd();
	}
  
	// getters and setters
	public MilitaryPoint getRequiredMilitaryPoints() {
		return requiredMilitaryPoints;
	}

	public void setRequiredMilitaryPoints(MilitaryPoint requiredMilitaryPoints) {
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}

	public SetOfValues getAlternativeCost() {
		return alternativeCost;
	}

	public void setAlternativeCost(SetOfValues alternativeCost) {
		this.alternativeCost = alternativeCost;
	}

	public Value getPointsAtTheEnd() {
		return pointsAtTheEnd;
	}

	public void setPointsAtTheEnd(Value pointsAtTheEnd) {
		this.pointsAtTheEnd = pointsAtTheEnd;
	}

}
