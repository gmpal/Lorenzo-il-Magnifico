package it.polimi.ingsw.GC_24.effects;

import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;

public class Exchange extends ImmediateEffect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5137569904363140061L;
	private List<ImmediateEffect> immediateEffectsFromExchange;
	private ExchangePackage exchangePackage;
	private ExchangePackage exchangePackage1;
	private ExchangePackage finalExchange;

	public Exchange(String name, ExchangePackage exchangePackage, ExchangePackage exchangePackage1) {
		super(name);
		this.exchangePackage = exchangePackage;
		this.exchangePackage1 = exchangePackage1;
	}

	@Override
	public void giveImmediateEffect(Player player) {
		ImmediateEffect im = exchangePackage.getImmediateEffect();
		finalExchange.getSet().subTwoSetsOfValues(player.getMyValues());
		if (im.getName().equals("value")) {
			im.giveImmediateEffect(player);
		} else {
			immediateEffectsFromExchange.add(im);
		}
	}
	
	@Override
	public void assignParameters(String string) {
			if (string.equals("1")){
				this.finalExchange = exchangePackage;
			}
			if (string.equals("2")){
				this.finalExchange = exchangePackage1;
			}
	
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Exchange: you can exchange "+ exchangePackage.getSet() +" for "+ exchangePackage.getImmediateEffect());
		if (exchangePackage1!=null){
			builder.append(" or otherwise " + exchangePackage1.getSet() +" for "+ exchangePackage1.getImmediateEffect());
		}
		return builder.toString();
	}

	//getters and setters
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

}
