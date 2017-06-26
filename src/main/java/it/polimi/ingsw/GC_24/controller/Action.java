package it.polimi.ingsw.GC_24.controller;

import java.util.List;

import it.polimi.ingsw.GC_24.board.Area;
import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public abstract class Action {

	protected FamilyMember familyMember;
	protected Area zone;
	protected Player player;
	protected Place place;
	protected int servants;
	protected SetOfValues temporaryCardCost;
	private String zoneString;
	protected SetOfValues setOfSales=new SetOfValues();


	// constructor
	public Action(Model game, String familiar, String zone, String floor, String servants) {
		if (familiar.equals("fakeFamiliarForChooseNewCard")){
			//ForChooseNewCard effect
			this.familyMember = new FamilyMember(null,null);
		} else {
			this.familyMember = player.getMyFamily().getMemberfromString(familiar);
		}
		
		this.player = game.getCurrentPlayer();
		this.zone = game.getBoard().getZoneFromString(zone);
		this.place = game.getBoard().getZoneFromString(zone).getPlaceFromStringOrFirstIfZero(floor);
		this.servants = Integer.parseInt(servants);
		this.zoneString = zone;
	}

	/**
	 * This method gives to player the cards' value effects and it removes them
	 * from the list of immediate effects that needs interaction with client
	 */
	public void giveValueEffect(List<ImmediateEffect> immediateEffects) {
		String nameEffect;
		for (int i = 0; i < immediateEffects.size(); i++) {
			nameEffect = immediateEffects.get(i).getName();
			if (nameEffect.equals("value")) {
				immediateEffects.get(i).giveImmediateEffect(player);
				immediateEffects.remove(immediateEffects.get(i));
				i--;
			}
		}
	}

	/**
	 * The verify() methods checks if the current action is logically correct,
	 * it returns "ok" if the action is correct, otherwise it returns the answer
	 * for the player
	 */
	public abstract String verify();

	/**
	 * The run() method executes the action and gets the List of
	 * ImmediateEffects that needs interaction with users. The Controller will
	 * use this list,
	 */
	public abstract List<ImmediateEffect> run();

	// verify methods
	protected String verifyIfEnoughServants(String answerToPlayer) {
		if (player.getMyValues().getServants().getQuantity() < this.servants) {

			return answerToPlayer + "You don't have enough servants to use! \n";

		}
		return answerToPlayer;
	}

	protected String verifyPlaceAvailability(String answerToPlayer) {

		if (!this.place.isAvailable() || this.place == null) {
			return answerToPlayer + "Sorry, place not available!";
		} else
			return answerToPlayer;
	}

	protected String verifyFamilyMemberAvailability(String answerToPlayer) {
		if (!this.familyMember.isAvailable()) {
			return answerToPlayer + "Sorry, this familiar is not available! \n";
		} else
			return answerToPlayer;
	}

	protected String verifyZoneOccupiedByMe(String answerToPlayer) {
		if (this.zone.isThereSameColour(this.familyMember)) {
			return answerToPlayer + "This zone is already occupied by one of your familiar. Choose another zone. \n";
		} else
			return answerToPlayer;
	}

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

	// shared run methods
	protected void placeFamiliar() {
		place.setFamMemberOnPlace(familyMember);
		familyMember.setAvailable(false);
	}

	protected void payValue(Value value) {
		value.subValuefromSet(player.getMyValues());
	}

	protected void takeValueFromPlace() {
		if (place.getValue().getEffectValues() != null) {
			place.getValue().getEffectValues().addTwoSetsOfValues(player.getMyValues());
		}
	}

	// getters and setters
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
