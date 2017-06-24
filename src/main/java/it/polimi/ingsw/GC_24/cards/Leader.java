package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.effects.*;

public class Leader extends Card{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5056159733029761893L;
	private Requirements requirements;
	private ValueEffect valueEffectLeader;
	private ImmediateEffect immediateEffectLeader;
	private PermanentEffect permanentEffectLeader;
	private boolean inUse; // true if the card is already in use
	private boolean oneTimePerTurn; // true if you can use it one time per turn,
									// false if the effect is permanent

	// constructor
	public Leader(String name, Requirements requirements, ValueEffect valueEffectLeader,
			ImmediateEffect immediateEffectLeader, PermanentEffect permanentEffectLeader, boolean oneTimePerTurn) {
		super(name);
		this.requirements = requirements;
		this.valueEffectLeader = valueEffectLeader;
		this.immediateEffectLeader = immediateEffectLeader;
		this.permanentEffectLeader = permanentEffectLeader;
		this.inUse = false;
		this.oneTimePerTurn = oneTimePerTurn;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Leader Card: Name: " + name + "\nRequirements: " + requirements.toString());
		if (valueEffectLeader != null) {
			sb.append("\nValue Effect: " + valueEffectLeader.toString());
		}
		if (immediateEffectLeader != null) {
			sb.append("\nImmediate Effect: " + immediateEffectLeader.toString());
		}
		if (permanentEffectLeader != null) {
			sb.append("\nPermanent Effect: " + permanentEffectLeader.toString());
		}
		if (this.oneTimePerTurn) {
			sb.append("\nYou can use this card one time per turn");
		} else {
			sb.append("\nThe card's effects are permanent");
		}
		return sb.toString();
	}

	// getters and setters
	public Requirements getRequirements() {
		return requirements;
	}

	public void setRequirements(Requirements requirements) {
		this.requirements = requirements;
	}

	public ValueEffect getValueEffectLeader() {
		return valueEffectLeader;
	}

	public void setValueEffectLeader(ValueEffect valueEffectLeader) {
		this.valueEffectLeader = valueEffectLeader;
	}

	public PermanentEffect getPermanentEffectLeader() {
		return permanentEffectLeader;
	}

	public void setPermanentEffectLeader(PermanentEffect permanentEffectLeader) {
		this.permanentEffectLeader = permanentEffectLeader;
	}

	public boolean isOneTimePerTurn() {
		return oneTimePerTurn;
	}

	public void setOneTimePerTurn(boolean oneTimePerTurn) {
		this.oneTimePerTurn = oneTimePerTurn;
	}

	public ImmediateEffect getImmediateEffectLeader() {
		return immediateEffectLeader;
	}

	public void setImmediateEffectLeader(ImmediateEffect immediateEffectLeader) {
		this.immediateEffectLeader = immediateEffectLeader;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

}
