package it.polimi.ingsw.GC_24.modeltest.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalCharacters;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;


public class TestPersonalCharacters {
	
	Characters character1;
	Characters character2;
	PersonalCharacters characters;
	PersonalCharacters charactersexpected;	
	Player player;
	VictoryPoint victoryPoints;

	@Before
	public void setUp() {
		player = new Player("Giorgia", PlayerColour.RED);
		character1 = new Characters("Character", null, "Character", null, null, null, null, 1);
		character2 = new Characters("Character2", null, "Character", null, null, null, null, 2);
		characters = new PersonalCharacters();
		charactersexpected = new PersonalCharacters();
		victoryPoints = new VictoryPoint(0);
	}

	@Test
	public void testFindCardsInPersonalBoard() {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertEquals(charactersexpected.getCards(), characters.findCardsInPersonalBoard(player.getMyBoard()).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		charactersexpected.getCards().add(character1);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testConvertCardToVictoryPoints() {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		victoryPoints.setQuantity(2);
		assertEquals(victoryPoints, player.getMyBoard().getPersonalCharacters().convertCardToVictoryPoints());
	}
}
