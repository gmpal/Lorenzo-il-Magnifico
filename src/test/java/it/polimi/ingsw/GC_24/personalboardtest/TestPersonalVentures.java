package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.personalboard.PersonalVentures;

public class TestPersonalVentures extends TestPersonalCards {
	
	Ventures building1;
	Ventures building2;
	PersonalVentures buildings;
	PersonalVentures buildingsexpected;	

	@Before
	public void setUp() throws Exception {
		building1 = new Ventures("Venture1", false, false, false, null, null);
		building2 = new Ventures("Venture2", false, false, false, null, null);
		buildings = new PersonalVentures();
		buildingsexpected = new PersonalVentures();
	}

	@Override
	public void testFindCardsInPersonalBoard() {
		building1.setCardOnPersonalBoard(personalBoard);
		building2.setCardOnPersonalBoard(personalBoard);
		buildingsexpected.getCards().add(building1);
		buildingsexpected.getCards().add(building2);
		assertEquals(buildingsexpected.getCards(), buildings.findCardsInPersonalBoard(personalBoard).getCards());
	}
	
	@Override
	public void testFindCardsInPersonalBoardFalse1() {
		building1.setCardOnPersonalBoard(personalBoard);
		building2.setCardOnPersonalBoard(personalBoard);
		buildingsexpected.getCards().add(building1);
		assertFalse(buildingsexpected.getCards().equals(buildings.findCardsInPersonalBoard(personalBoard).getCards()));
	}
	
	@Override
	public void testFindCardsInPersonalBoardFalse2() {
		building1.setCardOnPersonalBoard(personalBoard);
		buildingsexpected.getCards().add(building1);
		buildingsexpected.getCards().add(building2);
		assertFalse(buildingsexpected.getCards().equals(buildings.findCardsInPersonalBoard(personalBoard).getCards()));
	}
}
