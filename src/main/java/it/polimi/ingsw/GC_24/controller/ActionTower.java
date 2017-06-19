package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class ActionTower extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	protected SetOfValues temporaryCardCost;
	private TowerPlace towerPlace;

	/*
	 * This constructor saves and uses a temporaryCost: in a single-cost card it
	 * stores that cost, in a multi-cost card it saves the cost passed by the
	 * controller creating the action. If the parameter is null it uses the card
	 * cost, otherwise it uses the parameters
	 */
	public ActionTower(Model game, String familiar, String zone, String floor, String servants, SetOfValues cost) {
		super(game, familiar, zone, floor, servants);
		this.temporaryCardCost = cost;
		this.towerPlace = (TowerPlace) this.place;
	}

	@Override
	public String verify() {
		String answerToPlayer = "ok";
		while (answerToPlayer.equals("ok")) {
			answerToPlayer = verifyIfEnoughServants();
			answerToPlayer = verifyIfEnoughServantsForThisPlace();
			answerToPlayer = verifyFamilyMemberAvailability();
			answerToPlayer = verifyPlaceAvailability();
			answerToPlayer = verifyZoneOccupiedByMe();
			answerToPlayer = verifyMoneyForTowerOccupied();
			answerToPlayer = verifyTerritorySpaceAvailability();
			answerToPlayer = verifyBoardSpaceAvailability();
			answerToPlayer = verifyCardResources();
		}
		return answerToPlayer;

	}


	@Override
	public List<ImmediateEffect> run() {
		this.takeRealCost();
		this.payServants();
		this.placeFamiliar();
		this.takeValueFromPlace();
		this.takeCardAndPay();
		this.takeEffects();
		this.giveValueEffect(immediateEffects);
		
		return immediateEffects;
	}

	private void takeEffects() {
		ImmediateEffect im = towerPlace.getCorrespondingCard().getImmediateEffect();
		ImmediateEffect im1 = towerPlace.getCorrespondingCard().getImmediateEffect1();
		if (im != null) {
			immediateEffects.add(im);
		}
		if (im1 != null) {
			immediateEffects.add(im1);
		}
	}

	private void takeCardAndPay() {
		//Mine - cost --> Then set
		this.player.setMyValues(temporaryCardCost.subTwoSetsOfValues(this.player.getMyValues()));
		towerPlace.getCorrespondingCard().setCardOnPersonalBoard(player.getMyBoard());
	}



	private void takeRealCost() {
		{
			if (temporaryCardCost == null) {
				TowerPlace towerPlace = (TowerPlace) this.place;
				this.temporaryCardCost = towerPlace.getCorrespondingCard().getCost();
			}
		}
	}

	
	/*
	 * This method checks if you have enough money to put the familyMember in a
	 * tower occupied (3 coins)
	 */
	public String verifyMoneyForTowerOccupied() {
		if (this.player.getMyValues().getCoins().getQuantity() < 3) {
			return "You don't have enough coins to place your familiar in an already occupied tower";
		} else
			return "ok";
	}

	/*
	 * It checks if you have the resources for taking the card in the place
	 * you're trying to put your Family Member in. It's necessary to distinguish
	 * Venture cards because of the extra requirements needed. If you satisfy the requirement,
	 * this methods checks if you have the resources for this card
	 */
	public String verifyCardResources() {

		String typeOfCard = towerPlace.getCorrespondingCard().getType();
		if (typeOfCard.equals("Venture")) {
			Ventures specificCard = (Ventures) towerPlace.getCorrespondingCard();
			Value requirement = specificCard.getRequiredMilitaryPoints();
			if (!this.player.getMyValues().doIHaveEnoughOfThis(requirement)) {
				return "You don't have the required value for this card! Choose another card";
			}
		}
		if (!player.getMyValues().doIHaveThisSet(temporaryCardCost)) {
			return "You don't have enough resources to take this card! Choose another card";
		} else
			return "ok";
	}

	public String verifyTerritorySpaceAvailability() {
		TowerPlace tempTowerPlace = (TowerPlace) this.place;
		String typeOfCard = tempTowerPlace.getCorrespondingCard().getType();

		int militaryPoints = this.player.getMyValues().getMilitaryPoints().getQuantity();
		int territorySize = this.player.getMyBoard().getPersonalTerritories().getCards().size();

		if (typeOfCard.equals("Territory")) {
			if (territorySize == 2 && militaryPoints < 3) {
				return "Sorry, you need 3 Military Points to unlock the next Territory Space";
			}
			if (territorySize == 3 && militaryPoints < 7) {
				return "Sorry, you need 7 Military Points to unlock the next Territory Space";
			}
			if (territorySize == 4 && militaryPoints < 12) {
				return "Sorry, you need 12 Military Points to unlock the next Territory Space";
			}
			if (territorySize == 5 && militaryPoints < 18) {
				return "Sorry, you need 18 Military Points to unlock the next Territory Space";
			}
		}
		return "ok";

	}

	public String verifyBoardSpaceAvailability() {
		TowerPlace tempTowerPlace = (TowerPlace) this.place;
		String typeOfCard = tempTowerPlace.getCorrespondingCard().getType();
		if (typeOfCard.equals("Territory")) {
			if (this.player.getMyBoard().getPersonalTerritories().getCards().size() >= 6) {
				return "You have already 6 Territory Cards, no more empty spaces " + "--> choose another type of card";
			}
		}
		if (typeOfCard.equals("Building")) {
			if (this.player.getMyBoard().getPersonalBuildings().getCards().size() >= 6) {
				return "You have already 6 Buildings Cards, no more empty spaces " + "--> choose another type of card";
			}
		}
		return "ok";
	}

}
