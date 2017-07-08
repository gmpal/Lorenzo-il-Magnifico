package it.polimi.ingsw.GC_24.model.effects.immediate;

import it.polimi.ingsw.GC_24.model.values.SetOfValues;

/**
 * this class has been made as a part of the Exchange effect. It represents a
 * set of resources that can be exchanged for an immediate effect. Sometimes the
 * Exchange effect will require two of these exchange packages
 */
public class ExchangePackage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -91945156982810819L;
	private SetOfValues set;
	private ImmediateEffect immediateEffect;

	public ExchangePackage(SetOfValues set, ImmediateEffect immediateEffect) {
		this.set = set;
		this.immediateEffect = immediateEffect;
	}

	@Override
	public String toString() {
		return "[" + set + ", for " + immediateEffect + "]";
	}

	// getters and setters
	public SetOfValues getSet() {
		return set;
	}

	public void setSet(SetOfValues set) {
		this.set = set;
	}

	public ImmediateEffect getImmediateEffect() {
		return immediateEffect;
	}

	public void setImmediateEffect(ImmediateEffect immediateEffect) {
		this.immediateEffect = immediateEffect;
	}
}