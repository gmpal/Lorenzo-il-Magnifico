package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.*;

public class ActionTower extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private SetOfValues temporaryCardCost;
	private TowerPlace towerPlace;
	private SetOfValues setOfSales=new SetOfValues();
	private int valueOfFakeFamiliar;

/**
	 * This constructor saves and uses a temporaryCost: in a single-cost card it
	 * stores that cost, in a multi-cost card it saves the cost passed by the
	 * controller creating the action. If the parameter is null it uses the card
	 * cost, otherwise it uses the parameters
	 */
	public ActionTower(Model game, String familiar, String zone, String floor, String servants, SetOfValues cost, SetOfValues setOfSale) {
		super(game, familiar, zone, floor, servants);
		this.temporaryCardCost = cost;		
		this.towerPlace = (TowerPlace) this.place;
		this.setOfSales=setOfSale;	
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
			answerToPlayer = verifyMoneyForTowerOccupied(answerToPlayer);
			answerToPlayer = verifyTerritorySpaceAvailability(answerToPlayer);
			answerToPlayer = verifyBoardSpaceAvailability(answerToPlayer);
			answerToPlayer = verifyCardResources(answerToPlayer);
		}
		if (answerToPlayer.equals("Answer: \n"))
			return "ok";
		else
			return answerToPlayer;

	}

	@Override
	public List<ImmediateEffect> run() {
		this.takeRealCost();
		this.payCoinsforOccupiedTower();
		this.payValue(new Servant(this.servants));
		this.placeFamiliar();
		if (!isThereNoValueEffect()) {
			this.takeValueFromPlace();
		}
		this.takeCardAndPay();
		this.takeEffectsAndRemoveCard();
		this.giveValueEffect(immediateEffects);

		return immediateEffects;
	}

	/**This method is used to see if there is "noValueEffectFromTowerPlace" in player's Personal Board.
	 * 
	 * @return true if player have this effect, false otherwise. 
	 */
	private boolean isThereNoValueEffect() {
		Characters c;
		for (int i = 0; i < player.getMyBoard().getPersonalCharacters().getCards().size(); i++) {
			c = (Characters) player.getMyBoard().getPersonalCharacters().getCards().get(i);
			if (c.getPermanentEffects().getName().equals("noValueEffectFromTowerPlace")) {
				return true;
			}
		}
		return false;
	}

	private void payCoinsforOccupiedTower() {
		if (zone.isOccupied())
			this.payValue(new Coin(3));
	}

	private void takeEffectsAndRemoveCard() {
		ImmediateEffect im = towerPlace.getCorrespondingCard().getImmediateEffect();
		ImmediateEffect im1 = towerPlace.getCorrespondingCard().getImmediateEffect1();
		if (im != null) {
			immediateEffects.add(im);
		}
		if (im1 != null) {
			immediateEffects.add(im1);
		}
		towerPlace.setCorrespondingCard(null);
	}

	private void takeCardAndPay() {
		// Mine - cost --> Then set
		setOfSales.subTwoSetsOfValues(temporaryCardCost);
		this.player.setMyValues(temporaryCardCost.subTwoSetsOfValues(this.player.getMyValues()));
		towerPlace.getCorrespondingCard().setCardOnPersonalBoard(player.getMyBoard());
	}

	private void takeRealCost() {
		if (temporaryCardCost.isEmpty()) {
			TowerPlace towerPlace = (TowerPlace) this.place;
			System.out.println(towerPlace.toString());
			this.temporaryCardCost = setOfSales.subTwoSetsOfValues(towerPlace.getCorrespondingCard().getCost());
		}
		System.out.println(temporaryCardCost);
	}

	/*
	 * This method checks if you have enough money to put the familyMember in a
	 * tower occupied (3 coins)
	 */
	public String verifyMoneyForTowerOccupied(String answerToPlayer) {

		if (this.zone.isOccupied() && this.player.getMyValues().getCoins().getQuantity() < 3) {
			return answerToPlayer + "You don't have enough coins to place your family member in a tower already occupied\n";
		} else
			return "ok";
	}

	/**
	 * It checks if you have the resources for taking the card in the place
	 * you're trying to put your Family Member in. It's necessary to distinguish
	 * Venture cards because of the extra requirements needed. If you satisfy
	 * the requirement, this methods checks if you have the resources for this
	 * card
	 */
	public String verifyCardResources(String answerToPlayer) {
		String typeOfCard = towerPlace.getCorrespondingCard().getType();
		if (typeOfCard.equalsIgnoreCase("Venture")) {
			Ventures specificCard = (Ventures) towerPlace.getCorrespondingCard();
			Value requirement = specificCard.getRequiredMilitaryPoints();
			if (!this.player.getMyValues().doIHaveEnoughOfThis(requirement)) {
				return answerToPlayer + "You don't have the required value for this card! Choose another card \n";
			}
		}
		
		if (!player.getMyValues().doIHaveThisSet(temporaryCardCost)) {
			return answerToPlayer + "You don't have enough resources to take this card! Choose another card \n";
		}
		else{
				return answerToPlayer;

		}
	}

	public String verifyTerritorySpaceAvailability(String answerToPlayer) {

		TowerPlace tempTowerPlace = (TowerPlace) this.place;
		String typeOfCard = tempTowerPlace.getCorrespondingCard().getType();

		int militaryPoints = this.player.getMyValues().getMilitaryPoints().getQuantity();
		int territorySize = this.player.getMyBoard().getPersonalTerritories().getCards().size();

		if (typeOfCard.equals("Territory")) {
			if (territorySize == 2 && militaryPoints < 3) {
				return answerToPlayer + "Sorry, you need 3 Military Points to unlock the next Territory Space\n";
			}
			if (territorySize == 3 && militaryPoints < 7) {
				return answerToPlayer + "Sorry, you need 7 Military Points to unlock the next Territory Space\n";
			}
			if (territorySize == 4 && militaryPoints < 12) {
				return answerToPlayer + "Sorry, you need 12 Military Points to unlock the next Territory Space\n";
			}
			if (territorySize == 5 && militaryPoints < 18) {
				return answerToPlayer + "Sorry, you need 18 Military Points to unlock the next Territory Space\n";
			}
		}

		return answerToPlayer;

	}

	public String verifyBoardSpaceAvailability(String answerToPlayer) {
		TowerPlace tempTowerPlace = (TowerPlace) this.place;
		String typeOfCard = tempTowerPlace.getCorrespondingCard().getType();
		if (typeOfCard.equals("Territory")) {
			if (this.player.getMyBoard().getPersonalTerritories().getCards().size() >= 6) {
				return answerToPlayer + "You have already 6 Territory Cards, no more empty spaces \n ";
			}
		}
		if (typeOfCard.equals("Building")) {
			if (this.player.getMyBoard().getPersonalBuildings().getCards().size() >= 6) {
				return answerToPlayer + "You have already 6 Buildings Cards, no more empty spaces \n";
			}
		}
		return answerToPlayer;
	}
	
	@Override
	protected String verifyIfEnoughServantsForThisPlace(String answerToPlayer) {
		int placeCostRequired = this.place.getCostDice();
		if (placeCostRequired > (this.familyMember.getMemberValue() + this.servants
				+ getIncrementDieValueFromPermanentEffect())) {
			return answerToPlayer + "You have not used enough servants for this place. Please choose another place. \n";
		}
		return answerToPlayer;
	}

	/**
	 * This method checks in Personal Board the Permanent Effect of Characters
	 * and if there is IncreaseDieValueCard Effect gives to player the increment
	 * 
	 * @return int
	 */
	public int getIncrementDieValueFromPermanentEffect() {
		int incrementDieValueFromPermanentEffect = 0;
		for (int i = 0; i < player.getMyBoard().getPersonalCharacters().getCards().size(); i++) {
			Characters c = (Characters) player.getMyBoard().getPersonalCharacters().getCards().get(i);
			if (c.getPermanentEffects().getName().equals("increaseDieValueCard")) {
				IncreaseDieValueCard pe = (IncreaseDieValueCard) c.getPermanentEffects();
				if (pe.getPersonalCards() != null && (pe.getPersonalCards().getType() == zoneString)) {
					incrementDieValueFromPermanentEffect += pe.getIncreaseDieValue();
					if(pe.getAlternativeSale()==null){
						setOfSales=pe.getSale();
					}
				}
			}
		}
		return incrementDieValueFromPermanentEffect;
	}

	public int getValueOfFakeFamiliar() {
		return valueOfFakeFamiliar;
	}

	public void setValueOfFakeFamiliar(int valueOfFakeFamiliar) {
		this.valueOfFakeFamiliar = valueOfFakeFamiliar;
	}
}
