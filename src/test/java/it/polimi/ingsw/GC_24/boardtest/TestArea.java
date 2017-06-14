package it.polimi.ingsw.GC_24.boardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.board.Harvest;
import it.polimi.ingsw.GC_24.dice.*;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.PlayerColour;

public class TestArea {
	
	FamilyMember familyMember;
	FamilyMember familyMemberOnPlace;
	Die die;
	Harvest harvest;
	Harvest emptyHarvest;
	
	@Before
	public void setUp() {
		die = new Die(3, DieColour.WHITE);
		familyMember = new FamilyMember(PlayerColour.BLUE, die);
		familyMemberOnPlace = new FamilyMember(null, die);
		harvest = new Harvest(false, 4);
		emptyHarvest = new Harvest(false, 4);

		
	}

	@Test
	public void testIsThereSameColourFalse() {
		familyMemberOnPlace.setPlayerColour(PlayerColour.GREEN);
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		assertFalse(harvest.isThereSameColour(familyMember));
	}
	
	@Test
	public void testIsThereSameColourTrue() {
		familyMemberOnPlace.setPlayerColour(PlayerColour.BLUE);
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		assertTrue(harvest.isThereSameColour(familyMember));
	}
	
	@Test
	public void testClearPlaces() {
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		harvest.clearPlaces();
		assertEquals(harvest, emptyHarvest);
	}
}
