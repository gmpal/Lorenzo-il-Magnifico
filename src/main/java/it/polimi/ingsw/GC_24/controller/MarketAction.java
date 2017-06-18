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

	public MarketAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
	}

	@Override
	public boolean verify() {
		return false;
	}

	@Override
	public List<ImmediateEffect> run() {
		place.setFamMemberOnPlace(familyMember);
		familyMember.setAvailable(false);
		MarketPlace marketPlace = (MarketPlace) place;
		Value value = marketPlace.getValue();
		Value extraValue = marketPlace.getValue();
		ImmediateEffect im = marketPlace.getPrivilegeEffect();
		if (value != null) {
			value.addValueToSet(player.getMyValues());
		}
		if (extraValue != null) {
			extraValue.addValueToSet(player.getMyValues());
		}
		if (im != null) {
			immediateEffects.add(im);
		}
		return immediateEffects;
	}
}
