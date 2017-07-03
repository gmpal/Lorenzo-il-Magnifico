package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.board.Area;
import it.polimi.ingsw.GC_24.model.cards.Leader;
import it.polimi.ingsw.GC_24.model.effects.ChangeServantsValue;
import it.polimi.ingsw.GC_24.model.effects.CustomizedPermanentEffect;
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
	private List<String> placementEverywhereLeaderEffect = new ArrayList<>();

	// constructor
	public Action(Model game, String familiar, String zone, String floor, String servants) {
		this.player = game.getCurrentPlayer();

		if (familiar.equals("fakeFamiliarForChooseNewCard")) {
			// ForChooseNewCard effect
			this.familyMember = new FamilyMember(game.getCurrentPlayer().getMyColour());
		} else {
			this.familyMember = player.getMyFamily().getMemberfromString(familiar);
		}
		this.zone = game.getBoard().getZoneFromString(zone);
		this.place = game.getBoard().getZoneFromString(zone).getPlaceFromStringOrFirstIfZero(floor);
		this.servants = Integer.parseInt(servants);
		if (changedServantsValue()) {
			ChangeServantsValue pe = (ChangeServantsValue) player.getPermanentEffect("changeServantsValue");
			this.servants /= pe.getServantsQuantity();
		}
		this.zoneString = zone;

	}

	/**
	 * This method checks if the player has the permanent effect
	 * "ChangeServantsValue". If the players have this permanent effect the quantity
	 * of die value's increase caused by the servants is different.
	 * 
	 * @return true if the player has this effect, false otherwise.
	 */
	public boolean changedServantsValue() {
		if (player.getPermanentEffect("changeServantsValue") != null) {
			return true;
		}
		return false;
	}

	/**
	 * This method gives to player the cards' value effects and it removes them from
	 * the list of immediate effects that needs interaction with client
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
	 * The verify() methods checks if the current action is logically correct, it
	 * returns "ok" if the action is correct, otherwise it returns the answer for
	 * the player
	 */
	public abstract String verify();

	/**
	 * The run() method executes the action and gets the List of ImmediateEffects
	 * that needs interaction with users. The Controller will use this list,
	 */
	public abstract List<ImmediateEffect> run();

	// verify methods
	public String verifyIfEnoughServants(String answerToPlayer) {
		if (player.getMyValues().getServants().getQuantity() < this.servants) {

			return answerToPlayer + "You don't have enough servants to use! \n";

		}
		return answerToPlayer;
	}

	/**
	 * @param answerToPlayer
	 *            (String)
	 * @return String with the errors in case that the place is not available. If
	 *         the player activates the Leader Card with the effect
	 *         "PlaceEveryWhere" it can put his family member on places occupied.
	 */
	public String verifyPlaceAvailability(String answerToPlayer) {
		if (!this.place.isAvailable()) {
			if (placeEveryWhere() && (this.place.getFamMemberOnPlace() != null)) {
				return answerToPlayer;
			}
			return answerToPlayer + "Sorry, place not available!\n";
		} else
			return answerToPlayer;
	}

	/**
	 * This method checks if the player has a card with permanent effect
	 * "placeEveryWhere" activated.
	 * 
	 * @return true if the player has the card and it's activated, false otherwise.
	 */
	public boolean placeEveryWhere() {
		if (player.getPermanentEffect("placeEveryWhere") != null) {
			return true;
		}
		return false;

	}

	public String verifyFamilyMemberAvailability(String answerToPlayer) {
		if (!this.familyMember.isAvailable()) {
			return answerToPlayer + "Sorry, this familiar is not available! \n";
		} else
			return answerToPlayer;
	}

	public String verifyZoneOccupiedByMe(String answerToPlayer) {
		if (this.zone.isThereSameColour(this.familyMember)) {
			return answerToPlayer
					+ "This zone is already occupied by one of your family members. Choose another zone\n";
		} else {
			for (String s : placementEverywhereLeaderEffect) {
				StringTokenizer tokenizer = new StringTokenizer((String) s);
				String place = tokenizer.nextToken();
				String playerColour = tokenizer.nextToken();
				if (playerColour.equalsIgnoreCase(player.getMyColour().toString())
						&& place.equalsIgnoreCase(zoneString)) {
					return answerToPlayer
							+ "This zone is already occupied by one of your family members. Choose another zone\n";
				}
			}
		}
		return answerToPlayer;
	}

	public String verifyIfEnoughServantsForThisPlace(String answerToPlayer) {
		int placeCostRequired = this.place.getCostDice();
		if (placeCostRequired > (this.familyMember.getMemberValue() + this.servants)) {
			return answerToPlayer + "You have not used enough servants for this place. Please choose another place\n";
		}
		return answerToPlayer;
	}

	// shared run methods

	/**
	 * This method put a familiar on a place and set to false the availability of
	 * the family member. If the player has activated a card with permanent effect
	 * "placeEveryWhere" the family member will not put on place.
	 */
	public void placeFamiliar() {
		if (!placeEveryWhere()) {
			place.setFamMemberOnPlace(familyMember);
		} else {
			placementEverywhereLeaderEffect.add(zoneString + " " + player.getMyColour().toString());
		}
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

	public List<String> getPlacementEverywhereLeaderEffect() {
		return placementEverywhereLeaderEffect;
	}

	public void setPlacementEverywhereLeaderEffect(List<String> placementEverywhereLeaderEffect) {
		this.placementEverywhereLeaderEffect = placementEverywhereLeaderEffect;
	}
}
