package it.polimi.ingsw.GC_24.modeltest.boardtest;

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
	
	@Test
	public void testIsOccupiedFalse() {
		assertFalse(harvest.isOccupied());
	}
	
	@Test
	public void testIsOccupiedTrue() {
		harvest.getPlacesArray().get(1).setFamMemberOnPlace(familyMemberOnPlace);
		assertTrue(harvest.isOccupied());
	}
	
	@Test
	public void testGetFirstEmptyPlace() {
		harvest.getPlacesArray().get(2).setFamMemberOnPlace(familyMemberOnPlace);
		harvest.getPlacesArray().get(0).setFamMemberOnPlace(familyMember);
		harvest.clearPlaces();
		assertEquals(harvest.getFirstEmptyPlace(), harvest.getPlacesArray().get(1));
	}
	
	@Test
	public void testGetPlaceFromStringOrFirstIfZero() {
		assertEquals(harvest.getPlaceFromStringOrFirstIfZero("0"), harvest.getPlacesArray().get(0));
	}
	
	@Test
	public void testGetPlaceFromStringOrFirstIfZeroNotZero() {
		assertEquals(harvest.getPlaceFromStringOrFirstIfZero("2"), harvest.getPlacesArray().get(2));
	}
}
