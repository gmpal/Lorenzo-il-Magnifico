package it.polimi.ingsw.GC_24.model;

import java.io.Serializable;

import it.polimi.ingsw.GC_24.model.effects.Effect;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;
import it.polimi.ingsw.GC_24.model.values.FaithPoint;

/**
 * This class represent an excommunication card object. Every excommunication
 * card has a permanentEffect or a generic effect (Effect), a round, a
 * requirements and an url useful for GUI.
 * 
 * @author Carlo
 *
 */
public class Excommunication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2328755180790165117L;
	private PermanentEffect permanentEffect;
	private Effect effect;
	private int round;
	private FaithPoint requiremetsForExcommunication;
	private String url;

	// constructor
	public Excommunication(PermanentEffect permanentEffect, Effect effect, int round,
			FaithPoint requiremetsForExcommunication, String url) {
		this.permanentEffect = permanentEffect;
		this.effect = effect;
		this.round = round;
		this.setRequiremetsForExcommunication(requiremetsForExcommunication);
		this.setUrl(url);
	}

	@Override
	public String toString() {
		return "Excommunication [permanentEffect=" + permanentEffect + ", effect=" + effect + ", round=" + round
				+ ", requiremetsForExcommunication=" + requiremetsForExcommunication + "]";
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}