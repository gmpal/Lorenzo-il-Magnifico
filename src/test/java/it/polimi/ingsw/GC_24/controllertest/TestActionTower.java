package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.controller.ActionTower;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.effects.NoValueEffectFromTowerPlace;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

public class TestActionTower {

	Player p1;
	ActionTower actionTower;
	Model game;
	List<Player> players;
	
	@Before
	public void setUp() {
		players=new ArrayList<>();
		p1=new Player("Giorgia", PlayerColour.RED);
		players.add(p1);
		game=new Model(1);
		game.setModel(players);
		actionTower=new ActionTower(game, "1", "territories", "1", "0", new SetOfValues(), new SetOfValues());
	}
	
	
	@Test
	public void testNoValueEffect() {
		p1.getActivePermanentEffects().add(new NoValueEffectFromTowerPlace("noValueEffectFromTowerPlace"));
		assertTrue(actionTower.isThereNoValueEffect());
		p1.getActivePermanentEffects().remove(0);
		assertFalse(actionTower.isThereNoValueEffect());
		assertEquals((new NoValueEffectFromTowerPlace("noValueEffectFromTowerPlace")).toString(),"No Value Effect From Tower Place: when you place a family member on a tower place you won't get the values anymore ");
	}
}
