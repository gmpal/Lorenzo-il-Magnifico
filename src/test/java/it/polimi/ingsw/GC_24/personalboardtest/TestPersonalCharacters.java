package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalCharacters;


public class TestPersonalCharacters {
	
	Characters character1;
	Characters character2;
	PersonalCharacters characters;
	PersonalCharacters charactersexpected;	
	Player player;
	PersonalBoard personalBoard; 

	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		personalBoard = new PersonalBoard(player);
		character1 = new Characters("Character1", "Character", null, null, null, 1, null);
		character2 = new Characters("Character2", "Character", null, null, null, 2, null);
		characters = new PersonalCharacters();
		charactersexpected = new PersonalCharacters();
	}

	@Test
	public void testFindCardsInPersonalBoard() {
		character1.setCardOnPersonalBoard(personalBoard);
		character2.setCardOnPersonalBoard(personalBoard);
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertEquals(charactersexpected.getCards(), characters.findCardsInPersonalBoard(personalBoard).getCards());
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse1() {
		character1.setCardOnPersonalBoard(personalBoard);
		character2.setCardOnPersonalBoard(personalBoard);
		charactersexpected.getCards().add(character1);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(personalBoard).getCards()));
	}
	
	@Test
	public void testFindCardsInPersonalBoardFalse2() {
		character1.setCardOnPersonalBoard(personalBoard);
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(personalBoard).getCards()));
	}
}
