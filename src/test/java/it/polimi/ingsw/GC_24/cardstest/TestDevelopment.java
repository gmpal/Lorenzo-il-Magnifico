package it.polimi.ingsw.GC_24.cardstest;


import org.junit.Test;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;


public abstract class TestDevelopment {

	protected Player player = new Player("Giorgia", PlayerColour.RED);
	protected PersonalBoard personalBoard = new PersonalBoard(player); 
	
	@Test
	public abstract void testSetCardOnPersonalBoard() throws Exception;
	
	@Test
	public abstract void testSetCardOnPersonalBoardFalse1() throws Exception 
	;
	
	@Test
	public abstract void testSetCardOnPersonalBoardFalse2() throws Exception 
	; 
}
