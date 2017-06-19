package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.MarketPlace;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.Value;

public class MarketAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private MarketPlace marketPlace;

	public MarketAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.marketPlace = (MarketPlace) place;
	}

	@Override
	public String verify() {
		//TODO:finish
		String answerToPlayer = "ok";
		while (answerToPlayer.equals("ok")) {
			answerToPlayer = verifyIfEnoughServants();
			answerToPlayer = verifyIfEnoughServantsForThisPlace();
			answerToPlayer = verifyFamilyMemberAvailability();
			answerToPlayer = verifyPlaceAvailability();
		//	answerToPlayer = verifyZoneOccupiedByMe();

		}
		return answerToPlayer;
	}

	@Override
	public List<ImmediateEffect> run() {
		this.placeFamiliar();
		this.payServants();
		this.takeValueFromPlace();
		this.takeExtraValueFromMarketPlace();
		this.takeImmediateEffectFromMarketPlace();
		return immediateEffects;
	}

	private void takeImmediateEffectFromMarketPlace() {
		ImmediateEffect im = marketPlace.getPrivilegeEffect();

		if (im != null) {
			immediateEffects.add(im);
		}

	}

	private void takeExtraValueFromMarketPlace() {
		Value extraValue = marketPlace.getValue();

		if (extraValue != null) {
			extraValue.addValueToSet(player.getMyValues());
		}

	}
}
