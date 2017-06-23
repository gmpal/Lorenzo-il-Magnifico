package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.PermanentEffect;

public class Excommunication {
	private PermanentEffect permanentEffect;
	private PermanentEffect alternativPermanentEffect;
	private int round;

	// constructor
	public Excommunication(PermanentEffect permanentEffect, PermanentEffect alternativPermanentEffect, int round) {
		this.permanentEffect = permanentEffect;
		this.alternativPermanentEffect = alternativPermanentEffect;
		this.setRound(round);
	}

	// getters and setters
	public PermanentEffect getPermanentEffect() {
		return permanentEffect;
	}

	public void setPermanentEffect(PermanentEffect permanentEffect) {
		this.permanentEffect = permanentEffect;
	}

	public PermanentEffect getAlternativPermanentEffect() {
		return alternativPermanentEffect;
	}

	public void setAlternativPermanentEffect(PermanentEffect alternativPermanentEffect) {
		this.alternativPermanentEffect = alternativPermanentEffect;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
