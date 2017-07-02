package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Ventures;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalVentures;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

public class TestPersonalVentures {
	
	Ventures venture1;
	Ventures venture2;
	PersonalVentures ventures;
	PersonalVentures venturesexpected;	
	Player player;
	VictoryPoint victoryPoints;

	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		venture1 = new Ventures("Venture", "Venture", null, null, new VictoryPoint(5), null, null, null, 3);
		venture2 = new Ventures("Venture2", "Venture", null, null, new VictoryPoint(3), null, null, null, 3);
		ventures = new PersonalVentures();
		venturesexpected = new PersonalVentures();
		victoryPoints = new VictoryPoint(0);
	}

	@Test
	public void testFindCardsInPersonalBoard() throws Exception {
		venture1.setCardOnPersonalBoard(player.getMyBoard());
		venture2.setCardOnPersonalBoard(player.getMyBoard());
		venturesexpected.getCards().add(venture1);
		venturesexpected.getCards().add(venture2);
		assertEquals(venturesexpected.getCards(), ventures.findCardsInPersonalBoard(player.getMyBoard()).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() throws Exception {
		venture1.setCardOnPersonalBoard(player.getMyBoard());
		venture2.setCardOnPersonalBoard(player.getMyBoard());
		venturesexpected.getCards().add(venture1);
		assertFalse(venturesexpected.getCards().equals(ventures.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() throws Exception {
		venture1.setCardOnPersonalBoard(player.getMyBoard());
		venturesexpected.getCards().add(venture1);
		venturesexpected.getCards().add(venture2);
		assertFalse(venturesexpected.getCards().equals(ventures.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testConvertCardToVictoryPoints() throws Exception {
		venture1.setCardOnPersonalBoard(player.getMyBoard());
		venture2.setCardOnPersonalBoard(player.getMyBoard());
		victoryPoints.setQuantity(8);
		assertEquals(victoryPoints, player.getMyBoard().getPersonalVentures().convertCardToVictoryPoints());
	}
}
