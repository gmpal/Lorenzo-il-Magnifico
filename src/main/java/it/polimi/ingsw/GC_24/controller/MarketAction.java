package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.MarketPlace;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.SetOfValues;
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
		String answerToPlayer = "Answer: \n";
		while (answerToPlayer.equals("Answer: \n")) {
			answerToPlayer = verifyIfEnoughServants(answerToPlayer);
			answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
			answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
			answerToPlayer = verifyPlaceAvailability(answerToPlayer);
			answerToPlayer = verifyZoneOccupiedByMe(answerToPlayer);
		}
		if (answerToPlayer.equals("Answer: \n")) return "ok";
		else return answerToPlayer;
	}

	@Override
	public List<ImmediateEffect> run() {
		this.placeFamiliar();
		this.payValue(new Servant(this.servants));
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
		SetOfValues extraValue = marketPlace.getValue().getEffectValues();
		if (extraValue != null) {
			extraValue.addTwoSetsOfValues(player.getMyValues());
		}

	}
}
