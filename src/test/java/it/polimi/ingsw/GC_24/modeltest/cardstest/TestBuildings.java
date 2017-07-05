package it.polimi.ingsw.GC_24.modeltest.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Buildings;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalBuildings;

public class TestBuildings {

	Buildings building;
	PersonalBuildings buildings;
	Buildings building2;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		building = new Buildings("Building", 0, "Building", null, null, null, null, null, 1);
		buildings = new PersonalBuildings();
		building2 = new Buildings("Building2", 0, "Building", null, null, null, null, null, 3);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() throws Exception {
		buildings.getCards().add(building);
		building.setCardOnPersonalBoard(player.getMyBoard());
		assertEquals(buildings.getCards(), player.getMyBoard().getPersonalBuildings().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		buildings.getCards().add(building);
		building.setCardOnPersonalBoard(player.getMyBoard());
		building2.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(buildings.getCards().equals(player.getMyBoard().getPersonalBuildings().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		buildings.getCards().add(building);
		buildings.getCards().add(building2);
		building.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(buildings.getCards().equals(player.getMyBoard().getPersonalBuildings().getCards()));
	}
}

