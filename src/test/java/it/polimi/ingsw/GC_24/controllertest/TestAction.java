package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.controller.Action;
import it.polimi.ingsw.GC_24.controller.ActionTower;
import it.polimi.ingsw.GC_24.controller.CouncilPalaceAction;
import it.polimi.ingsw.GC_24.effects.ChooseNewCard;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class TestAction {
	
	List<ImmediateEffect> immediateEffects;
	List<ImmediateEffect> immediateEffectsExpected;
	List<Player> players;
	Action action;
	Model game;
	Player player;
	Player player2;
	ImmediateEffect chooseNewCard;
	ImmediateEffect valueEffect;
	ImmediateEffect privilege;

	@Before
	public void setUp() throws Exception {
		immediateEffects = new ArrayList<>();
		immediateEffectsExpected = new ArrayList<>();
		players = new ArrayList<>();
		game = new Model(0);
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		players.add(player);
		players.add(player2);
		game.setModel(players);
		action = new CouncilPalaceAction(game, "1", "council", "1", "0");
		//chooseNewCard = new ChooseNewCard("ChooseNewCard", "personalVentures", 4, null);
		privilege = new CouncilPrivilege("CouncilPrivilege", 1);
		valueEffect = new ValueEffect("value");		
	}
	
	@Test
	public void testGiveValueEffect() throws Exception {
		immediateEffects.add(privilege);
		immediateEffects.add(valueEffect);
		immediateEffectsExpected.add(privilege);
		action.giveValueEffect(immediateEffects);
		assertEquals(immediateEffectsExpected, immediateEffects);
	}
	
	@Test
	public void testVerifyIfEnoughServants() throws Exception {
		action.verifyIfEnoughServants(answerToPlayer)
		assertEquals(immediateEffectsExpected, immediateEffects);
	}
	
}
