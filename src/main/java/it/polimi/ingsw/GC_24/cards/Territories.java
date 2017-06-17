package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Territories extends Development {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1023997492010425653L;

	private int costDie;
	private ImmediateEffect effectForHarvest;

	// constructor
	public Territories(String name, int costDie, String type, SetOfValues cost, ImmediateEffect immediateEffects,
			ImmediateEffect immediateEffects1, ImmediateEffect effectForHarvest, int round) {
		super(name, type, cost, immediateEffects, immediateEffects1, round);
		this.effectForHarvest = effectForHarvest;
		this.costDie = costDie;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalTerritories().setCards(this);
	}

	@Override
	public String toString() {
		return "Territory [Name: " + getName() + "\nImmediate effects: 1." + getImmediateEffect() + " 2."
				+ getImmediateEffect1() + "\nHarvest effect: " + getEffectForHarvest() + "\nCost die value: " + costDie
				+ "]";
	}

	// getter and setter
	public int getCostDie() {
		return costDie;
	}

	public void setCostDie(int costDie) {
		this.costDie = costDie;
	}

	public ImmediateEffect getEffectForHarvest() {
		return effectForHarvest;
	}

	public void setEffectForHarvest(ImmediateEffect effectForHarvest) {
		this.effectForHarvest = effectForHarvest;
	}

}
