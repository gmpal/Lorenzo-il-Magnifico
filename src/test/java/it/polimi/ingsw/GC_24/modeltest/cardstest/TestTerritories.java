package it.polimi.ingsw.GC_24.modeltest.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalTerritories;

public class TestTerritories {

	Territories territory;
	PersonalTerritories territories;
	Territories territory2;
	Player player;
		
	@Before
	public void setUp() {
		player = new Player("Giorgia", PlayerColour.RED);
		territory = new Territories("Territory", null, 0, "Territory", null, null, null, null, 2);
		territories = new PersonalTerritories();
		territory2 = new Territories("Territory2", null, 0, "Territory", null, null, null, null, 1);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() {
		territories.getCards().add(territory);
		territory.setCardOnPersonalBoard(player.getMyBoard());
		assertEquals(territories.getCards(), player.getMyBoard().getPersonalTerritories().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() {
		territories.getCards().add(territory);
		territory.setCardOnPersonalBoard(player.getMyBoard());
		territory2.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(territories.getCards().equals(player.getMyBoard().getPersonalTerritories().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() {
		territories.getCards().add(territory);
		territories.getCards().add(territory2);
		territory.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(territories.getCards().equals(player.getMyBoard().getPersonalTerritories().getCards()));
	}
}
