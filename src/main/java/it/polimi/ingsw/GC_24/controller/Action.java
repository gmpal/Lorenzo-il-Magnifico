package it.polimi.ingsw.GC_24.controller;


import java.util.List;

import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.board.Area;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.places.Place;
import it.polimi.ingsw.GC_24.model.values.Value;


public abstract class Action {

	protected FamilyMember familyMember;
	protected Area zone;
	protected Player player;
	protected Place place;
	protected int servants;
	protected String zoneString;


	// constructor
	public Action(Model game, String familiar, String zone, String floor, String servants) {
		this.player = game.getCurrentPlayer();

		if (familiar.equals("fakeFamiliarForChooseNewCard")){
			//ForChooseNewCard effect
			this.familyMember = new FamilyMember(game.getCurrentPlayer().getMyColour());
		} else {		
			this.familyMember = player.getMyFamily().getMemberfromString(familiar);
		}		
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
	public String verifyIfEnoughServants(String answerToPlayer) {
		if (player.getMyValues().getServants().getQuantity() < this.servants) {

			return answerToPlayer + "You don't have enough servants to use! \n";

		}
		return answerToPlayer;
	}

	public String verifyPlaceAvailability(String answerToPlayer) {
		if (!this.place.isAvailable()) {
			return answerToPlayer + "Sorry, place not available!\n";
		} else
			return answerToPlayer;
	}

	public String verifyFamilyMemberAvailability(String answerToPlayer) {
		if (!this.familyMember.isAvailable()) {
			return answerToPlayer + "Sorry, this familiar is not available! \n";
		} else
			return answerToPlayer;
	}

	public String verifyZoneOccupiedByMe(String answerToPlayer) {
		if (this.zone.isThereSameColour(this.familyMember)) {
			return answerToPlayer + "This zone is already occupied by one of your family members. Choose another zone\n";
		} else
			return answerToPlayer;
	}

	public String verifyIfEnoughServantsForThisPlace(String answerToPlayer) {
		int placeCostRequired = this.place.getCostDice();
		if (placeCostRequired > (this.familyMember.getMemberValue() + this.servants)){
			return answerToPlayer + "You have not used enough servants for this place. Please choose another place\n";
		}
		return answerToPlayer;
	}

	
	// shared run methods
	public void placeFamiliar() {
		place.setFamMemberOnPlace(familyMember);
		familyMember.setAvailable(false);
	}

	public void payValue(Value value) {
		value.subValuefromSet(player.getMyValues());
	}

	public void takeValueFromPlace() {
		if (place.getValue().getEffectValues() != null) {
			place.getValue().getEffectValues().addTwoSetsOfValues(player.getMyValues());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((familyMember == null) ? 0 : familyMember.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + servants;
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		result = prime * result + ((zoneString == null) ? 0 : zoneString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Action other = (Action) obj;
		if (familyMember == null) {
			if (other.familyMember != null)
				return false;
		} else if (!familyMember.equals(other.familyMember))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (servants != other.servants)
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		if (zoneString == null) {
			if (other.zoneString != null)
				return false;
		} else if (!zoneString.equals(other.zoneString))
			return false;
		return true;
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

	public Player getPlayer() {
		return player;
	}
	
	public void setZone(Area zone) {
		this.zone = zone;
	}

	public Area getZone() {
		return zone;
	}
}
