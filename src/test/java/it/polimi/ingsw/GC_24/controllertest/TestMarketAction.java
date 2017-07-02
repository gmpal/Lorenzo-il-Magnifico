package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.controller.MarketAction;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;

public class TestMarketAction {
	
	MarketAction market;
	MarketAction market2;
	Model game;
	Player player;
	Player player2;
	Player player3;
	Player player4;
	List<Player> players;
	List<ImmediateEffect> immediateEffects;
	
	@Before
	public void setUp() throws Exception {
		game = new Model(1);
		players = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		player3 = new Player("Gian Marco", PlayerColour.YELLOW);
		player4 = new Player("Elisa", PlayerColour.BLUE);
		players.add(player);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		game.setModel(players);
		market = new MarketAction(game, "1", "market", "4", "0");
		market2 = new MarketAction(game, "1", "market", "2", "0");
		immediateEffects = new ArrayList<>();
	}
	
	@Test
	public void testTakePrivilegeEffectFromMarketPlace() throws Exception {
		immediateEffects.add(new CouncilPrivilege("Council", 2));
		market.takePrivilegeEffectFromMarketPlace();
		assertEquals(immediateEffects, market.getImmediateEffects());
	}
	
	@Test
	public void testTakePrivilegeEffectFromMarketPlaceWrong() throws Exception {
		market2.takePrivilegeEffectFromMarketPlace();
		assertEquals(immediateEffects, market2.getImmediateEffects());
	}
}
