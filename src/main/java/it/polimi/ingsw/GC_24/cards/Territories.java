package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Territories extends Development {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1023997492010425653L;

	private int dieValueHarvest;
	private ImmediateEffect effectForHarvest;

	// constructor
	public Territories(String name, int dieValueHarvest, String type, SetOfValues cost,
			ImmediateEffect immediateEffects, ImmediateEffect immediateEffects1, ImmediateEffect effectForHarvest,
			int round) {
		super(name, type, cost, immediateEffects, immediateEffects1, round);
		this.effectForHarvest = effectForHarvest;
		this.dieValueHarvest = dieValueHarvest;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalTerritories().setCards(this);
	}

	@Override
	public String toString() {
		return "Territory: Name = " + name + " Cost = " + getCost() + " Immediate Effects = " + getImmediateEffect()+" "+getImmediateEffect1()+
				"\nDie Value For Production = "+dieValueHarvest +"Harvest Effect = " + effectForHarvest;
	}
  
	// getter and setter
	public int getDieValueHarvest() {
		return dieValueHarvest;
	}

	public void setDieValueHarvest(int dieValueHarvest) {
		this.dieValueHarvest = dieValueHarvest;
	}

	public ImmediateEffect getEffectForHarvest() {
		return effectForHarvest;
	}

	public void setEffectForHarvest(ImmediateEffect effectForHarvest) {
		this.effectForHarvest = effectForHarvest;
	}

}
