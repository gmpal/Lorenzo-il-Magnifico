package it.polimi.ingsw.GC_24.personalboardtest;

import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;


public abstract class TestPersonalCards {
	
	protected Player player = new Player("Giorgia", PlayerColour.RED);
	protected PersonalBoard personalBoard = new PersonalBoard(player); 
	
	@Test
	public abstract void testFindCardsInPersonalBoard();
	
	@Test
	public abstract void testFindCardsInPersonalBoardFalse1();
	
	@Test
	public abstract void testFindCardsInPersonalBoardFalse2();
}
