package it.polimi.ingsw.GC_24.model.cards;

import java.io.Serializable;

import it.polimi.ingsw.GC_24.model.effects.Effect;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;
import it.polimi.ingsw.GC_24.model.values.FaithPoint;

public class Excommunication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2328755180790165117L;
	private PermanentEffect permanentEffect;
	private Effect effect;
	private int round;
	private FaithPoint requiremetsForExcommunication;

	// constructor
	public Excommunication(PermanentEffect permanentEffect, Effect effect, int round,
			FaithPoint requiremetsForExcommunication) {
		this.permanentEffect = permanentEffect;
		this.effect = effect;
		this.round = round;
		this.setRequiremetsForExcommunication(requiremetsForExcommunication);
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

	public FaithPoint getRequiremetsForExcommunication() {
		return requiremetsForExcommunication;
	}

	public void setRequiremetsForExcommunication(FaithPoint requiremetsForExcommunication) {
		this.requiremetsForExcommunication = requiremetsForExcommunication;
	}

	@Override
	public String toString() {
		return "Excommunication [permanentEffect=" + permanentEffect + ", effect=" + effect + ", round=" + round
				+ ", requiremetsForExcommunication=" + requiremetsForExcommunication + "]";
	}
	
	
}
