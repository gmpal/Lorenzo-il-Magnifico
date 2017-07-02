package it.polimi.ingsw.GC_24.personalboardtest;

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
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		character1 = new Characters("Character", "Character", null, null, null, null, 1);
		character2 = new Characters("Character2", "Character", null, null, null, null, 2);
		characters = new PersonalCharacters();
		charactersexpected = new PersonalCharacters();
		victoryPoints = new VictoryPoint(0);
	}

	@Test
	public void testFindCardsInPersonalBoard() throws Exception {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertEquals(charactersexpected.getCards(), characters.findCardsInPersonalBoard(player.getMyBoard()).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() throws Exception {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		charactersexpected.getCards().add(character1);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() throws Exception {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(player.getMyBoard()).getCards()));
	}
	
	@Test
	public void testConvertCardToVictoryPoints() throws Exception {
		character1.setCardOnPersonalBoard(player.getMyBoard());
		character2.setCardOnPersonalBoard(player.getMyBoard());
		victoryPoints.setQuantity(2);
		assertEquals(victoryPoints, player.getMyBoard().getPersonalCharacters().convertCardToVictoryPoints());
	}
}
