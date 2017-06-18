package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Buildings extends Development {
	/**
	 * 
	 */
	private static final long serialVersionUID = 944266538603562720L;

	private int dieValueProduction;
	private ImmediateEffect productionEffect, productionEffect1;

	// constructor
	public Buildings(String name, int dieValueProduction, String type, SetOfValues cost,
			ImmediateEffect immediateEffects, ImmediateEffect immediateEffects1, ImmediateEffect productionEffect,
			ImmediateEffect productionEffect1, int round) {
		super(name, type, cost, immediateEffects, immediateEffects1, round);
		this.productionEffect = productionEffect;
		this.productionEffect1 = productionEffect1;
		this.dieValueProduction = dieValueProduction;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalBuildings().setCards(this);
	}

	@Override
	public String toString() {
		return "Building: Name = " + name + " Cost = " + getCost() + " Immediate Effects = " + getImmediateEffect()+" "+getImmediateEffect1()+
				"\nPermanent Effects = " + getPermanentEffect();
	}

	// getter and setter
	public ImmediateEffect getProductionEffect() {
		return productionEffect;
	}

	public int getDieValueProduction() {
		return dieValueProduction;
	}

	public void setDieValueProduction(int dieValueProduction) {
		this.dieValueProduction = dieValueProduction;
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
