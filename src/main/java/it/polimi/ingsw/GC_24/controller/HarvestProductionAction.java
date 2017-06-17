package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.PerformHarvest;
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
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
		HarvestPlace harvestPlace = (HarvestPlace) place;
		int finalActionValue = familyMember.getMemberValue() - harvestPlace.getAdditionalCostDice()+servants;	//to fix when there are permanent effect
		immediateEffects.add(new PerformHarvest("performHarvest", finalActionValue));
		return immediateEffects;
	}
}
