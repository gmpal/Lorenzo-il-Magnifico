package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.*;

public class Ventures extends Development {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6234182947488565457L;
	
	private MilitaryPoint requiredMilitaryPoints;

	// constructor
	public Ventures(String name, String type, SetOfValues cost,
			MilitaryPoint requiredMilitaryPoints, ImmediateEffect immediateEffects, PermanentEffect permanentEffects,
			int round, ValueEffect valueEffects) {
		super(name, type, cost, immediateEffects, permanentEffects, round, valueEffects);
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalVentures().setCards(this);
	}

	@Override
	public String toString() {
		return "Buildings [name=" + getName() + ", permanentEffect=" + getPermanentEffect()
				+ ", requiredMilitaryPoints=" + requiredMilitaryPoints + "]";
	}

	// getters and setters
	public MilitaryPoint getRequiredMilitaryPoints() {
		return requiredMilitaryPoints;
	}

	public void setRequiredMilitaryPoints(MilitaryPoint requiredMilitaryPoints) {
		this.requiredMilitaryPoints = requiredMilitaryPoints;
	}

}
