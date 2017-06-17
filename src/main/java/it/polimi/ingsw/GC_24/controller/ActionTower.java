package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.places.TowerPlace;

public class ActionTower extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();

	public ActionTower(Model partita, String familiar, String zone, String floor, String servants) {
		super(partita, familiar, zone, floor, servants);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ImmediateEffect> run() {
		place.setFamMemberOnPlace(familyMember);
		familyMember.setAvailable(false);
		if (place.getValue() != null) {
			place.getValue().addValueToSet(player.getMyValues());
		}
		TowerPlace towerPlace = (TowerPlace) place;
		towerPlace.getCorrespondingCard().setCardOnPersonalBoard(player.getMyBoard());
		immediateEffects.add(towerPlace.getCorrespondingCard().getImmediateEffect());
		immediateEffects.add(towerPlace.getCorrespondingCard().getImmediateEffect1());
		giveEffect(immediateEffects);
		return immediateEffects;
	}

	/**
	 * This method gives to player the cards' value effects and it removes them
	 * from the list of immediate effects that needs interaction with client
	 */
	public void giveEffect(List<ImmediateEffect> immediateEffects) {
		String nameEffect;
		for (ImmediateEffect im : immediateEffects) {
			nameEffect = im.getName();
			if (nameEffect.equals("value")) {
				im.giveImmediateEffect(player);
				immediateEffects.remove(im);
			}
		}
	}

	@Override
	public String verify() {
		String answerToPlayer="ok";
		while(answerToPlayer.equals("ok")){
			answerToPlayer = verifyIfEnoughServants();
			answerToPlayer = verifyFamilyMemberAvailability();
			answerToPlayer = verifyPlaceAvailability();
			answerToPlayer = verifyTowerOccupiedByMe();
			answerToPlayer = verifyMoneyForTowerOccupied();
			answerToPlayer = verifyCardResources();
			answerToPlayer = verifyTerritorySpaceAvailability();
			answerToPlayer = verifyBoardSpaceAvailability();
		}
		return answerToPlayer;
	
	}
	
	public String verifyTowerOccupiedByMe(){
		return null;
	}
	
	public String verifyMoneyForTowerOccupied(){
		return null;
	}
	
	public String verifyCardResources(){
		return null;
	}
	
	public String verifyTerritorySpaceAvailability(){
		return null;
	}
	
	public String verifyBoardSpaceAvailability(){
		return null;
	}


}
