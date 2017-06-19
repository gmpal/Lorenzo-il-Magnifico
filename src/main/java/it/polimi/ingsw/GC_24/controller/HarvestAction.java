package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.PerformHarvest;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.HarvestPlace;

public class HarvestAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private HarvestPlace harvestPlace;

	public HarvestAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.harvestPlace = (HarvestPlace) place;
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
		this.getHarvestTileValues();
		this.createHarvestEffect();
		return immediateEffects;
	}

	private void createHarvestEffect() {
		//TODO: fix with permanent effect
		int finalActionValue = familyMember.getMemberValue() - harvestPlace.getAdditionalCostDice()+servants;
		immediateEffects.add(new PerformHarvest("performHarvest", finalActionValue));		
	}

	private void getHarvestTileValues() {
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());		
	}
}
