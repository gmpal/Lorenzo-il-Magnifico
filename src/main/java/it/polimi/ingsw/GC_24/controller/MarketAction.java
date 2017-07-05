package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.places.MarketPlace;
import it.polimi.ingsw.GC_24.model.values.Servant;

public class MarketAction extends Action {

	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private MarketPlace marketPlace;

	public MarketAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.marketPlace = (MarketPlace) place;
	}

	@Override
	public String verify() {
		String answerToPlayer = "Answer: \n";
		
		answerToPlayer = verifyIfEnoughServants(answerToPlayer);
		
		answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
		
		answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
		
		if (!noMarketAvailability()) {
			answerToPlayer = verifyPlaceAvailability(answerToPlayer);
		} else {
			answerToPlayer += "Sorry, place not available!\n";
		}

		if (answerToPlayer.equals("Answer: \n"))
			return "ok";
		else
			return answerToPlayer;
	}

	/**
	 * This method checks if the player has a card with permanent effect
	 * "NoMarketAvailability".
	 * 
	 * @return true if the player has "NoMarketAvailability" effect, false
	 *         otherwise.
	 */
	public boolean noMarketAvailability() {
		if ((player.getPermanentEffect("noMarketAvailability")) != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<ImmediateEffect> run() {

		this.placeFamiliar();
		this.payValue(new Servant(this.servants));
		this.takeValueFromPlace();

		this.takePrivilegeEffectFromMarketPlace();

		return immediateEffects;
	}

	public void takePrivilegeEffectFromMarketPlace() {
		ImmediateEffect im = marketPlace.getPrivilegeEffect();

		if (im != null) {
			immediateEffects.add(im);
		}
	}

	public List<ImmediateEffect> getImmediateEffects() {
		return immediateEffects;
	}
}
