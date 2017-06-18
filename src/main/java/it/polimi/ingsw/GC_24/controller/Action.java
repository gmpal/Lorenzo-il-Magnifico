package it.polimi.ingsw.GC_24.controller;

import java.util.List;

import it.polimi.ingsw.GC_24.board.Area;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;

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
		this.familyMember = player.getMyFamily().getMemberfromString(familiar);
		this.zone = game.getBoard().getZoneFromString(zone);
		this.place = game.getBoard().getZoneFromString(zone).getPlaceFromString(floor);
		this.servants = Integer.parseInt(servants);
		this.zoneString=zone;
	}
	
	/**
	 * This method gives to player the cards' value effects and it removes them
	 * from the list of immediate effects that needs interaction with client
	 */
	public void giveValueEffect(List<ImmediateEffect> immediateEffects) {
		String nameEffect;
		for (ImmediateEffect im : immediateEffects) {
			nameEffect = im.getName();
			if (nameEffect.equals("value")) {
				im.giveImmediateEffect(player);
				immediateEffects.remove(im);
			}
		}
	}

	/**
	 * The verify() methods checks if the current action is logically correct,
	 * it returns "ok" if the action is correct, otherwise it returns the answer for the player
	 */
	public abstract String verify();

	/** The run() method executes the action and gets the List of ImmediateEffects that needs
	 * interaction with users. The Controller will use this list,  */
	public abstract List<ImmediateEffect> run();

	//verify methods
	public String verifyIfEnoughServants(){
		if (player.getMyValues().getServants().getQuantity() < this.servants){
			return "You don't have enough servants to use!";
		} else return "ok";
	}
	
	public String verifyPlaceAvailability(){
		if (!this.place.isAvailable()){
			return "Sorry, place not available!";
		}else return "ok";
	}
	
	public String verifyFamilyMemberAvailability(){
		if (!this.familyMember.isAvailable()){
			return "Sorry, this familiar is not available!";
		}else return "ok";
	}
	
	public String verifyZoneOccupiedByMe(){
		if (this.zone.isThereSameColour(this.familyMember)){
			return "This zone is already occupied by one of your familiar. Choose another zone.";
		}
		else return "ok";
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
