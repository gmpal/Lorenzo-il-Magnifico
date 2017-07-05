package it.polimi.ingsw.GC_24.modeltest.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Ventures;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalVentures;

public class TestVentures {

	Ventures venture;
	PersonalVentures ventures;
	Ventures ventures2;
	Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		venture = new Ventures("Venture", "Venture", null, null, null, null, null, null, 3);
		ventures = new PersonalVentures();
		ventures2 = new Ventures("Venture2", "Venture", null, null, null, null, null, null, 3);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() throws Exception {
		ventures.getCards().add(venture);
		venture.setCardOnPersonalBoard(player.getMyBoard());
		assertEquals(ventures.getCards(), player.getMyBoard().getPersonalVentures().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		ventures.getCards().add(venture);
		venture.setCardOnPersonalBoard(player.getMyBoard());
		ventures2.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(ventures.getCards().equals(player.getMyBoard().getPersonalVentures().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		ventures.getCards().add(venture);
		ventures.getCards().add(ventures2);
		venture.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(ventures.getCards().equals(player.getMyBoard().getPersonalVentures().getCards()));
	}
}
