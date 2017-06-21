package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public class ExchangePackage {

	private SetOfValues set;
	private ImmediateEffect immediateEffect;

	public ExchangePackage(SetOfValues set, ImmediateEffect immediateEffect) {
		this.set = set;
		this.immediateEffect = immediateEffect;
	}

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
