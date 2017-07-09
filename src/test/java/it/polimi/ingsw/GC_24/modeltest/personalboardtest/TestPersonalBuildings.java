package it.polimi.ingsw.GC_24.modeltest.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Buildings;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalBuildings;

public class TestPersonalBuildings {
	
	Buildings building1;
	Buildings building2;
	PersonalBuildings buildings;
	PersonalBuildings buildingsexpected;
	Player player;

	@Before
	public void setUp() {
		player = new Player("Giorgia", PlayerColour.RED);
		building1 = new Buildings("Building1", null, 0, "Building", null, null, null, null, null, 1);
		building2 = new Buildings("Building2", null, 0, "Building", null, null, null, null, null, 3);
		buildings = new PersonalBuildings();
		buildingsexpected = new PersonalBuildings();
	}

	@Test
	public void testFindCardsInPersonalBoard() {
		building1.setCardOnPersonalBoard(player.getMyBoard());
		building2.setCardOnPersonalBoard(player.getMyBoard());
		buildingsexpected.getCards().add(building1);
		buildingsexpected.getCards().add(building2);
		assertEquals(buildingsexpected.getCards(), buildings.findCardsInPersonalBoard(player.getMyBoard()).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() {
		building1.setCardOnPersonalBoard(player.getMyBoard());
		building2.setCardOnPersonalBoard(player.getMyBoard());
		buildingsexpected.getCards().add(building1);
		assertFalse(buildingsexpected.getCards().equals(buildings.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() {
		building1.setCardOnPersonalBoard(player.getMyBoard());
		buildingsexpected.getCards().add(building1);
		buildingsexpected.getCards().add(building2);
		assertFalse(buildingsexpected.getCards().equals(buildings.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
}
