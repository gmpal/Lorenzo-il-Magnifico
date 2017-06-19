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
	private int finalActionValue;

	public HarvestAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.harvestPlace = (HarvestPlace) place;
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
		this.getHarvestTileValues();
		this.getFinalActionValue();
		this.createHarvestEffect();
		return immediateEffects;
	}

	private void getFinalActionValue() {
		//TODO: fix with permanent effect
		this.finalActionValue = familyMember.getMemberValue() - harvestPlace.getAdditionalCostDice()+servants;
		
	}

	private void createHarvestEffect() {
		immediateEffects.add(new PerformHarvest("performHarvest", finalActionValue));		
	}

	private void getHarvestTileValues() {
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());		
	}
}
