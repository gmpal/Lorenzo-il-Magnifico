package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.controller.MarketAction;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.effects.immediate.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.NoMarketAvailability;

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
	NoMarketAvailability nm;
	
	
	@Before
	public void setUp() {
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
		nm = new NoMarketAvailability("NoMarketAvailability");
	}
	
	@Test
	public void testVerify() {
		assertEquals("ok", market.verify());
	}
	
	@Test
	public void testVerifyWrong() {
		player.getMyFamily().getMember1().setMemberValue(0);
		player.getActivePermanentEffects().add(nm);
		player.getMyFamily().getMember1().setAvailable(false);
		assertEquals("Answer: \nYou have not used enough servants for this place. Please choose another place\n"
				+ "Sorry, this familiar is not available! \nSorry, place not available!\n", market.verify());
	}
	
	@Test
	public void testVerifyWrong2() {
		player.getMyFamily().getMember1().setMemberValue(0);
		player.getMyFamily().getMember1().setAvailable(false);
		market.getPlace().setAvailable(false);
		assertEquals("Answer: \nYou have not used enough servants for this place. Please choose another place\n"
				+ "Sorry, this familiar is not available! \nSorry, place not available!\n", market.verify());
	}
	
	@Test
	public void testTakePrivilegeEffectFromMarketPlace() {
		immediateEffects.add(new CouncilPrivilege("Council", 2));
		market.takePrivilegeEffectFromMarketPlace();
		assertEquals(immediateEffects, market.getImmediateEffects());
	}
	
	@Test
	public void testTakePrivilegeEffectFromMarketPlaceWrong() {
		market2.takePrivilegeEffectFromMarketPlace();
		assertEquals(immediateEffects, market2.getImmediateEffects());
	}
}
