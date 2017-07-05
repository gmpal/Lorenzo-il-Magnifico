package it.polimi.ingsw.GC_24.modeltest.placestest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.places.CouncilPlace;
import it.polimi.ingsw.GC_24.model.places.Place;
import it.polimi.ingsw.GC_24.model.values.Value;

public class TestPlace {

	Place place;
	Value value;
	int costDice = 3;
	FamilyMember familyMember;
	Player player;

	
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		familyMember = new FamilyMember(player.getMyColour());
		place = new CouncilPlace(0, null, null);
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

}
