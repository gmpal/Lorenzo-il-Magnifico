package it.polimi.ingsw.GC_24.controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_24.cards.Characters;
import it.polimi.ingsw.GC_24.cards.Leader;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.values.*;

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
	Characters character1;
	Ventures venture1;
	Ventures venture2;
	Leader leader1;
	Leader leader2;
	Leader leader3;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		player = new Player("Giorgia", PlayerColour.RED);
		player2 = new Player("Carlo", PlayerColour.GREEN);
		player3 = new Player("Gian Marco", PlayerColour.YELLOW);
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
		character1 = new Characters("Character", "Character", null, null, null, null, 1);
		venture1 = new Ventures("Venture", "Venture", null, null, new VictoryPoint(5), null, null, null, 3);
		venture2 = new Ventures("Venture2", "Venture", null, null, new VictoryPoint(3), null, null, null, 3);
		leader1 = new Leader("Leader1", null, null, null, null, true);
		leader2 = new Leader("Leader2", null, null, null, null, true);
		leader3 = new Leader("Leader1", null, null, null, null, false);
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
		character1.setCardOnPersonalBoard(player.getMyBoard());
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
}
