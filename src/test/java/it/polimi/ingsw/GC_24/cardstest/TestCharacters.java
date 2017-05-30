package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalCharacters;

public class TestCharacters {

	Characters character;
	PersonalCharacters characters;
	Characters characters2;
	Player player;
	PersonalBoard personalBoard; 
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Giorgia", PlayerColour.RED);
		personalBoard = new PersonalBoard(player); 
		character = new Characters("Character", "Character", null, null, null, 1, null);
		characters = new PersonalCharacters();
		characters2 = new Characters("Character2", "Character", null, null, null, 2, null);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() throws Exception {
		characters.getCards().add(character);
		character.setCardOnPersonalBoard(personalBoard);
		assertEquals(characters.getCards(), personalBoard.getPersonalCharacters().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		characters.getCards().add(character);
		character.setCardOnPersonalBoard(personalBoard);
		characters2.setCardOnPersonalBoard(personalBoard);
		assertFalse(characters.getCards().equals(personalBoard.getPersonalCharacters().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		characters.getCards().add(character);
		characters.getCards().add(characters2);
		character.setCardOnPersonalBoard(personalBoard);
		assertFalse(characters.getCards().equals(personalBoard.getPersonalCharacters().getCards()));
	}

}
