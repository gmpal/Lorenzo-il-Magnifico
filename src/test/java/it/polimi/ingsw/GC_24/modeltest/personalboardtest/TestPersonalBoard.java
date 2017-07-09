package it.polimi.ingsw.GC_24.modeltest.personalboardtest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Territories;

public class TestPersonalBoard {

	Model game;
	Player player;
	Player player2;
	Player player3;
	List<Player> players;
	Territories territory;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.YELLOW);
		player2 = new Player("Carlo", PlayerColour.RED);
		player3 = new Player("Gian Marco", PlayerColour.GREEN);
		players.add(player);
		players.add(player2);
		players.add(player3);
		game = new Model(1);
		game.setModel(players);
		game.getCards().dealCards(game.getBoard(), 1);
		territory = new Territories("Territory", null, 0, "Territory", null, null, null, null, 2);
	}

	@Test
	public void testUrlPersonalBoard() {
		player.getMyBoard().getPersonalTerritories().setCards(territory);
		assertEquals(player.getMyBoard().urlPersonalBoard().size(), 28);
	}
	
	@Test
	public void testUrlPersonalBoard1() {
		player.getMyBoard().getPersonalTerritories().setCards(territory);
		assertEquals(player.getMyBoard().urlPersonalBoard().size(), 28);
	}
}
