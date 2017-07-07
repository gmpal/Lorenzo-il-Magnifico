package it.polimi.ingsw.GC_24.model.cards;

import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class Territories extends Development {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3085569800080233356L;
	private int dieValueHarvest;
	private ImmediateEffect effectForHarvest;

	// constructor
	public Territories(String name, String url, int dieValueHarvest, String type, SetOfValues cost,
			ImmediateEffect immediateEffects, ImmediateEffect immediateEffects1, ImmediateEffect effectForHarvest,
			int round) {
		super(name, url, type, cost, immediateEffects, immediateEffects1, round);
		this.effectForHarvest = effectForHarvest;
		this.dieValueHarvest = dieValueHarvest;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalTerritories().setCards(this);
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
		if (effectForHarvest != null) {
			builder.append(", Die Value For Harvest = " + dieValueHarvest + ", Harvest Effect = " + effectForHarvest);
		}
		builder.append("]");
		return builder.toString();
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