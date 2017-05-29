package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalTerritories;

public class TestPersonalTerritories {
	
	Territories territory1;
	Territories territory2;
	PersonalTerritories territories;
	PersonalTerritories territoriesexpected;
	Player player;
	PersonalBoard personalBoard; 

	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		personalBoard = new PersonalBoard(player);
		territory1 = new Territories("Territory1", 0, "Territory", null, null, null, 2, null);
		territory2 = new Territories("Territory2", 0, "Territory", null, null, null, 3, null);
		territories = new PersonalTerritories();
		territoriesexpected = new PersonalTerritories();
	}

	@Test
	public void testFindCardsInPersonalBoard() {
		territory1.setCardOnPersonalBoard(personalBoard);
		territory2.setCardOnPersonalBoard(personalBoard);
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertEquals(territoriesexpected.getCards(), territories.findCardsInPersonalBoard(personalBoard).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() {
		territory1.setCardOnPersonalBoard(personalBoard);
		territory2.setCardOnPersonalBoard(personalBoard);
		territoriesexpected.getCards().add(territory1);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(personalBoard).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() {
		territory1.setCardOnPersonalBoard(personalBoard);
		territoriesexpected.getCards().add(territory1);
		territoriesexpected.getCards().add(territory2);
		assertFalse(territoriesexpected.getCards().equals(territories.findCardsInPersonalBoard(personalBoard).getCards()));
	}
}
