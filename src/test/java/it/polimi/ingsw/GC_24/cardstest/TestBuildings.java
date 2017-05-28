package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.personalboard.PersonalBuildings;

public class TestBuildings extends TestDevelopment {

	Buildings building;
	PersonalBuildings buildings;
	Buildings building2;
	
	@Before
	public void setUp() throws Exception {
		building = new Buildings("Building", false, false, false, 0, "Building", null, null, null, 1, null);
		buildings = new PersonalBuildings();
		building2 = new Buildings("Building2", false, false, false, 0, "Building", null, null, null, 3, null);
	}
	
	@Override
	public void testSetCardOnPersonalBoard() throws Exception {
		buildings.getCards().add(building);
		building.setCardOnPersonalBoard(personalBoard);
		assertEquals(buildings.getCards(), personalBoard.getPersonalBuildings().getCards());
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		buildings.getCards().add(building);
		building.setCardOnPersonalBoard(personalBoard);
		building2.setCardOnPersonalBoard(personalBoard);
		assertFalse(buildings.getCards().equals(personalBoard.getPersonalBuildings().getCards()));
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		buildings.getCards().add(building);
		buildings.getCards().add(building2);
		building.setCardOnPersonalBoard(personalBoard);
		assertFalse(buildings.getCards().equals(personalBoard.getPersonalBuildings().getCards()));
	}
}

