package it.polimi.ingsw.GC_24.personalboardtest;

import static org.junit.Assert.*;
import org.junit.Before;
import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.personalboard.PersonalCharacters;


public class TestPersonalCharacters extends TestPersonalCards {
	
	Characters character1;
	Characters character2;
	PersonalCharacters characters;
	PersonalCharacters charactersexpected;	

	@Before
	public void setUp() throws Exception {
		character1 = new Characters("Character1", false, false, false, "Character", null, null, null, 1, null);
		character2 = new Characters("Character2", false, false, false, "Character", null, null, null, 2, null);
		characters = new PersonalCharacters();
		charactersexpected = new PersonalCharacters();
	}

	@Override
	public void testFindCardsInPersonalBoard() {
		character1.setCardOnPersonalBoard(personalBoard);
		character2.setCardOnPersonalBoard(personalBoard);
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertEquals(charactersexpected.getCards(), characters.findCardsInPersonalBoard(personalBoard).getCards());
	}
	
	@Override
	public void testFindCardsInPersonalBoardFalse1() {
		character1.setCardOnPersonalBoard(personalBoard);
		character2.setCardOnPersonalBoard(personalBoard);
		charactersexpected.getCards().add(character1);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(personalBoard).getCards()));
	}
	
	@Override
	public void testFindCardsInPersonalBoardFalse2() {
		character1.setCardOnPersonalBoard(personalBoard);
		charactersexpected.getCards().add(character1);
		charactersexpected.getCards().add(character2);
		assertFalse(charactersexpected.getCards().equals(characters.findCardsInPersonalBoard(personalBoard).getCards()));
	}
}
