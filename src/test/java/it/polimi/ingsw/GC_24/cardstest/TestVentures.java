package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalVentures;

public class TestVentures {

	Ventures venture;
	PersonalVentures ventures;
	Ventures ventures2;
	Player player;
	PersonalBoard personalBoard; 
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		personalBoard = new PersonalBoard(player); 
		venture = new Ventures("Venture", "Venture", null, null, null, null, 3, null);
		ventures = new PersonalVentures();
		ventures2 = new Ventures("Venture2", "Venture", null, null, null, null, 3, null);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() throws Exception {
		ventures.getCards().add(venture);
		venture.setCardOnPersonalBoard(personalBoard);
		assertEquals(ventures.getCards(), personalBoard.getPersonalVentures().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		ventures.getCards().add(venture);
		venture.setCardOnPersonalBoard(personalBoard);
		ventures2.setCardOnPersonalBoard(personalBoard);
		assertFalse(ventures.getCards().equals(personalBoard.getPersonalVentures().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		ventures.getCards().add(venture);
		ventures.getCards().add(ventures2);
		venture.setCardOnPersonalBoard(personalBoard);
		assertFalse(ventures.getCards().equals(personalBoard.getPersonalVentures().getCards()));
	}
}
