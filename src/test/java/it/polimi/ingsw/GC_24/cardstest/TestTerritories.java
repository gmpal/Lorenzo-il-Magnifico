package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalTerritories;

public class TestTerritories {

	Territories territory;
	PersonalTerritories territories;
	Territories territory2;
	Player player;
		
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		territory = new Territories("Territory", 0, "Territory", null, null, null, 2, null);
		territories = new PersonalTerritories();
		territory2 = new Territories("Territory2", 0, "Territory", null, null, null, 1, null);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() throws Exception {
		territories.getCards().add(territory);
		territory.setCardOnPersonalBoard(player.getMyBoard());
		assertEquals(territories.getCards(), player.getMyBoard().getPersonalTerritories().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		territories.getCards().add(territory);
		territory.setCardOnPersonalBoard(player.getMyBoard());
		territory2.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(territories.getCards().equals(player.getMyBoard().getPersonalTerritories().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		territories.getCards().add(territory);
		territories.getCards().add(territory2);
		territory.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(territories.getCards().equals(player.getMyBoard().getPersonalTerritories().getCards()));
	}
}
