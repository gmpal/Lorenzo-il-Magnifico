package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;

public abstract class ImmediateEffect extends Effect {

	// this class gives to the player the resource immediate effect and the
	// immediate special effect
	// constructor needed for subclasses
	public ImmediateEffect(String name) {
		super(name);
	}

	// this method gives the immediate effect of the card to the player when
	// called
	public abstract void giveImmediateEffect(Player player);
}
