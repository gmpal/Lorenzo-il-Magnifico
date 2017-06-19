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
	private int finalActionValue;

	public ProductionAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.productionPlace = (ProductionPlace) place;
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
		this.payServants();
		this.getProductionTileValues();
		this.getFinalActionValue();
		this.createProductionEffect();
		return immediateEffects;
	}

	private void getFinalActionValue() {
		//TODO: fix with permanent effect
		this.finalActionValue = familyMember.getMemberValue() - productionPlace.getAdditionalCostDice()+servants;
	}

	private void createProductionEffect() {
		
		immediateEffects.add(new PerformProduction("performProduction", finalActionValue));
		
	}

	private void getProductionTileValues() {
		player.getMyBoard().getBonusTile().giveProductionValues(player.getMyValues());
		
	}
}
