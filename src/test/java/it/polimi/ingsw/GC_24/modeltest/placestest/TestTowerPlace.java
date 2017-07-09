package it.polimi.ingsw.GC_24.modeltest.placestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.Value;

public class TestTowerPlace {

	TowerPlace tower;
	Value value;
	int costDice = 3;
	FamilyMember familyMember;
	Player player;

	
	@Before
	public void setUp() {
		player = new Player("Giorgia", PlayerColour.RED);
		familyMember = new FamilyMember(player.getMyColour());
		tower = new TowerPlace(0, null, null);
		tower.setAvailable(false);
		tower.setFamMemberOnPlace(familyMember);
		tower.setCorrespondingCard(new Characters("Character", null, "Character", null, null, null, null, 1));
	}
	
	@Test
	public void testClearPlaceFamilyMember() {
		tower.clearPlace();
		assertEquals(null, tower.getFamMemberOnPlace());
	}
	
	@Test
	public void testClearPlaceAvailable() {
		tower.clearPlace();
		assertTrue(tower.isAvailable());
	}
	
	@Test
	public void testClearCorrespondingCard() {
		tower.clearPlace();
		assertEquals(null, tower.getCorrespondingCard());
	}
}
