package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Characters extends Development {

	// constructor
	public Characters(String name, boolean permeff, boolean immeff, boolean speceff, String type, SetOfValues cost,
			ImmediateEffect immediateEffects, PermanentEffect permanentEffects, int round, ValueEffect valueEffects) {
		super(name, permeff, immeff, speceff, type, cost, immediateEffects, permanentEffects, round, valueEffects);
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard) {
		personalBoard.getPersonalCharacters().setCards(this);
	}

}
