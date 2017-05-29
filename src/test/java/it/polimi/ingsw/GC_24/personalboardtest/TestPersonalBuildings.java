package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalBuildings;

public class TestPersonalBuildings {
	
	Buildings building1;
	Buildings building2;
	PersonalBuildings buildings;
	PersonalBuildings buildingsexpected;
	Player player;
	PersonalBoard personalBoard; 

	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		personalBoard = new PersonalBoard(player);
		building1 = new Buildings("Building1", 0, "Building", null, null, null, 1, null);
		building2 = new Buildings("Building2", 0, "Building", null, null, null, 3, null);
		buildings = new PersonalBuildings();
		buildingsexpected = new PersonalBuildings();
	}

	@Test
	public void testFindCardsInPersonalBoard() {
		building1.setCardOnPersonalBoard(personalBoard);
		building2.setCardOnPersonalBoard(personalBoard);
		buildingsexpected.getCards().add(building1);
		buildingsexpected.getCards().add(building2);
		assertEquals(buildingsexpected.getCards(), buildings.findCardsInPersonalBoard(personalBoard).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() {
		building1.setCardOnPersonalBoard(personalBoard);
		building2.setCardOnPersonalBoard(personalBoard);
		buildingsexpected.getCards().add(building1);
		assertFalse(buildingsexpected.getCards().equals(buildings.findCardsInPersonalBoard(personalBoard).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() {
		building1.setCardOnPersonalBoard(personalBoard);
		buildingsexpected.getCards().add(building1);
		buildingsexpected.getCards().add(building2);
		assertFalse(buildingsexpected.getCards().equals(buildings.findCardsInPersonalBoard(personalBoard).getCards()));
	}
}
