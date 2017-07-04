package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.model.cards.Buildings;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.cards.Leader;
import it.polimi.ingsw.GC_24.model.cards.Requirements;
import it.polimi.ingsw.GC_24.model.cards.Territories;
import it.polimi.ingsw.GC_24.model.cards.Ventures;
import it.polimi.ingsw.GC_24.model.values.*;

public class TestController {

	Controller controller;
	Model game;
	Player player;
	Player player2;
	Player player3;
	List<Player> temporaryTurn;
	List<Player> temporaryTurnExpected;
	List<Player> players;
	List<Integer> militaryPoints;
	VictoryPoint vc;
	Characters character;
	Buildings building;
	Territories territory;
	Ventures venture1;
	Ventures venture2;
	Leader leader1;
	Leader leader2;
	Leader leader3;
	SetOfValues set;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		player = new Player(1);
		player2 = new Player(2);
		player3 = new Player(3);
		players.add(player);
		players.add(player2);
		players.add(player3);
		game = new Model(1);
		game.setPlayers(players);
		game.getCorrespondingValueFromFile();
		controller = new Controller(game);
		controller.setPlayerTurn(players);
		temporaryTurn = new ArrayList<>();
		temporaryTurnExpected = new ArrayList<>();
		militaryPoints = new ArrayList<>();
		vc = new VictoryPoint(0);
		set = new SetOfValues();
		set.getCoins().setQuantity(2);
		character = new Characters("Character", "Character", null, null, null, null, 1);
		venture1 = new Ventures("Venture", "Venture", null, null, new VictoryPoint(5), null, null, null, 3);
		venture2 = new Ventures("Venture2", "Venture", null, null, new VictoryPoint(3), null, null, null, 3);
		territory = new Territories("Territory", 0, "Territory", null, null, null, null, 2);
		building = new Buildings("Building", 0, "Building", null, null, null, null, null, 1);
		leader1 = new Leader("Leader1", new Requirements(set, 2, 5, 3, 6), null, null, null, true);
		leader2 = new Leader("Leader2", new Requirements(new SetOfValues(), 0, 1, 1, 2), null, null, null, true);
		leader3 = new Leader("Leader3", null, null, null, null, false);
	}

	@Test
	public void testUpdateListOfPlayerTurn() {
		temporaryTurn.add(player3);
		temporaryTurn.add(player);
		controller.updateListOfPlayerTurn(temporaryTurn);
		temporaryTurnExpected.add(player3);
		temporaryTurnExpected.add(player);
		temporaryTurnExpected.add(player2);
		assertEquals(temporaryTurnExpected, controller.getPlayerTurn());
	}

	@Test
	public void testAutoCompletePlayers() {
		players.get(1).setMyName(null);
		controller.autoCompletePlayers();
		assertEquals("Player_2", controller.getGame().getPlayers().get(1).getMyName());
	}

	@Test
	public void testConvertMilitaryPointsToVictoryPointsPlayer1() {
		player.getMyValues().getMilitaryPoints().setQuantity(6);
		player2.getMyValues().getMilitaryPoints().setQuantity(3);
		player3.getMyValues().getMilitaryPoints().setQuantity(10);
		militaryPoints.add(player3.getMyValues().getMilitaryPoints().getQuantity());
		militaryPoints.add(player.getMyValues().getMilitaryPoints().getQuantity());
		militaryPoints.add(player2.getMyValues().getMilitaryPoints().getQuantity());
		controller.convertMilitaryPointsToVictoryPoints(militaryPoints);
		vc.setQuantity(5);
		assertEquals(vc, player3.getMyValues().getVictoryPoints());
	}

	@Test
	public void testConvertMilitaryPointsToVictoryPointsPlayer2() {
		player.getMyValues().getMilitaryPoints().setQuantity(6);
		player2.getMyValues().getMilitaryPoints().setQuantity(3);
		player3.getMyValues().getMilitaryPoints().setQuantity(10);
		militaryPoints.add(player3.getMyValues().getMilitaryPoints().getQuantity());
		militaryPoints.add(player.getMyValues().getMilitaryPoints().getQuantity());
		militaryPoints.add(player2.getMyValues().getMilitaryPoints().getQuantity());
		controller.convertMilitaryPointsToVictoryPoints(militaryPoints);
		vc.setQuantity(2);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testGiveVictoryPoints() {
		character.setCardOnPersonalBoard(player.getMyBoard());
		venture1.setCardOnPersonalBoard(player.getMyBoard());
		venture2.setCardOnPersonalBoard(player.getMyBoard());
		player.getMyValues().getFaithPoints().setQuantity(4);
		player.getMyValues().getMilitaryPoints().setQuantity(6);
		player2.getMyValues().getMilitaryPoints().setQuantity(3);
		player3.getMyValues().getMilitaryPoints().setQuantity(10);
		controller.giveVictoryPoints();
		vc.setQuantity(15);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testCheckToActivateLeader1() {
		player.getMyBoard().getPersonalLeader().add(leader3);
		player.getMyBoard().getPersonalLeader().add(leader1);
		player.getMyBoard().getPersonalLeader().add(leader2);
		player.getMyBoard().getPersonalLeader().get(1).setInUse(true);
		player.getMyBoard().getPersonalLeader().get(2).setInUse(true);
		controller.checkToActivateLeader();
		assertFalse(player.getMyBoard().getPersonalLeader().get(1).isInUse());
	}

	@Test
	public void testCheckToActivateLeader2() {
		player.getMyBoard().getPersonalLeader().add(leader3);
		player.getMyBoard().getPersonalLeader().add(leader1);
		player.getMyBoard().getPersonalLeader().add(leader2);
		player.getMyBoard().getPersonalLeader().get(1).setInUse(true);
		player.getMyBoard().getPersonalLeader().get(2).setInUse(false);
		controller.checkToActivateLeader();
		assertFalse(player.getMyBoard().getPersonalLeader().get(2).isInUse());
	}

	@Test
	public void testVerifyAvailabilityLeader() {
		player.getMyBoard().getPersonalLeader().add(leader3);
		player.getMyBoard().getPersonalLeader().add(leader1);
		player.getMyBoard().getPersonalLeader().add(leader2);
		player.getMyBoard().getPersonalLeader().get(1).setInUse(true);
		player.getMyBoard().getPersonalLeader().get(2).setInUse(false);
		controller.setCurrentPlayer(player);
		assertEquals("ok", controller.verifyAvailabilityLeader(2, "ok"));
	}

	@Test
	public void testVerifyAvailabilityLeaderWrong() {
		player.getMyBoard().getPersonalLeader().add(leader3);
		player.getMyBoard().getPersonalLeader().add(leader1);
		player.getMyBoard().getPersonalLeader().add(leader2);
		player.getMyBoard().getPersonalLeader().get(1).setInUse(true);
		player.getMyBoard().getPersonalLeader().get(2).setInUse(false);
		controller.setCurrentPlayer(player);
		assertEquals("okThis card is already in use\n", controller.verifyAvailabilityLeader(1, "ok"));
	}

	@Test
	public void testVerifyRequirementsLeader() {
		character.setCardOnPersonalBoard(player.getMyBoard());
		building.setCardOnPersonalBoard(player.getMyBoard());
		venture1.setCardOnPersonalBoard(player.getMyBoard());
		venture2.setCardOnPersonalBoard(player.getMyBoard());
		player.getMyBoard().getPersonalLeader().add(leader3);
		player.getMyBoard().getPersonalLeader().add(leader1);
		player.getMyBoard().getPersonalLeader().add(leader2);
		player.getMyBoard().getPersonalLeader().get(1).setInUse(false);
		player.getMyBoard().getPersonalLeader().get(2).setInUse(false);
		controller.setCurrentPlayer(player);
		assertEquals("ok", controller.verifyRequirementsLeader(2, "ok"));
	}

	@Test
	public void testVerifyRequirementsLeaderWrong() {
		character.setCardOnPersonalBoard(player.getMyBoard());
		building.setCardOnPersonalBoard(player.getMyBoard());
		venture1.setCardOnPersonalBoard(player.getMyBoard());
		venture2.setCardOnPersonalBoard(player.getMyBoard());
		player.getMyBoard().getPersonalLeader().add(leader3);
		player.getMyBoard().getPersonalLeader().add(leader1);
		player.getMyBoard().getPersonalLeader().add(leader2);
		player.getMyBoard().getPersonalLeader().get(1).setInUse(false);
		player.getMyBoard().getPersonalLeader().get(2).setInUse(false);
		controller.setCurrentPlayer(player);
		assertEquals(
				"okYou don't have enough resources to activate this card!\nYou don't have enough Territories to activate this card!\nYou don't have enough Characters to activate this card!\nYou don't have enough Buildings to activate this card!\nYou don't have enough Ventures to activate this card!\n",
				controller.verifyRequirementsLeader(1, "ok"));
	}
}
