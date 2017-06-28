package it.polimi.ingsw.GC_24.cards;

import java.io.Serializable;

import it.polimi.ingsw.GC_24.effects.Effect;
import it.polimi.ingsw.GC_24.effects.PermanentEffect;

public class Excommunication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2328755180790165117L;
	private PermanentEffect permanentEffect;
	private Effect effect;
	private int round;

	// constructor
	public Excommunication(PermanentEffect permanentEffect, Effect effect, int round) {
		this.permanentEffect = permanentEffect;
		this.effect = effect;
		this.round = round;
	}

	// getters and setters
	public PermanentEffect getPermanentEffect() {
		return permanentEffect;
	}

	public void setPermanentEffect(PermanentEffect permanentEffect) {
		this.permanentEffect = permanentEffect;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
