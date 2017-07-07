package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.ArrayList;
import java.util.HashMap;
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
		System.out.println("Taking the effects");
		ImmediateEffect im = finalExchange.getImmediateEffect();
		finalExchange.getSet().subTwoSetsOfValues(player.getMyValues());
		System.out.println("Subtracting the sets... : new set");
		System.out.println(player.getMyValues());
		if (im instanceof ValueEffect) {
			((ValueEffect) im).giveImmediateEffect(player);
		} else {
			immediateEffectsFromExchange.add(im);
		}
	}

	public void assignParameters(int string) {
		if (string == 1) {
			this.finalExchange = exchangePackage;
		}
		if (string == 2) {
			this.finalExchange = exchangePackage1;
		}
	}

	@Override
	public String generateParametersRequest() {
		if (getExchangePackage1() != null) {
			String request = "You have to choose between exchanging \n " + exchangePackage.toString() + "\nand\n "
					+ exchangePackage1.toString();
			return request;
		} else
			return null;

	}

	@Override
	public HashMap<String, Object> generateHashMapToSend(String response) {
		if (getExchangePackage1() != null) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("exchangeParamRequest", response);
			return map;
		} else
			return null;
	}

	@Override
	public void assignParameters(String responseFromClient) {
		if (getExchangePackage1() != null) {
			if (responseFromClient.equals("1")) {
				this.finalExchange = exchangePackage;

			} else {
				this.finalExchange = exchangePackage1;
			}
		}
	}

	@Override
	public List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects) {
		secondaryInteractiveEffects.addAll(getImmediateEffectsFromExchange());
		return secondaryInteractiveEffects;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exchange: you can exchange " + exchangePackage.getSet() + " for "
				+ exchangePackage.getImmediateEffect());
		if (exchangePackage1 != null) {
			builder.append(
					" or otherwise " + exchangePackage1.getSet() + " for " + exchangePackage1.getImmediateEffect());
		}
		return builder.toString();
	}

	// getters and setters
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

	public ExchangePackage getFinalExchange() {
		return finalExchange;
	}

	public void setFinalExchange(ExchangePackage finalExchange) {
		this.finalExchange = finalExchange;
	}
}