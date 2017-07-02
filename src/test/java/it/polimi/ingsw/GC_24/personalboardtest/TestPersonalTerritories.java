package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalTerritories;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

public class TestPersonalTerritories {
	
	Territories territory1;
	Territories territory2;
	Territories territory3;
	PersonalTerritories territories;
	PersonalTerritories territoriesexpected;
	Player player;
	VictoryPoint victoryPoints;

	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		territory1 = new Territories("Territory", 0, "Territory", null, null, null, null, 2);
		territory2 = new Territories("Territory2", 0, "Territory", null, null, null, null, 1);
		territory3 = new Territories("Territory3", 0, "Territory", null, null, null, null, 3);
		territories = new PersonalTerritories();
		territoriesexpected = new PersonalTerritories();
		victoryPoints = new VictoryPoint(0);
	}

	@Test
	public void testFindCardsInPersonalBoard() throws Exception {
		territory1.setCardOnPersonalBoard(player.getMyBoard());
		territory2.setCardOnPersonalBoard(player.getMyBoard());
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertEquals(territoriesexpected.getCards(), territories.findCardsInPersonalBoard(player.getMyBoard()).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() throws Exception {
		territory1.setCardOnPersonalBoard(player.getMyBoard());
		territory2.setCardOnPersonalBoard(player.getMyBoard());
		territoriesexpected.getCards().add(territory1);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() throws Exception {
		territory1.setCardOnPersonalBoard(player.getMyBoard());
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testConvertCardToVictoryPoints() throws Exception {
		territory1.setCardOnPersonalBoard(player.getMyBoard());
		territory2.setCardOnPersonalBoard(player.getMyBoard());
		territory3.setCardOnPersonalBoard(player.getMyBoard());
		victoryPoints.setQuantity(1);
		assertEquals(victoryPoints, player.getMyBoard().getPersonalTerritories().convertCardToVictoryPoints());
	}
}
