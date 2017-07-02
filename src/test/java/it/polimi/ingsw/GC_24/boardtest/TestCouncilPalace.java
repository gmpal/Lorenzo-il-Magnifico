package it.polimi.ingsw.GC_24.boardtest;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.board.CouncilPalace;

public class TestCouncilPalace {
	
	List<Player> temporaryTurn;
	List<Player> temporaryTurnExpected;
	CouncilPalace council;
	Player player;
	Player player2;
	Player player3;
	
	@Before
	public void setUp() throws Exception {
		council = new CouncilPalace(3);
		temporaryTurn = new ArrayList<>();
		temporaryTurnExpected = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		player3 = new Player("Gian Marco", PlayerColour.YELLOW);
	}
	
	@Test
	public void testUpdateTurnNewPlayer() throws Exception {
		temporaryTurn.add(player);
		temporaryTurn.add(player2);
		temporaryTurnExpected.add(player);
		temporaryTurnExpected.add(player2);
		temporaryTurnExpected.add(player3);
		council.setTemporaryTurn(temporaryTurn);
		assertEquals(temporaryTurnExpected, council.updateTurn(player3));
	}
	
	@Test
	public void testUpdateTurnPlayerAlreadyInArray() throws Exception {
		temporaryTurnExpected.add(player);
		temporaryTurnExpected.add(player2);
		temporaryTurnExpected.add(player3);
		temporaryTurn.add(player);
		temporaryTurn.add(player2);
		temporaryTurn.add(player3);
		council.setTemporaryTurn(temporaryTurn);
		assertEquals(temporaryTurn, council.updateTurn(player3));
	}

}
