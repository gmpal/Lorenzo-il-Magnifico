package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Buildings extends Development {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 944266538603562720L;
	
	private int costDie;

	// constructor
	public Buildings(String name, int costDie, String type, SetOfValues cost, ImmediateEffect immediateEffects,
			PermanentEffect permanentEffects, int round, ImmediateEffect immediateEffects1) {
		super(name, type, cost, immediateEffects, permanentEffects, round, immediateEffects1);
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
