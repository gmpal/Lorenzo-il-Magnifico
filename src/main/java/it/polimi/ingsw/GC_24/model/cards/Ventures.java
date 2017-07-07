package it.polimi.ingsw.GC_24.model.cards;

import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.model.values.*;

public class Ventures extends Development {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4970264749976478608L;
	private MilitaryPoint requiredMilitaryPoints;
	private SetOfValues alternativeCost;
	private Value pointsAtTheEnd;

	// constructor
	public Ventures(String name, String url, String type, SetOfValues cost, SetOfValues alternativeCost, Value pointsAtTheEnd,
			MilitaryPoint requiredMilitaryPoints, ImmediateEffect immediateEffects, ImmediateEffect immediateEffects1,
			int round) {
		super(name, url, type, cost, immediateEffects, immediateEffects1, round);
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
		StringBuilder builder = new StringBuilder();
		builder.append("\n[Name = " + name);
		if (getImmediateEffect() != null) {
			builder.append(", Immediate Effects = " + getImmediateEffect());
			if (getImmediateEffect1() != null)
				builder.append(" and " + getImmediateEffect1());
		}
		if (requiredMilitaryPoints != null) {
			builder.append(", Cost Military Points = " + alternativeCost + " (Required Military Points = "
					+ requiredMilitaryPoints + ")");
			if (getCost() != null) {
				builder.append(", Cost Values = " + getCost());
			}
		} else {
			builder.append(", Cost = " + getCost());
		}
		builder.append(", Victory Points at the End = " + getPointsAtTheEnd().getQuantity() + "]");
		return builder.toString();
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