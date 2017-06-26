package it.polimi.ingsw.GC_24.effects;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;

public class Exchange extends ImmediateEffect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5137569904363140061L;
	private List<ImmediateEffect> immediateEffectsFromExchange = new ArrayList<>();
	private ExchangePackage exchangePackage;
	private ExchangePackage exchangePackage1;
	private ExchangePackage finalExchange;

	public Exchange(String name, ExchangePackage exchangePackage, ExchangePackage exchangePackage1) {
		super(name);
		this.exchangePackage = exchangePackage;
		this.exchangePackage1 = exchangePackage1;
		this.finalExchange = exchangePackage;
	}

	@Override
	public void giveImmediateEffect(Player player) {
		ImmediateEffect im = finalExchange.getImmediateEffect();
		finalExchange.getSet().subTwoSetsOfValues(player.getMyValues());
		if (im instanceof ValueEffect) {
			((ValueEffect)im).giveImmediateEffect(player);
		} else {
			immediateEffectsFromExchange.add(im);
		}
	}



	public List<ImmediateEffect> getImmediateEffectsFromExchange() {
		return immediateEffectsFromExchange;
	}

	public void setImmediateEffectsFromExchange(List<ImmediateEffect> immediateEffectsFromExchange) {
		this.immediateEffectsFromExchange = immediateEffectsFromExchange;
	}

	public ExchangePackage getExchangePackage() {
		return exchangePackage;
	}

	public void setExchangePackage(ExchangePackage exchangePackage) {
		this.exchangePackage = exchangePackage;
	}

	public ExchangePackage getExchangePackage1() {
		return exchangePackage1;
	}

	public void setExchangePackage1(ExchangePackage exchangePackage1) {
		this.exchangePackage1 = exchangePackage1;
	}

	@Override
	public String toString() {
		return "Exchange [exchangePackage=" + exchangePackage + ", exchangePackage1=" + exchangePackage1 + "]";
	}

	
	public void assignParameters(int choice) {
			if (choice == 1){
				this.finalExchange = exchangePackage;
			}
			if (choice == 2){
				this.finalExchange = exchangePackage1;
			}
	
		
	}
}
