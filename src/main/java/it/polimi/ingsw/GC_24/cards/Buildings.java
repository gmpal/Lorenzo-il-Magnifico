package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Buildings extends Development {

	private int costDie;

	// constructor
	public Buildings(String name, boolean permeff, boolean immeff, boolean speceff, int costDie, String type,
			SetOfValues cost, ImmediateEffect immediateEffects, PermanentEffect permanentEffects, int round, ValueEffect valueEffects) {
		super(name, permeff, immeff, speceff, type, cost, immediateEffects, permanentEffects, round, valueEffects);
		this.costDie = costDie;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalBuildings().setCards(this);
	}

	@Override
	public String toString() {
		return "Building [name=" + getName() + ", immediateEffect=" + getImmediateEffect() + ", costDie=" + costDie
				+ "]";
	}

	// getter and setter
	public int getCostDie() {
		return costDie;
	}

	public void setCostDie(int costDie) {
		this.costDie = costDie;
	}

}
