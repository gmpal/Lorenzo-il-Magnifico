package it.polimi.ingsw.GC_24.modeltest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.model.*;

public class TestModel {
	
	List<Player> players;
	Player player;
	Player player2;
	Model game;
	
	@Before
	public void setUp() throws Exception {
		players = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		players.add(player);
		players.add(player2);
		game = new Model(1);
		game.setPlayers(players);
		game.setGameState(State.WAITINGFORPLAYERTHREE);
	}
	
	@Test
	public void testGetMyValuesFromColour() throws Exception {
		assertEquals(player, game.getPlayerfromColour(PlayerColour.RED));
	}
	
	@Test
	public void testIncrementState() throws Exception {
		game.incrementState();
		assertEquals(State.WAITINGFORPLAYERFOUR, game.getGameState());
	}

}
