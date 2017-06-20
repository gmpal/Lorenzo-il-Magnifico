package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Characters extends Development {
	/**
	 * 
	 */
	private static final long serialVersionUID = 727209788794536624L;

	private PermanentEffect permanentEffects;

	// constructor
	public Characters(String name, String type, SetOfValues cost, ImmediateEffect immediateEffects,
			PermanentEffect permanentEffects, ImmediateEffect immediateEffects1, int round) {
		super(name, type, cost, immediateEffects, immediateEffects1, round);
		this.permanentEffects = permanentEffects;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalCharacters().setCards(this);
	}

	@Override
	public String toString() {
		return "Character: Name = " + name + " Cost = " + getCost() + " Immediate Effects = " + getImmediateEffect()+" "+getImmediateEffect1()+
				"\nPermanent Effects = " + getPermanentEffects();
  }

	public PermanentEffect getPermanentEffects() {
		return permanentEffects;
	}

	public void setPermanentEffects(PermanentEffect permanentEffects) {
		this.permanentEffects = permanentEffects;
	}

}
