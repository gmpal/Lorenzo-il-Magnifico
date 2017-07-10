package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.board.CouncilPalace;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.places.CouncilPlace;

public class CouncilPalaceAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private CouncilPlace councilPlace;

	public CouncilPalaceAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.councilPlace = (CouncilPlace) place;
	}

	@Override
	public String verify() {
		String answerToPlayer = "Answer: \n";
		
			answerToPlayer = verifyIfEnoughServants(answerToPlayer);
			answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
			answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
	
		if (answerToPlayer.equals("Answer: \n")) return "ok";
		else return answerToPlayer;
	}

	@Override
	public List<ImmediateEffect> run() {
		this.placeFamiliar();
		this.takeValueFromPlace();
		this.takeEffectFromCouncil();
		this.updateTurn();
		return immediateEffects;
	}

	private void updateTurn() {
		CouncilPalace palace = (CouncilPalace) this.zone; 		
		palace.updateTurn(this.player);
	}

	private void takeEffectFromCouncil() {
		immediateEffects.add(councilPlace.getPrivilegeEffect());
		
	}

}
