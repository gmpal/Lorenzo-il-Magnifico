package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.personalboard.PersonalVentures;

public class TestVentures extends TestDevelopment {

	Ventures venture;
	PersonalVentures ventures;
	Ventures ventures2;
	
	@Before
	public void setUp() throws Exception {
		venture = new Ventures("Venture", false, false, false, null,null);
		ventures = new PersonalVentures();
		ventures2 = new Ventures("Venture2", false, false, false, null,null);
	}
	
	@Override
	public void testSetCardOnPersonalBoard() throws Exception {
		ventures.getCards().add(venture);
		venture.setCardOnPersonalBoard(personalBoard);
		assertEquals(ventures.getCards(), personalBoard.getPersonalVentures().getCards());
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		ventures.getCards().add(venture);
		venture.setCardOnPersonalBoard(personalBoard);
		ventures2.setCardOnPersonalBoard(personalBoard);
		assertFalse(ventures.getCards().equals(personalBoard.getPersonalVentures().getCards()));
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		ventures.getCards().add(venture);
		ventures.getCards().add(ventures2);
		venture.setCardOnPersonalBoard(personalBoard);
		assertFalse(ventures.getCards().equals(personalBoard.getPersonalVentures().getCards()));
	}
}
