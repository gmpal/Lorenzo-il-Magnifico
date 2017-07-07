package it.polimi.ingsw.GC_24.modeltest.cardstest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.cards.Deck;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;

public class TestDeck {
	Deck d;
	Player p1, p2;
	Model game;
	List<Player> players;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		p1 = new Player("Giorgia", PlayerColour.RED);
		p2 = new Player("Carlo", PlayerColour.RED);
		players.add(p1);
		players.add(p2);
		game = new Model(1);
		game.setModel(players);
		d = new Deck();
	}

	@Test
	public void testDealCards() {
		d.dealCards(game.getBoard(), 1);
		TowerPlace t = (TowerPlace) game.getBoard().getTowerTerritories().getPlacesArray().get(0);
		assertTrue(t.getCorrespondingCard() != null);
	}

}
