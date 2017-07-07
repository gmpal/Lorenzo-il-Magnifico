package it.polimi.ingsw.GC_24.modeltest.boardtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.board.Board;
import it.polimi.ingsw.GC_24.model.board.Tower;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;

public class TestTower {

	Model game;
	Player player;
	Player player2;
	Player player3;
	List<Player> players;

	Tower tower;
	Tower tower2;
	Territories territory;
	Territories territory2;
	Board b;
	List<String> urls;

	@Before
	public void setUp() throws Exception {
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
		tower = new Tower("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerTerritories.json");
		tower2 = new Tower("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerTerritories.json");
		territory = new Territories("Territory", null, 0, "Territory", null, null, null, null, 2);
		territory2 = new Territories("Territory", null, 1, "Territory", null, null, null, null, 3);
		urls = new ArrayList<>();
	}

	@Test
	public void testPutCardInFirstEmptyPlace() throws Exception {
		TowerPlace p = (TowerPlace) tower.getPlacesArray().get(0);
		p.setCorrespondingCard(territory);
		TowerPlace p1 = (TowerPlace) tower2.getPlacesArray().get(0);
		p1.setCorrespondingCard(territory);
		TowerPlace p2 = (TowerPlace) tower2.getPlacesArray().get(1);
		p2.setCorrespondingCard(territory2);
		tower.putCardInFirstEmptyPlace(territory2);
		assertEquals(tower2.getPlacesArray(), tower.getPlacesArray());
	}

	@Test
	public void testAllUrl() {
		game.getBoard().getTowerTerritories().getPlacesArray().get(0).setFamMemberOnPlace(player.getMyFamily().getMember1());
		assertEquals(game.getBoard().allUrl().size(), 16);
	}
}
