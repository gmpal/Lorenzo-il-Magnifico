package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;

import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.VictoryPoint;

public abstract class Development extends Card {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9083471155940958683L;

	private SetOfValues cost;
	private String type;
	private ImmediateEffect immediateEffects1;
	private ImmediateEffect immediateEffects;
	private int round;

	// constructor
	/*TODO: i type DEVONO coincidere con i nomi degli array della Board
	 * 1)Territory 2)Characters 3)Buildings 4)Ventures*/
	public Development(String name, String type, SetOfValues cost, ImmediateEffect immediateEffects,
			ImmediateEffect immediateEffects1, int round) {
		super(name);
		this.type = type;
		this.cost = cost;
		this.immediateEffects = immediateEffects;
		this.round = round;
		this.setImmediateEffect1(immediateEffects1);
	}

	// add the card to the right ArrayList on the personal board
	public abstract void setCardOnPersonalBoard(PersonalBoard personalBoard); // redefined
																				// in
																				// every
																				// subclass


	// getter and setters
	public SetOfValues getCost() {
		return cost;
	}

	public void setCost(SetOfValues cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public ImmediateEffect getImmediateEffect() {
		return immediateEffects;
	}

	public void setImmediateEffect(ImmediateEffect immediateEffect) {
		this.immediateEffects = immediateEffect;
	}

	public ImmediateEffect getImmediateEffect1() {
		return immediateEffects1;
	}

	public void setImmediateEffect1(ImmediateEffect immediateEffects1) {
		this.immediateEffects1 = immediateEffects1;
	}
}
