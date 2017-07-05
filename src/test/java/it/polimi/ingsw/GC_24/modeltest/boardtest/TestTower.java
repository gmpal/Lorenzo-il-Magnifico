package it.polimi.ingsw.GC_24.modeltest.boardtest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.model.board.Tower;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;

public class TestTower {

	Tower tower;
	Tower tower2;
	Territories territory;
	Territories territory2;
	
	@Before
	public void setUp() throws Exception {
		tower = new Tower("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerTerritories.json");
		tower2 = new Tower("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerTerritories.json");
		territory = new Territories("Territory", 0, "Territory", null, null, null, null, 2);
		territory2 = new Territories("Territory", 1, "Territory", null, null, null, null, 3);
	}

	@Test
	public void testPutCardInFirstEmptyPlace() throws Exception {
		TowerPlace p = (TowerPlace)tower.getPlacesArray().get(0);
		p.setCorrespondingCard(territory);
		TowerPlace p1 = (TowerPlace)tower2.getPlacesArray().get(0);
		p1.setCorrespondingCard(territory);
		TowerPlace p2 = (TowerPlace)tower2.getPlacesArray().get(1);
		p2.setCorrespondingCard(territory2);
		tower.putCardInFirstEmptyPlace(territory2);
		assertEquals(tower2.getPlacesArray(), tower.getPlacesArray());
	}
}
