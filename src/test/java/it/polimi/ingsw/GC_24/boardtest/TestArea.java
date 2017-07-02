package it.polimi.ingsw.GC_24.boardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.board.Harvest;
import it.polimi.ingsw.GC_24.model.dice.*;

public class TestArea {
	
	FamilyMember familyMember;
	FamilyMember familyMemberOnPlace;
	Die die;
	Harvest harvest;
	Harvest emptyHarvest;
	
	@Before
	public void setUp() throws Exception {
		die = new Die(3, DieColour.WHITE);
		familyMember = new FamilyMember(PlayerColour.BLUE, die);
		familyMemberOnPlace = new FamilyMember(null, die);
		harvest = new Harvest(false, 4);
		emptyHarvest = new Harvest(false, 4);
	}

	@Test
	public void testIsThereSameColourFalse() throws Exception {
		familyMemberOnPlace.setPlayerColour(PlayerColour.GREEN);
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		assertFalse(harvest.isThereSameColour(familyMember));
	}
	
	@Test
	public void testIsThereSameColourTrue() throws Exception {
		familyMemberOnPlace.setPlayerColour(PlayerColour.BLUE);
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		assertTrue(harvest.isThereSameColour(familyMember));
	}
	
	@Test
	public void testClearPlaces() throws Exception {
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		harvest.clearPlaces();
		assertEquals(harvest, emptyHarvest);
	}
	
	@Test
	public void testIsOccupiedFalse() throws Exception {
		assertFalse(harvest.isOccupied());
	}
	
	@Test
	public void testIsOccupiedTrue() throws Exception {
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		assertTrue(harvest.isOccupied());
	}
	
	@Test
	public void testGetFirstEmptyPlace() throws Exception {
		harvest.getPlacesArray().get(2).setFamMemberOnPlace(familyMemberOnPlace);
		harvest.getPlacesArray().get(0).setFamMemberOnPlace(familyMember);
		harvest.clearPlaces();
		assertEquals(harvest.getFirstEmptyPlace(), harvest.getPlacesArray().get(1));
	}
	
	@Test
	public void testGetPlaceFromStringOrFirstIfZero() throws Exception {
		assertEquals(harvest.getPlaceFromStringOrFirstIfZero("0"), harvest.getPlacesArray().get(0));
	}
	
	@Test
	public void testGetPlaceFromStringOrFirstIfZeroNotZero() throws Exception {
		assertEquals(harvest.getPlaceFromStringOrFirstIfZero("2"), harvest.getPlacesArray().get(2));
	}
}
