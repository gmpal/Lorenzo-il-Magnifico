package it.polimi.ingsw.GC_24.model.cards;

import it.polimi.ingsw.GC_24.model.effects.*;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class Buildings extends Development {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2263424574013433330L;
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
		StringBuilder builder = new StringBuilder();
		builder.append( "\n[Name = " + name + ", Cost = " + getCost());
		if (getImmediateEffect()!=null){
			builder.append(", Immediate Effects = " + getImmediateEffect());
			if (getImmediateEffect1()!=null)
				builder.append(" and " + getImmediateEffect1());
		}
		if (productionEffect!=null){
			builder.append("\n\tDie Value For Production = "+dieValueProduction +", Production Effects = "+productionEffect);
			if (productionEffect1!=null)
				builder.append(" and " + productionEffect1);
		}
		builder.append("]");
		return builder.toString();
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
