package it.polimi.ingsw.GC_24.personalboard;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.personalboard.PersonalTerritories;

public class TestPersonalTerritories extends TestPersonalCards {
	
	Territories territory1;
	Territories territory2;
	PersonalTerritories territories;
	PersonalTerritories territoriesexpected;	

	@Before
	public void setUp() throws Exception {
		territory1 = new Territories("Territory1", false, false, false, null,0);
		territory2 = new Territories("Territory2", false, false, false, null,0);
		territories = new PersonalTerritories();
		territoriesexpected = new PersonalTerritories();
	}

	@Override
	public void testFindCardsInPersonalBoard() {
		territory1.setCardOnPersonalBoard(personalBoard);
		territory2.setCardOnPersonalBoard(personalBoard);
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertEquals(territoriesexpected.getCards(), territories.findCardsInPersonalBoard(personalBoard).getCards());
	}
	
	@Override
	public void testFindCardsInPersonalBoardFalse1() {
		territory1.setCardOnPersonalBoard(personalBoard);
		territory2.setCardOnPersonalBoard(personalBoard);
		territoriesexpected.getCards().add(territory1);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(personalBoard).getCards()));
	}
	
	@Override
	public void testFindCardsInPersonalBoardFalse2() {
		territory1.setCardOnPersonalBoard(personalBoard);
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(personalBoard).getCards()));
	}
}
