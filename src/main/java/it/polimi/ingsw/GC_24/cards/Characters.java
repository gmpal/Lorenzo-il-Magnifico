package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Characters extends Development {
	/**
	 * 
	 */
	private static final long serialVersionUID = 727209788794536624L;
	

	// constructor
	public Characters(String name, String type, SetOfValues cost,
			ImmediateEffect immediateEffects, PermanentEffect permanentEffects, int round, ImmediateEffect immediateEffects1) {
		super(name, type, cost, immediateEffects, permanentEffects, round, immediateEffects1);
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalCharacters().setCards(this);
	}

}
