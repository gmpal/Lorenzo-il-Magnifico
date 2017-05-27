package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.personalboard.PersonalTerritories;

public class TestTerritories extends TestDevelopment {

	Territories territory;
	PersonalTerritories territories;
	Territories territory2;
	
	@Before
	public void setUp() throws Exception {
		territory = new Territories("Territory", false, false, false, null,0);
		territories = new PersonalTerritories();
		territory2 = new Territories("Territory2", false, false, false, null,0);
	}
	
	@Override
	public void testSetCardOnPersonalBoard() throws Exception {
		territories.getCards().add(territory);
		territory.setCardOnPersonalBoard(personalBoard);
		assertEquals(territories.getCards(), personalBoard.getPersonalTerritories().getCards());
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		territories.getCards().add(territory);
		territory.setCardOnPersonalBoard(personalBoard);
		territory2.setCardOnPersonalBoard(personalBoard);
		assertFalse(territories.getCards().equals(personalBoard.getPersonalTerritories().getCards()));
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		territories.getCards().add(territory);
		territories.getCards().add(territory2);
		territory.setCardOnPersonalBoard(personalBoard);
		assertFalse(territories.getCards().equals(personalBoard.getPersonalTerritories().getCards()));
	}
}
