package it.polimi.ingsw.GC_24.modeltest.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalCharacters;

public class TestCharacters {

	Characters character;
	PersonalCharacters characters;
	Characters characters2;
	Player player;
	
	@Before
	public void setUp() {
		player = new Player("Giorgia", PlayerColour.RED);
		character = new Characters("Character", null, "Character", null, null, null, null, 1);
		characters = new PersonalCharacters();
		characters2 = new Characters("Character2", null, "Character", null, null, null, null, 2);
	}
	
	@Test
	public void testSetCardOnPersonalBoard() {
		characters.getCards().add(character);
		character.setCardOnPersonalBoard(player.getMyBoard());
		assertEquals(characters.getCards(), player.getMyBoard().getPersonalCharacters().getCards());
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse1() {
		characters.getCards().add(character);
		character.setCardOnPersonalBoard(player.getMyBoard());
		characters2.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(characters.getCards().equals(player.getMyBoard().getPersonalCharacters().getCards()));
	}
	
	@Test
	public void testSetCardOnPersonalBoardFalse2() {
		characters.getCards().add(character);
		characters.getCards().add(characters2);
		character.setCardOnPersonalBoard(player.getMyBoard());
		assertFalse(characters.getCards().equals(player.getMyBoard().getPersonalCharacters().getCards()));
	}

}
