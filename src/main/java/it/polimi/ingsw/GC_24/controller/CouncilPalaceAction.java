package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.board.CouncilPalace;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.CouncilPlace;

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
		while (answerToPlayer.equals("Answer: \n")) {
			answerToPlayer = verifyIfEnoughServants(answerToPlayer);
			answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
			answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
		//	answerToPlayer = verifyPlaceAvailability(answerToPlayer);
		//	answerToPlayer = verifyZoneOccupiedByMe(answerToPlayer);
		}
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
		//TODO: gestione del Turno nella logica di gioco
	}

	private void takeEffectFromCouncil() {
		immediateEffects.add(councilPlace.getPrivilegeEffect());
		
	}

}
