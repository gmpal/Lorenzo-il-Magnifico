package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.TowerPlace;

public class ActionTower extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();

	public ActionTower(Model partita, String familiar, String zone, String floor, String servants) {
		super(partita, familiar, zone, floor, servants);
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
		ImmediateEffect im = towerPlace.getCorrespondingCard().getImmediateEffect();
		ImmediateEffect im1 = towerPlace.getCorrespondingCard().getImmediateEffect1();
		if (im != null) {
			immediateEffects.add(im);
		}
		if (im1 != null) {
			immediateEffects.add(im1);
		}
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
			answerToPlayer = verifyZoneOccupiedByMe();
			answerToPlayer = verifyMoneyForTowerOccupied();
			answerToPlayer = verifyCardResources();
			answerToPlayer = verifyTerritorySpaceAvailability();
			answerToPlayer = verifyBoardSpaceAvailability();
		}
		return answerToPlayer;
	
	}
	
	
	/*This method checks if you have enough money to put the familyMember in a tower occupied (3 coins)*/
	public String verifyMoneyForTowerOccupied(){
		if (this.player.getMyValues().getCoins().getQuantity()<3){
			return "You don't have enough coins to place your familiar in an already occupied tower";
		}
		else return "ok";
	}
	
	/*It checks if you have the resources for taking the card in the place
	 * you're trying to put your Family Member in*/
	public String verifyCardResources(){
		return null;

	}
	
	public String verifyTerritorySpaceAvailability(){
		return null;
	}
	
	public String verifyBoardSpaceAvailability(){
		TowerPlace tempTowerPlace = (TowerPlace) this.place;
		String typeOfCard = tempTowerPlace.getCorrespondingCard().getType();
		if (typeOfCard.equals("Territory")){
			
		}
	}


}
