package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.controller.*;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.values.SetOfValues;


public class TestActionFactory {
	
	Action council;
	Action harvest;
	Action production;
	Action market;
	Action tower;
	ActionFactory actionFactory;
	Model game;
	Player player;
	Player player2;
	List<Player> players;

	@Before
	public void setUp() throws Exception {
		game = new Model(1);
		players = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		players.add(player);
		players.add(player2);
		game.setModel(players);
		council = new CouncilPalaceAction(game, "1", "council", "1", "0");
		harvest = new HarvestAction(game, "2", "harvest", "1", "4");
		production = new ProductionAction(game, "1", "production", "1", "0");
		market = new MarketAction(game, "1", "market", "1", "0");
		tower = new ActionTower(game, "3", "characters", "1", "2", new SetOfValues(), new SetOfValues());
		actionFactory = new ActionFactory();
	}
	
	@Test
	public void testMakeActionCouncil() throws Exception {
		assertEquals(council, actionFactory.makeAction(game, "1", "council", "1", "0", new SetOfValues(), new SetOfValues()));
	}
	
	@Test
	public void testMakeActionHarvest() throws Exception {
		assertEquals(harvest, actionFactory.makeAction(game, "2", "harvest", "1", "4", new SetOfValues(), new SetOfValues()));
	}
	
	@Test
	public void testMakeActionProduction() throws Exception {
		assertEquals(production, actionFactory.makeAction(game, "1", "production", "1", "0", new SetOfValues(), new SetOfValues()));
	}
	
	@Test
	public void testMakeActionMarket() throws Exception {
		assertEquals(market, actionFactory.makeAction(game, "1", "market", "1", "0", new SetOfValues(), new SetOfValues()));
	}
	
	@Test
	public void testMakeActionMarketTower() throws Exception {
		assertEquals(tower, actionFactory.makeAction(game, "3", "characters", "1", "2", new SetOfValues(), new SetOfValues()));
	}
}
