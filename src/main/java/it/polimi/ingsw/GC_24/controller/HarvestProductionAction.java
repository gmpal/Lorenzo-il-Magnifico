package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.CouncilPlace;
import it.polimi.ingsw.GC_24.places.HarvestPlace;

public class HarvestProductionAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();

	public HarvestProductionAction(Model game, String familiar, String zone, String floor, String servants) {
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
		HarvestPlace harvestPlace = (HarvestPlace) place;
		// immediateEffects.add(harvestPlace.getPrivilegeEffect());
		return immediateEffects;
	}

}
