package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.PerformHarvest;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.ProductionPlace;

public class ProductionAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();

	public ProductionAction(Model game, String familiar, String zone, String floor, String servants) {
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
		player.getMyBoard().getBonusTile().giveProductionValues(player.getMyValues());
		ProductionPlace productionPlace = (ProductionPlace) place;
		int finalActionValue = familyMember.getMemberValue() - productionPlace.getAdditionalCostDice()+servants;	//to fix when there are permanent effect
		immediateEffects.add(new PerformHarvest("performProduction", finalActionValue));
		return immediateEffects;
	}
}
