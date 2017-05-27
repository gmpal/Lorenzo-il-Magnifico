package it.polimi.ingsw.GC_24.cardstest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.personalboard.PersonalCharacters;

public class TestCharacters extends TestDevelopment{

	Characters character;
	PersonalCharacters characters;
	Characters characters2;
	
	@Before
	public void setUp() throws Exception {
		character = new Characters("Character", false, false, false, null);
		characters = new PersonalCharacters();
		characters2 = new Characters("Character2", false, false, false, null);
	}
	
	@Override
	public void testSetCardOnPersonalBoard() throws Exception {
		characters.getCards().add(character);
		character.setCardOnPersonalBoard(personalBoard);
		assertEquals(characters.getCards(), personalBoard.getPersonalCharacters().getCards());
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse1() throws Exception {
		characters.getCards().add(character);
		character.setCardOnPersonalBoard(personalBoard);
		characters2.setCardOnPersonalBoard(personalBoard);
		assertFalse(characters.getCards().equals(personalBoard.getPersonalCharacters().getCards()));
	}
	
	@Override
	public void testSetCardOnPersonalBoardFalse2() throws Exception {
		characters.getCards().add(character);
		characters.getCards().add(characters2);
		character.setCardOnPersonalBoard(personalBoard);
		assertFalse(characters.getCards().equals(personalBoard.getPersonalCharacters().getCards()));
	}

}
