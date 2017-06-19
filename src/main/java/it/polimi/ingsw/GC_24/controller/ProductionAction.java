package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.PerformHarvest;
import it.polimi.ingsw.GC_24.effects.PerformProduction;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.ProductionPlace;

public class ProductionAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private ProductionPlace productionPlace;

	public ProductionAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.productionPlace = (ProductionPlace) place;
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
			answerToPlayer = verifyZoneOccupiedByMe(); //?
			
		}
		return answerToPlayer;
	}

	@Override
	public List<ImmediateEffect> run() {
		this.placeFamiliar();
		this.payServants();
		this.getProductionTileValues();
		this.createProductionEffect();
		return immediateEffects;
	}

	private void createProductionEffect() {
		//TODO: fix with permanent effect
		int finalActionValue = familyMember.getMemberValue() - productionPlace.getAdditionalCostDice()+servants;	//to fix when there are permanent effect
		immediateEffects.add(new PerformProduction("performProduction", finalActionValue));
		
	}

	private void getProductionTileValues() {
		player.getMyBoard().getBonusTile().giveProductionValues(player.getMyValues());
		
	}
}
