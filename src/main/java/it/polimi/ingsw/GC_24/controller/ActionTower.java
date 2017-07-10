package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.cards.Ventures;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.effects.permanent.NoValueEffectFromTowerPlace;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.SubSetOfValues;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.*;

public class ActionTower extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private SetOfValues temporaryCardCost;
	private TowerPlace towerPlace;
	private SetOfValues setOfSales = new SetOfValues();
	private int valueOfFakeFamiliar;

	/**
	 * This constructor saves and uses a temporaryCost: in a single-cost card it
	 * stores that cost, in a multi-cost card it saves the cost passed by the
	 * controller creating the action. If the parameter is null it uses the card
	 * cost, otherwise it uses the parameters
	 */
	public ActionTower(Model game, String familiar, String zone, String floor, String servants, SetOfValues cost,
			SetOfValues setOfSale) {
		super(game, familiar, zone, floor, servants);
		this.towerPlace = (TowerPlace) this.place;
		this.temporaryCardCost = cost;
		this.setOfSales = setOfSale;
	}

	@Override
	public String verify() {
		System.out.println(player.getMyValues());
		String answerToPlayer = "Answer: \n";
		answerToPlayer = verifyIfEnoughServants(answerToPlayer);

		answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);

		answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);

		answerToPlayer = verifyPlaceAvailability(answerToPlayer);

		answerToPlayer = verifyZoneOccupiedByMe(answerToPlayer);

		answerToPlayer = verifyTerritorySpaceAvailability(answerToPlayer);

		answerToPlayer = verifyBoardSpaceAvailability(answerToPlayer);

		answerToPlayer = verifyCardResources(answerToPlayer);

		if (answerToPlayer.equals("Answer: \n"))
			return "ok";

		else
			return answerToPlayer;

	}

	@Override
	public List<ImmediateEffect> run() {
		this.takeRealCost();
//	this.payCoinsforOccupiedTower();
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

	/**
	 * This method is used to see if there is "noValueEffectFromTowerPlace" in
	 * player's Personal Board.
	 * 
	 * @return true if player have this effect, false otherwise.
	 */
	public boolean isThereNoValueEffect() {
		NoValueEffectFromTowerPlace pe = (NoValueEffectFromTowerPlace) player
				.getPermanentEffect("noValueEffectFromTowerPlace");
		if (pe != null) {
			return true;
		}
		return false;
	}

/*	public void payCoinsforOccupiedTower() {
		if (player.getPermanentEffect("noCoinsForOccupiedTower") == null) {
			if (zone.isOccupied())
				this.payValue(new Coin(3));
		}
	}*/

	public void takeEffectsAndRemoveCard() {
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

	public void takeCardAndPay() {
		// Mine - cost --> Then set
		setOfSales.subTwoSetsOfValues(temporaryCardCost);
		this.player.setMyValues(temporaryCardCost.subTwoSetsOfValues(this.player.getMyValues()));
		if (towerPlace.getCorrespondingCard().getType().equalsIgnoreCase("character")) {
			Characters c = (Characters) towerPlace.getCorrespondingCard();
			if (c.getPermanentEffects() != null) {
				player.getActivePermanentEffects().add(c.getPermanentEffects());
			}
		}
		towerPlace.getCorrespondingCard().setCardOnPersonalBoard(player.getMyBoard());
	}

	public void takeRealCost() {
		if (temporaryCardCost.isEmpty()) {
			TowerPlace towerPlace = (TowerPlace) this.place;
			System.out.println(towerPlace.toString());
			this.temporaryCardCost = setOfSales.subTwoSetsOfValues(towerPlace.getCorrespondingCard().getCost());
		}
		System.out.println(temporaryCardCost);
	}



	/**
	 * It checks if you have the resources for taking the card in the place you're
	 * trying to put your Family Member in. It's necessary to distinguish Venture
	 * cards because of the extra requirements needed. If you satisfy the
	 * requirement, this methods checks if you have the resources for this card
	 */
	public String verifyCardResources(String answerToPlayer) {
		if (towerPlace.getCorrespondingCard() != null) {
			String typeOfCard = towerPlace.getCorrespondingCard().getType();
			if (typeOfCard.equalsIgnoreCase("Venture")) {
				Ventures specificCard = (Ventures) towerPlace.getCorrespondingCard();
				Value requirement = specificCard.getRequiredMilitaryPoints();
				SetOfValues cost1 = specificCard.getCost();
				SetOfValues cost2 = specificCard.getAlternativeCost();
				if (temporaryCardCost.equals(cost2) && !this.player.getMyValues().doIHaveEnoughOfThis(requirement)) {
					return answerToPlayer
							+ "You don't have the EXTRA REQUIREMENTS for this card! Choose another card \n";
				} else if (temporaryCardCost.equals(cost1) && !this.player.getMyValues().doIHaveThisSet(cost1)) {
					return answerToPlayer + "You don't have the VALUES for this card! Choose another card \n";
				}
			}
			if (player.getPermanentEffect("discountCoinsCard") != null) {
				SubSetOfValues pes = (SubSetOfValues) player.getPermanentEffect("discountCoinsCard");
				temporaryCardCost.getCoins().subQuantity(pes.getSubSet().getCoins().getQuantity());
				if (temporaryCardCost.getCoins().getQuantity() < 0) {
					temporaryCardCost.getCoins().setQuantity(0);
				}
			}
			if ((player.getPermanentEffect("noCoinsForOccupiedTower") == null && this.zone.isOccupied())){
				temporaryCardCost.getCoins().addQuantity(3);
			}
			
			if (!player.getMyValues().doIHaveThisSet(temporaryCardCost)) {
				return answerToPlayer + "You don't have enough resources to take this card! Choose another card \n";
			}
			
			
		}
		return answerToPlayer;
	}

	public String verifyTerritorySpaceAvailability(String answerToPlayer) {

		TowerPlace tempTowerPlace = (TowerPlace) this.place;
		if (tempTowerPlace.getCorrespondingCard() != null) {
			String typeOfCard = tempTowerPlace.getCorrespondingCard().getType();

			if (player.getPermanentEffect("noMilitaryPointsForTerritories") != null) {
				return answerToPlayer;
			}

			int militaryPoints = this.player.getMyValues().getMilitaryPoints().getQuantity();
			int territorySize = this.player.getMyBoard().getPersonalTerritories().getCards().size();

			if (typeOfCard.equalsIgnoreCase("Territory")) {
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
		}
		return answerToPlayer;

	}

	public String verifyBoardSpaceAvailability(String answerToPlayer) {
		TowerPlace tempTowerPlace = (TowerPlace) this.place;
		if (tempTowerPlace.getCorrespondingCard() != null) {
			String typeOfCard = tempTowerPlace.getCorrespondingCard().getType();
			if (typeOfCard.equalsIgnoreCase("Territory")) {
				if (this.player.getMyBoard().getPersonalTerritories().getCards().size() >= 6) {
					return answerToPlayer + "You have already 6 Territory Cards, no more empty spaces \n";
				}
			}
			if (typeOfCard.equalsIgnoreCase("Building")) {
				if (this.player.getMyBoard().getPersonalBuildings().getCards().size() >= 6) {
					return answerToPlayer + "You have already 6 Building Cards, no more empty spaces \n";
				}
			}
			if (typeOfCard.equalsIgnoreCase("Character")) {
				if (this.player.getMyBoard().getPersonalCharacters().getCards().size() >= 6) {
					return answerToPlayer + "You have already 6 Character Cards, no more empty spaces \n";
				}
			}
			if (typeOfCard.equalsIgnoreCase("Venture")) {
				if (this.player.getMyBoard().getPersonalVentures().getCards().size() >= 6) {
					return answerToPlayer + "You have already 6 Venture Cards, no more empty spaces \n";
				}
			}
		}
		return answerToPlayer;
	}

	@Override
	public String verifyIfEnoughServantsForThisPlace(String answerToPlayer) {
		int placeCostRequired = this.place.getCostDice();
		if (placeCostRequired > (this.familyMember.getMemberValue() + this.servants
				+ getIncrementDieValueFromPermanentEffect())) {
			return answerToPlayer + "You have not used enough servants for this place. Please choose another place. \n";
		}
		return answerToPlayer;
	}

	/**
	 * ##PERMANENT EFFECT CHECK HERE: Increase Die Value Card## This method checks
	 * in Personal Board the Permanent Effect of Characters and if there is
	 * IncreaseDieValueCard Effect gives to player the increment. The increase is
	 * given to the player only if the selected zone to place the family member is
	 * the same as the one specify on the card. If the effect have an alternative
	 * sale it is checked before doing any action.
	 * 
	 * @return increment die value.
	 */
	public int getIncrementDieValueFromPermanentEffect() {
		int incrementDieValueFromPermanentEffect = 0;
		List<PermanentEffect> peList = player.getPermanentEffectList("increaseDieValueCard");
		for (int i = 0; i < peList.size(); i++) {
			IncreaseDieValueCard pe = (IncreaseDieValueCard) peList.get(i);
			if ((pe.getPersonalCards() != null && (pe.getPersonalCards().getType().equalsIgnoreCase(zoneString)))||pe.getPersonalCards()==null) {
				incrementDieValueFromPermanentEffect += pe.getIncreaseDieValue();
				if (pe.getAlternativeSale() == null) {
					setOfSales = pe.getSale();
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

	public List<ImmediateEffect> getImmediateEffects() {
		return immediateEffects;
	}

	public void setImmediateEffects(List<ImmediateEffect> immediateEffects) {
		this.immediateEffects = immediateEffects;
	}

	public SetOfValues getTemporaryCardCost() {
		return temporaryCardCost;
	}

	public void setTemporaryCardCost(SetOfValues temporaryCardCost) {
		this.temporaryCardCost = temporaryCardCost;
	}
}
