package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public abstract class Development extends Card{
	
	private SetOfValues cost;
	private String type;
	private ImmediateEffect immediateEffects;
	private PermanentEffect permanentEffects;
	private int round;
	
	//constructor
	public Development(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost) {
		super(name, permeff, immeff, speceff);
		this.cost = cost;
	}
	
	//add the card to the right ArrayList on the personal board
	public abstract void setCardOnPersonalBoard(PersonalBoard personalBoard); // redefined in every subclass
	
	//getter and setters
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

	public PermanentEffect getPermanentEffect() {
		return permanentEffects;
	}

	public void setPermanentEffect(PermanentEffect permanentEffect) {
		this.permanentEffects = permanentEffect;
	}

}
