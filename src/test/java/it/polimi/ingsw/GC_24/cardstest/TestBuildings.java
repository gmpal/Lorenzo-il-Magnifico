package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalBuildings;

public class TestBuildings {

	Buildings building;
	PersonalBuildings buildings;
	Buildings building2;
	Player player;
	PersonalBoard personalBoard; 
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		personalBoard = new PersonalBoard(player); 
		building = new Buildings("Building", 0, "Building", null, null, null, 1, null);
		buildings = new PersonalBuildings();
		building2 = new Buildings("Building2", 0, "Building", null, null, null, 3, null);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() throws Exception {
		buildings.getCards().add(building);
		building.setCardOnPersonalBoard(personalBoard);
		assertEquals(buildings.getCards(), personalBoard.getPersonalBuildings().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		buildings.getCards().add(building);
		building.setCardOnPersonalBoard(personalBoard);
		building2.setCardOnPersonalBoard(personalBoard);
		assertFalse(buildings.getCards().equals(personalBoard.getPersonalBuildings().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		buildings.getCards().add(building);
		buildings.getCards().add(building2);
		building.setCardOnPersonalBoard(personalBoard);
		assertFalse(buildings.getCards().equals(personalBoard.getPersonalBuildings().getCards()));
	}
}

