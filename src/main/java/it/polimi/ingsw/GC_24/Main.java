package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.board.Harvest;
import it.polimi.ingsw.GC_24.dice.Die;
import it.polimi.ingsw.GC_24.dice.DieColour;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.PlayerColour;

public class Main {

	public static void main(String[] args){
		FamilyMember familyMember;
		FamilyMember familyMemberOnPlace;
		Die die;
		Harvest harvest;
		
		die = new Die(3, DieColour.WHITE);
		familyMember = new FamilyMember(PlayerColour.BLUE, die);
		familyMemberOnPlace = new FamilyMember(PlayerColour.BLUE, die);
		harvest = new Harvest(false, 4);
		
		harvest.getPlacesArray().get(0).setFamMemberOnPlace(familyMemberOnPlace);
		System.out.print(harvest.isThereSameColour(familyMember));
	}
}
