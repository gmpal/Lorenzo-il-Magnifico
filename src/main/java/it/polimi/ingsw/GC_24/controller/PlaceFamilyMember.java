package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;

public class PlaceFamilyMember extends Action {

	public PlaceFamilyMember(Player player, FamilyMember familyMember, Place place) {
		
	}

	@Override
	public void run(Player player, FamilyMember familyMember, Place place) {
		place.setFamMemberOnPlace(familyMember);
		familyMember.setAvailable(false);
		place.giveEffects(player);
	}

}
