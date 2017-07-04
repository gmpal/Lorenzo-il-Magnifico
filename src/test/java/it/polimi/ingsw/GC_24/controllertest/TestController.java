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
import it.polimi.ingsw.GC_24.model.cards.*;
import it.polimi.ingsw.GC_24.model.effects.NoVictoryPointsFromCard;
import it.polimi.ingsw.GC_24.model.effects.SubVicrotyPointsFromSetOfValue;
import it.polimi.ingsw.GC_24.model.personalboard.*;
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
	List<Excommunication> excommunicationDeck;
	VictoryPoint vc;
	Characters character1;
	Buildings building1;
	Buildings building2;
	Territories territory1;
	Territories territory2;
	Territories territory3;
	Characters character;
	Buildings building;
	Territories territory;
	Ventures venture1;
	Ventures venture2;
	Leader leader1;
	Leader leader2;
	Leader leader3;
	Excommunication ex1, ex2, ex3, ex4, ex5, ex6, ex7;
	SetOfValues setEx = new SetOfValues();
	SetOfValues setEx5 = new SetOfValues();
	SetOfValues setEx7 = new SetOfValues();
	SetOfValues costB1 = new SetOfValues();
	SetOfValues costB2 = new SetOfValues();
	SetOfValues set;
	List<Player> playerTurn = new ArrayList<>();
	int cardIndex = 0;

	@Before
	public void setUp() {
		players = new ArrayList<>();
		player = new Player(1);
		player2 = new Player(2);
		player3 = new Player(3);
		setEx.setMilitaryPoints(new MilitaryPoint(1));
		setEx5.setVictoryPoints(new VictoryPoint(5));
		setEx7.setWoods(new Wood(1));
		setEx7.setStones(new Stone(1));
		costB1.setCoins(new Coin(2));
		costB1.setWoods(new Wood(2));
		costB2.setStones(new Stone(3));
		costB2.setWoods(new Wood(1));
		playerTurn.add(player);
		playerTurn.add(player2);
		playerTurn.add(player3);
		ex1 = new Excommunication(null,
				new NoVictoryPointsFromCard("noVictoryPointsFromTerritories", new PersonalTerritories()), 1,
				new FaithPoint(3));
		ex2 = new Excommunication(null,
				new NoVictoryPointsFromCard("noVictoryPointsFromCharacters", new PersonalCharacters()), 3,
				new FaithPoint(5));
		ex3 = new Excommunication(null,
				new NoVictoryPointsFromCard("noVictoryPointsFromVentures", new PersonalVentures()), 3,
				new FaithPoint(5));
		ex4 = new Excommunication(null,
				new SubVicrotyPointsFromSetOfValue("subMilitaryPoints", setEx, new VictoryPoint(1)), 3,
				new FaithPoint(5));
		ex5 = new Excommunication(null,
				new SubVicrotyPointsFromSetOfValue("subVictoryPoints", setEx5, new VictoryPoint(1)), 3,
				new FaithPoint(5));
		ex6 = new Excommunication(null,
				new SubVicrotyPointsFromSetOfValue("subResourcesPoints", null, new VictoryPoint(1)), 3,
				new FaithPoint(5));
		ex7 = new Excommunication(null,
				new SubVicrotyPointsFromSetOfValue("subCostBuildings", setEx7, new VictoryPoint(1)), 3,
				new FaithPoint(5));
		territory1 = new Territories("territory1", 1, null, null, null, null, null, 1);
		territory2 = new Territories("territory1", 1, null, null, null, null, null, 2);
		territory3 = new Territories("territory1", 1, null, null, null, null, null, 3);
		building1 = new Buildings("building1", 1, null, costB1, null, null, null, null, 1);
		building2 = new Buildings("building2", 1, null, costB2, null, null, null, null, 1);
		players.add(player);
		players.add(player2);
		players.add(player3);
		game = new Model(1);
		game.setPlayers(players);
		game.createExcommunicationDeck();
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
	public void testGiveVictoryPointsWithFinalExcommunicationTerritories() {
		player.setMyValues(new SetOfValues());
		player.getMyValues().setFaithPoints(new FaithPoint(4));
		player.getMyBoard().getPersonalTerritories().setCards(territory1);
		player.getMyBoard().getPersonalTerritories().setCards(territory2);
		player.getMyBoard().getPersonalTerritories().setCards(territory3);
		player.getMyBoard().getPersonalCharacters().setCards(character1);
		player.getMyBoard().getPersonalVentures().setCards(venture1);
		player.getMyBoard().getPersonalVentures().setCards(venture2);
		player.getMyValues().setMilitaryPoints(new MilitaryPoint(3));
		player2.getMyValues().setMilitaryPoints(new MilitaryPoint(6));
		player.setLastExcommunication(true);
		game.getExcommunicationDeck().set(2, ex1);
		controller.giveVictoryPoints();
		vc.setQuantity(15);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testGiveVictoryPointsWithFinalExcommunicationCharacters() {
		player.setMyValues(new SetOfValues());
		player.getMyValues().setFaithPoints(new FaithPoint(4));
		player.getMyBoard().getPersonalTerritories().setCards(territory1);
		player.getMyBoard().getPersonalTerritories().setCards(territory2);
		player.getMyBoard().getPersonalTerritories().setCards(territory3);
		player.getMyBoard().getPersonalCharacters().setCards(character1);
		player.getMyBoard().getPersonalVentures().setCards(venture1);
		player.getMyBoard().getPersonalVentures().setCards(venture2);
		player.getMyValues().setMilitaryPoints(new MilitaryPoint(6));
		player2.getMyValues().setMilitaryPoints(new MilitaryPoint(3));
		player.setLastExcommunication(true);
		game.getExcommunicationDeck().set(2, ex2);
		controller.giveVictoryPoints();
		vc.setQuantity(18);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testGiveVictoryPointsWithFinalExcommunicationVentures() {
		player.setMyValues(new SetOfValues());
		player.getMyValues().setFaithPoints(new FaithPoint(4));
		player.getMyBoard().getPersonalTerritories().setCards(territory1);
		player.getMyBoard().getPersonalTerritories().setCards(territory2);
		player.getMyBoard().getPersonalTerritories().setCards(territory3);
		player.getMyBoard().getPersonalCharacters().setCards(character1);
		player.getMyBoard().getPersonalVentures().setCards(venture1);
		player.getMyBoard().getPersonalVentures().setCards(venture2);
		player.getMyValues().setMilitaryPoints(new MilitaryPoint(3));
		player2.getMyValues().setMilitaryPoints(new MilitaryPoint(3));
		player.setLastExcommunication(true);
		game.getExcommunicationDeck().set(2, ex3);
		controller.giveVictoryPoints();
		vc.setQuantity(11);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testGiveVictoryPointsWithFinalExcommunicationMilitary() {
		player.setMyValues(new SetOfValues());
		player.getMyValues().setFaithPoints(new FaithPoint(4));
		player.getMyBoard().getPersonalTerritories().setCards(territory1);
		player.getMyBoard().getPersonalTerritories().setCards(territory2);
		player.getMyBoard().getPersonalTerritories().setCards(territory3);
		player.getMyBoard().getPersonalCharacters().setCards(character1);
		player.getMyBoard().getPersonalVentures().setCards(venture1);
		player.getMyBoard().getPersonalVentures().setCards(venture2);
		player.getMyValues().setMilitaryPoints(new MilitaryPoint(30));
		player2.getMyValues().setMilitaryPoints(new MilitaryPoint(3));
		player.setLastExcommunication(true);
		game.getExcommunicationDeck().set(2, ex4);
		controller.giveVictoryPoints();
		vc.setQuantity(0);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testGiveVictoryPointsWithFinalSubVictoryPoints() {
		player.setMyValues(new SetOfValues());
		player.getMyValues().setFaithPoints(new FaithPoint(4));
		player.getMyBoard().getPersonalTerritories().setCards(territory1);
		player.getMyBoard().getPersonalTerritories().setCards(territory2);
		player.getMyBoard().getPersonalTerritories().setCards(territory3);
		player.getMyBoard().getPersonalCharacters().setCards(character1);
		player.getMyBoard().getPersonalVentures().setCards(venture1);
		player.getMyBoard().getPersonalVentures().setCards(venture2);
		player.getMyValues().setMilitaryPoints(new MilitaryPoint(7));
		player2.getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		player.setLastExcommunication(true);
		game.getExcommunicationDeck().set(2, ex5);
		controller.giveVictoryPoints();
		vc.setQuantity(13);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testGiveVictoryPointsWithFinalSubResoucesPoints() {
		player.setMyValues(new SetOfValues());
		player.getMyValues().setFaithPoints(new FaithPoint(6));
		player.getMyValues().setCoins(new Coin(3));
		player.getMyValues().setServants(new Servant(2));
		player.getMyValues().setStones(new Stone(1));
		player.getMyValues().setWoods(new Wood(1));
		player.getMyValues().setMilitaryPoints(new MilitaryPoint(7));
		player2.getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		player3.getMyValues().setMilitaryPoints(new MilitaryPoint(7));
		player.setLastExcommunication(true);
		game.getExcommunicationDeck().set(2, ex6);
		controller.giveVictoryPoints();
		vc.setQuantity(3);
		assertEquals(vc, player.getMyValues().getVictoryPoints());
	}

	@Test
	public void testGiveVictoryPointsWithFinalSubCostBuildings() {
		player.setMyValues(new SetOfValues());
		player.getMyValues().setFaithPoints(new FaithPoint(6));
		player.getMyBoard().getPersonalBuildings().setCards(building1);
		player.getMyBoard().getPersonalBuildings().setCards(building2);
		player.getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		player2.getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		player3.getMyValues().setMilitaryPoints(new MilitaryPoint(10));
		player.setLastExcommunication(true);
		game.getExcommunicationDeck().set(2, ex7);
		controller.giveVictoryPoints();
		vc.setQuantity(6);
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

	@Test
	public void testWinnerOfTheGame() {
		player.getMyValues().setVictoryPoints(new VictoryPoint(10));
		player2.getMyValues().setVictoryPoints(new VictoryPoint(1));
		player3.getMyValues().setVictoryPoints(new VictoryPoint(3));
		controller.giveVictoryPoints();
		Player winner = controller.winnerOfTheGame();
		assertEquals(winner, player);
	}

	@Test
	public void testWinnerOfTheGameTie() {
		player.setMyName("carlo");
		player2.setMyName("giorgia");
		player3.setMyName("merco");
		player.setMyColour(PlayerColour.BLUE);
		player2.setMyColour(PlayerColour.RED);
		player3.setMyColour(PlayerColour.YELLOW);
		controller.setPlayerTurn(playerTurn);
		player.getMyValues().setVictoryPoints(new VictoryPoint(10));
		player2.getMyValues().setVictoryPoints(new VictoryPoint(10));
		player3.getMyValues().setVictoryPoints(new VictoryPoint(1));
		Player winner = controller.winnerOfTheGame();
		assertEquals(player, winner);
	}

	@Test
	public void testVerifyRequirementsExcommunication() {
		controller.setCurrentPlayer(player);
		cardIndex = 1;
		game.getExcommunicationDeck().set(0, ex1);
		player.getMyValues().setFaithPoints(new FaithPoint(3));
		assertTrue(controller.verifyRequiremetsExcommunication());
	}
}
