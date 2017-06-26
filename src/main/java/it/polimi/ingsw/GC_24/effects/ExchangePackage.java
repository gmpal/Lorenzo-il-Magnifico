package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public class ExchangePackage implements java.io.Serializable{

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
