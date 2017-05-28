package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.*;

public class Ventures extends Development {

	private MilitaryPoint requiredMilitaryPoints;

	// constructor
	public Ventures(String name, boolean permeff, boolean immeff, boolean speceff, String type, SetOfValues cost,
			MilitaryPoint requiredMilitaryPoints, ImmediateEffect immediateEffects, PermanentEffect permanentEffects,
			int round, ValueEffect valueEffects) {
		super(name, permeff, immeff, speceff, type, cost, immediateEffects, permanentEffects, round, valueEffects);
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
