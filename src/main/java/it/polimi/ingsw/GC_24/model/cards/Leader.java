package it.polimi.ingsw.GC_24.model.cards;

import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;

public class Leader extends Card {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5056159733029761893L;
	private Requirements requirements;
	private ValueEffect valueEffectLeader;
	private ImmediateEffect immediateEffectLeader;
	private PermanentEffect permanentEffectLeader;
	/** true if the card is already in use */
	private boolean inUse;
	/**
	 * true if you can use it one time per turn, false if the effect is permanent
	 */
	private boolean oneTimePerTurn;

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
		StringBuilder builder = new StringBuilder();
		builder.append("\n[Name = " + name + ", Requirements: " + requirements);
		if (immediateEffectLeader != null) {
			builder.append(", Immediate Effect: " + immediateEffectLeader);
		}
		if (valueEffectLeader != null) {
			builder.append(", Value Effect: " + valueEffectLeader);
		}
		if (permanentEffectLeader != null) {
			builder.append(", Permanent Effect: " + permanentEffectLeader);
		}
		if (this.oneTimePerTurn) {
			builder.append("\n\tYou can activate this card only one time per turn");
		} else {
			builder.append("\n\tThe card's effect is permanent");
		}
		builder.append("]");
		return builder.toString();
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