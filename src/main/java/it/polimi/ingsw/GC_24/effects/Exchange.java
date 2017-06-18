package it.polimi.ingsw.GC_24.effects;

import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Exchange extends ImmediateEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SetOfValues set;
	private SetOfValues set1;
	private ImmediateEffect imEff;
	private ImmediateEffect imEff1;
	private List<ImmediateEffect> immediateEffectsFromExchange;
	private HashMap<SetOfValues, ImmediateEffect> exchangeEffects = new HashMap<>();

	public Exchange(String name, SetOfValues set, SetOfValues set1, ImmediateEffect imEff, ImmediateEffect imEff1) {
		super(name);
		this.set = set;
		this.set1 = set1;
		this.imEff = imEff;
		this.imEff1 = imEff1;
		exchangeEffects.put(set, imEff);
		exchangeEffects.put(set1, imEff1);
	}

	@Override
	public void giveImmediateEffect(Player player) {
		ImmediateEffect im = exchangeEffects.get(set);
		set.subTwoSetsOfValues(player.getMyValues());
		if (im.getName().equals("value")) {
			im.giveImmediateEffect(player);
		} else {
			immediateEffectsFromExchange.add(im);
		}
	}

	public void assignParameter(SetOfValues finalSet) {
		set = finalSet;
	}

	public SetOfValues getSet() {
		return set;
	}

	public void setSet(SetOfValues set) {
		this.set = set;
	}

	public SetOfValues getSet1() {
		return set1;
	}

	public void setSet1(SetOfValues set1) {
		this.set1 = set1;
	}

	public ImmediateEffect getImEff() {
		return imEff;
	}

	public void setImEff(ImmediateEffect imEff) {
		this.imEff = imEff;
	}

	public ImmediateEffect getImEff1() {
		return imEff1;
	}

	public void setImEff1(ImmediateEffect imEff1) {
		this.imEff1 = imEff1;
	}

	public List<ImmediateEffect> getImmediateEffectsFromExchange() {
		return immediateEffectsFromExchange;
	}

	public void setImmediateEffectsFromExchange(List<ImmediateEffect> immediateEffectsFromExchange) {
		this.immediateEffectsFromExchange = immediateEffectsFromExchange;
	}

	public HashMap<SetOfValues, ImmediateEffect> getExchangeEffects() {
		return exchangeEffects;
	}

	public void setExchangeEffects(HashMap<SetOfValues, ImmediateEffect> exchangeEffects) {
		this.exchangeEffects = exchangeEffects;
	}

}
