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
	private ImmediateEffect productionEffect, productionEffect1;

	// constructor
	public Buildings(String name, int costDie, String type, SetOfValues cost, ImmediateEffect immediateEffects,
			ImmediateEffect immediateEffects1, ImmediateEffect productionEffect, ImmediateEffect productionEffect1,
			int round) {
		super(name, type, cost, immediateEffects, immediateEffects1, round);
		this.productionEffect = productionEffect;
		this.productionEffect1 = productionEffect1;
		this.costDie = costDie;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalBuildings().setCards(this);
	}

	@Override
	public String toString() {
		return "Building [Name: " + getName() + "\nCost: " + getCost() + "\nImmediate effects: 1."
				+ getImmediateEffect() + " 2." + getImmediateEffect1() + "\nProduction effects: 1."
				+ getProductionEffect() + ", 2." + getProductionEffect() + "\nCost die value: " + costDie + "]";
	}

	// getter and setter
	public int getCostDie() {
		return costDie;
	}

	public void setCostDie(int costDie) {
		this.costDie = costDie;
	}

	public ImmediateEffect getProductionEffect() {
		return productionEffect;
	}

	public void setProductionEffect(ImmediateEffect productionEffect) {
		this.productionEffect = productionEffect;
	}

	public ImmediateEffect getProductionEffect1() {
		return productionEffect1;
	}

	public void setProductionEffect1(ImmediateEffect productionEffect1) {
		this.productionEffect1 = productionEffect1;
	}

}
