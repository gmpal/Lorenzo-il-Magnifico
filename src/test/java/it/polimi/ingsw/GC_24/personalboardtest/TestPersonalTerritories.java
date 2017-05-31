package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalTerritories;

public class TestPersonalTerritories {
	
	Territories territory1;
	Territories territory2;
	PersonalTerritories territories;
	PersonalTerritories territoriesexpected;
	Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		territory1 = new Territories("Territory1", 0, "Territory", null, null, null, 2, null);
		territory2 = new Territories("Territory2", 0, "Territory", null, null, null, 3, null);
		territories = new PersonalTerritories();
		territoriesexpected = new PersonalTerritories();
	}

	@Test
	public void testFindCardsInPersonalBoard() {
		territory1.setCardOnPersonalBoard(player.getMyBoard());
		territory2.setCardOnPersonalBoard(player.getMyBoard());
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertEquals(territoriesexpected.getCards(), territories.findCardsInPersonalBoard(player.getMyBoard()).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() {
		territory1.setCardOnPersonalBoard(player.getMyBoard());
		territory2.setCardOnPersonalBoard(player.getMyBoard());
		territoriesexpected.getCards().add(territory1);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() {
		territory1.setCardOnPersonalBoard(player.getMyBoard());
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
}
