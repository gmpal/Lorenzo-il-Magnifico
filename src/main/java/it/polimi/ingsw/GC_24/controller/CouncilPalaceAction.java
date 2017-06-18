package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.CouncilPlace;
import it.polimi.ingsw.GC_24.places.TowerPlace;

public class CouncilPalaceAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();

	public CouncilPalaceAction(Model game, String familiar, String zone, String floor, String servants) {
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
		CouncilPlace councilPlace = (CouncilPlace) place;
		place.getValue().addValueToSet(player.getMyValues());
		immediateEffects.add(councilPlace.getPrivilegeEffect());
		return immediateEffects;
	}

}
