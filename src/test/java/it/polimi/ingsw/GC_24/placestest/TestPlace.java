package it.polimi.ingsw.GC_24.placestest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.Value;


public abstract class TestPlace {

	Place place;
	protected Value value;
	protected int costDice = 3;
	FamilyMember familyMember;
	Player player;

	
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		familyMember = new FamilyMember(player);
		place.setAvailable(false);
		place.setFamMemberOnPlace(familyMember);
	}
	
	@Test
	public void testClearPlaceFamilyMember() throws Exception {
		place.clearPlace();
		assertEquals(null, place.getFamMemberOnPlace());
	}
	
	@Test
	public void testClearPlaceAvailable() throws Exception {
		place.clearPlace();
		assertTrue(place.isAvailable());
	}

	@Test
	public abstract void testGiveEffects() throws Exception;

}