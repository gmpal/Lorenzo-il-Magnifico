package it.polimi.ingsw.GC_24.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.Ranking;
import it.polimi.ingsw.GC_24.model.State;
import it.polimi.ingsw.GC_24.model.cards.Buildings;
import it.polimi.ingsw.GC_24.model.cards.Development;
import it.polimi.ingsw.GC_24.model.cards.Leader;
import it.polimi.ingsw.GC_24.model.cards.Requirements;
import it.polimi.ingsw.GC_24.model.cards.Ventures;
import it.polimi.ingsw.GC_24.model.effects.immediate.ChooseNewCard;
import it.polimi.ingsw.GC_24.model.effects.immediate.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.effects.permanent.SubVicrotyPointsFromSetOfValue;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;
import it.polimi.ingsw.GC_24.network.RMI.ServerRMIView;
import it.polimi.ingsw.GC_24.network.SOC.ServerSocketView;
import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;

/**
 * This class contains all the controller logic and methods. They will be
 * explained one by one
 */
public class Controller extends MyObservable implements MyObserver, Runnable {

	private final Model game;
	private ActionFactory actionFactory = new ActionFactory();
	private SetOfValues tempCost = new SetOfValues();
	private Action action = null;
	private HashMap<String, Object> hashMap;
	private int controllerNumber = 0;
	private List<Player> councilTurnArray;
	private List<Player> playerTurn;
	private Player currentPlayer;
	private int cardsIndex = 0;

	private SetOfValues saleForPermanentEffect = new SetOfValues();
	private SetOfValues alternativeSale = new SetOfValues();

	private String parametersAnswer;

	private boolean alreadyPlaying = false;
	private boolean parametersChosen = true;
	private boolean vaticanChosen;

	private Timers timers = new Timers();
	private Timer t1;

	/** These locks are used to wait for specific answers */
	private Object tempCostWaiting = new Object();
	private Object actionWaiting = new Object();
	private Object waitingForSalesChoice = new Object();
	private Object waitingForParametersChoose = new Object();
	private Object waitingForVaticanChoice = new Object();
	private String tempCostString = new String();

	private boolean saleChosen;

	// constructor

	public Controller(Model game) {

		this.game = game;
		controllerNumber++;
	}

	/**
	 * This run method encapsulates the game logic. It handles the turns,
	 * communicates the turnArray, sends the information when needed and makes the
	 * players start playing.
	 */
	@Override
	public void run() {
		// sets the model based on the players list
		game.setModel(game.getPlayers());

		this.currentPlayer = game.getCurrentPlayer();

		sendPersonalInformationToEveryOne();

		playerTurn = game.getPlayers();

		game.setGameState(State.PERIOD1_ROUND1);
		sendUrlExcommunication();

		// until the game is ENDED
		while (!game.getGameState().equals(State.ENDED)) {
			// clears the board
			game.getBoard().clear();
			// deals the cards
			game.getCards().dealCards(game.getBoard(), cardsIndex / 2 + 1);

			// sends various informations
			sendBoardInformation();
			sendUrlColor(game.getBoard().urlPlayerColour());
			sendPersonalInformationToEveryOne();
			sendTurnArray(playerTurn);

			for (int j = 0; j < 4; j++) {
				// cycle for consuming all the family members for all the players
				for (int i = 0; i < playerTurn.size(); i++) {
					// cycle for consuming one family members for all the players

					// sends various informations
					sendUrlBoard(game.getBoard().allUrl());
					sendUrlColor(game.getBoard().urlPlayerColour());

					this.currentPlayer = game.getCurrentPlayer();
					// sends the current player (to update the turns)
					sendCurrentPlayer();

					if (!alreadyPlaying) {
						letThemPlay();

					}

					/*
					 * This block waits for a player doing an action, because after an action the
					 * game-currentPlayer is updated. If the action done before the timer, it stops
					 * the timer, otherwise it does startTimerForPlayerAction() content. TIMER READ
					 * FROM FILE
					 */

					t1 = new Timer();

					startTimerForPlayerAction(t1);

					synchronized (actionWaiting) {

						while (this.currentPlayer.equals(game.getCurrentPlayer())) {
							try {
								actionWaiting.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
								Thread.currentThread().interrupt();

							}
						}
					}
					t1.cancel();

					/* Repeats until the players are finished */
				}

			}
			// it's time to look at the council palace for turn updates!
			councilTurnArray = game.getBoard().getCouncilPalace().getTemporaryTurn();

			// sends useful informations
			updateListOfPlayerTurn(councilTurnArray);
			sendTurnArray(playerTurn);

			// increments the state
			game.incrementState();

			// check if there are activated One-Time-per-Turn leaders, and sets them not in
			// use
			checkToActivateLeader();
			// increments the cardsindex, useful for dealing
			cardsIndex++;
			// and repeat everything til state "ENDED"
		}
		gameEndHandler();

	}

	/** Sends the board information in order to update it */
	public void sendBoardInformation() {

		String[] boardInformation = game.prepareBoardInformation();
		hashMap = new HashMap<>();
		hashMap.put("boardInformation", boardInformation);
		notifyMyObservers(hashMap);

		game.setRanking(new Ranking(playerTurn));
		sendRankings();

	}

	/** sends the rankings of the game */
	public void sendRankings() {
		String rankings = game.getRanking().toString();
		hashMap = new HashMap<>();
		hashMap.put("rankings", rankings);
		notifyMyObservers(hashMap);
	}

	/**
	 * sends useful informations to every player, temporary changing the
	 * currentPlayer to use the sendToCurrentPlayer method
	 */
	public void sendPersonalInformationToEveryOne() {
		Player actualCurrentPlayer = currentPlayer;
		for (Player p : game.getPlayers()) {

			currentPlayer = p;
			sendPersonalInformation();
			sendUrlPersonalBoard(p.getMyBoard().urlPersonalBoard());
		}
		currentPlayer = actualCurrentPlayer;
	}

	/** sends useful informations to currentplayer */
	public void sendPersonalInformation() {
		String[] personalInformation = game.preparePersonalInformation(currentPlayer);
		hashMap = new HashMap<>();
		hashMap.put("personalInformation", personalInformation);
		sendToCurrentPlayer(hashMap);
	}

	/**
	 * checks if in the player's personalLeader there are some activated cards that
	 * are oneTimePerTurn and in that case the method sets this boolean to false
	 */
	public void checkToActivateLeader() {
		for (Player p : playerTurn) {
			for (Leader l : p.getMyBoard().getPersonalLeader()) {
				if (l.isOneTimePerTurn() && l.isInUse()) {
					l.setInUse(false);
				}
			}
		}
		sendPersonalInformationToEveryOne();
	}

	/**
	 * This methods starts the timer for the player action and notify everyone if
	 * somebody loses their turn
	 */
	private void startTimerForPlayerAction(Timer t1) {
		t1.schedule(new TimerTask() {
			public void run() {
				sendInfo("Time out for " + currentPlayer.getMyName() + ", player number "
						+ currentPlayer.getPlayerNumber() + ", colour " + currentPlayer.getMyColour());
				incrementTurn();
				// wakes up the run() that is followed by a timer.cancel();
				synchronized (actionWaiting) {
					actionWaiting.notify();
				}
			}

		}, timers.getTimeToDisconnectPlayer());
	}

	/**
	 * This method handles the end of the game. 1)Conquered Territories: 1/4/10/20
	 * Victory Points for 3/4/5/6 Territory Cards on your Personal Board.
	 * 2)Influenced Characters: 1/3/6/10/15/21 Victory Points for 1/2/3/4/5/6
	 * Character Cards next to your Personal Board. 3)Encouraged Ventures: The sum
	 * of all Victory Points on the Venture Cards next to your Personal Board.
	 * 4)Military Strength: If there is a tie between first players, they all gain 5
	 * Victory Points and nobody gains 2 Victory Points. If there is a tie between
	 * second players, they all gain 2 Victory Points. 5)Collected Resources: 1
	 * Victory Point for every 5 resources of all types. ---> The player with most
	 * Victory Points is the winner. In case of a tie, the player more advanced on
	 * the Turn Order is the winner.
	 */
	public void gameEndHandler() {
		giveVictoryPoints();
		Player winner = winnerOfTheGame();
		sendInfo("The winner of the game is " + winner);
		game.setGameState(State.ENDED);
	}

	/** This methods returns the winner of the game using victoryPoints */
	public Player winnerOfTheGame() {
		List<Integer> finalVictoryPoints = new ArrayList<>();
		List<Player> winners = new ArrayList<>();
		Player winner = null;
		for (int i = 0; i < game.getPlayers().size(); i++) {
			finalVictoryPoints.add(game.getPlayers().get(i).getMyValues().getVictoryPoints().getQuantity());
		}
		Collections.sort(finalVictoryPoints);
		Collections.reverse(finalVictoryPoints);
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (game.getPlayers().get(i).getMyValues().getVictoryPoints().getQuantity() == finalVictoryPoints.get(0)) {
				winners.add(game.getPlayers().get(i));
			}

		}
		if (winners.size() > 1) {
			for (Player playert : playerTurn) {
				if (winners.contains(playert)) {
					winner = playert;
					break;
				}
			}
		} else {
			winner = winners.get(0);
		}

		return winner;
	}

	/**
	 * This method calculates the final victory points for each player. Based on the
	 * final rules of the game. It checks the permanent effect and the
	 * excommunication effect that gives or takes victory points.
	 */
	public void giveVictoryPoints() {
		Player player;
		List<Integer> finalMilitaryPoints = new ArrayList<>();
		String finalExcommunication = game.getExcommunicationDeck().get(2).getEffect().getName();

		for (int i = 0; i < game.getPlayers().size(); i++) {
			player = game.getPlayers().get(i);
			player.getMyValues().getFaithPoints().convertToValue(game.getCorrespondingValue())
					.addTwoSetsOfValues(player.getMyValues());

			if (player.hasLastExcommunication() && finalExcommunication.equals("noVictoryPointsFromTerritories")) {
				player.getMyValues().getVictoryPoints().addQuantity(
						player.getMyBoard().getPersonalCharacters().convertCardToVictoryPoints().getQuantity());
				player.getMyValues().getVictoryPoints().addQuantity(
						player.getMyBoard().getPersonalVentures().convertCardToVictoryPoints().getQuantity());
			} else if (player.hasLastExcommunication()
					&& finalExcommunication.equals("noVictoryPointsFromCharacters")) {
				player.getMyValues().getVictoryPoints().addQuantity(
						player.getMyBoard().getPersonalTerritories().convertCardToVictoryPoints().getQuantity());
				player.getMyValues().getVictoryPoints().addQuantity(
						player.getMyBoard().getPersonalVentures().convertCardToVictoryPoints().getQuantity());
			} else if (player.hasLastExcommunication() && finalExcommunication.equals("noVictoryPointsFromVentures")) {
				player.getMyValues().getVictoryPoints().addQuantity(
						player.getMyBoard().getPersonalTerritories().convertCardToVictoryPoints().getQuantity());
				player.getMyValues().getVictoryPoints().addQuantity(
						player.getMyBoard().getPersonalCharacters().convertCardToVictoryPoints().getQuantity());
			} else {
				player.getMyValues().getVictoryPoints()
						.addQuantity(player.getMyBoard().convertToVictoryPoints().getQuantity());
			}
			player.getMyValues().getVictoryPoints()
					.addQuantity(player.getMyValues().convertSetToVictoryPoints().getQuantity());
			finalMilitaryPoints.add(player.getMyValues().getMilitaryPoints().getQuantity());

		}
		for (int i = 0; i < finalMilitaryPoints.size() - 1; i++) {
			for (int j = i + 1; j < finalMilitaryPoints.size(); j++) {
				if (finalMilitaryPoints.get(i) == finalMilitaryPoints.get(j)) {
					finalMilitaryPoints.remove(j--);
				}
			}
		}
		Collections.sort(finalMilitaryPoints);
		Collections.reverse(finalMilitaryPoints);
		convertMilitaryPointsToVictoryPoints(finalMilitaryPoints);

		for (int i = 0; i < game.getPlayers().size(); i++) {
			player = game.getPlayers().get(i);
			if (player.hasLastExcommunication() && finalExcommunication.equalsIgnoreCase("subVictoryPoints")) {
				SubVicrotyPointsFromSetOfValue eff = (SubVicrotyPointsFromSetOfValue) game.getExcommunicationDeck()
						.get(2).getEffect();
				int subValue = (player.getMyValues().getVictoryPoints().getQuantity())
						/ (eff.getSetForSub().getVictoryPoints().getQuantity());
				player.getMyValues().getVictoryPoints().subQuantity(subValue);
			}
			if (player.hasLastExcommunication() && finalExcommunication.equalsIgnoreCase("subResourcesPoints")) {
				player.getMyValues().getVictoryPoints().subQuantity(player.getMyValues().numberResources());
			}
			if (player.hasLastExcommunication() && finalExcommunication.equalsIgnoreCase("subCostBuildings")) {
				int quantityVictoryPoints = 0;
				for (Development d : player.getMyBoard().getPersonalBuildings().getCards()) {
					Buildings b = (Buildings) d;
					if (game.getExcommunicationDeck().get(2).getEffect().getName().equals("subCostBuildings")) {
						SubVicrotyPointsFromSetOfValue eff = (SubVicrotyPointsFromSetOfValue) game
								.getExcommunicationDeck().get(2).getEffect();
						if (eff.getSetForSub().getCoins().getQuantity() != 0) {
							quantityVictoryPoints += b.getCost().getCoins().getQuantity();
						}
						if (eff.getSetForSub().getWoods().getQuantity() != 0) {
							quantityVictoryPoints += b.getCost().getWoods().getQuantity();
						}
						if (eff.getSetForSub().getStones().getQuantity() != 0) {
							quantityVictoryPoints += b.getCost().getStones().getQuantity();
						}
						if (eff.getSetForSub().getServants().getQuantity() != 0) {
							quantityVictoryPoints += b.getCost().getServants().getQuantity();
						}
					}
				}
				player.getMyValues().getVictoryPoints().subQuantity(quantityVictoryPoints);
			}
			if (player.hasLastExcommunication() && finalExcommunication.equalsIgnoreCase("subMilitaryPoints")) {
				player.getMyValues().getVictoryPoints()
						.subQuantity(player.getMyValues().getMilitaryPoints().getQuantity());
			}
		}
	}

	/**
	 * This method convert Military Points to Victory Points though a List of
	 * Integer, this list contains the players' quantity of Military Points sorted.
	 */

	public void convertMilitaryPointsToVictoryPoints(List<Integer> finalMilitaryPoints) {
		VictoryPoint v1 = new VictoryPoint(5);
		VictoryPoint v2 = new VictoryPoint(2);
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (game.getPlayers().get(i).getMyValues().getMilitaryPoints().getQuantity() == finalMilitaryPoints
					.get(0)) {
				v1.addValueToSet(game.getPlayers().get(i).getMyValues());
			} else if (game.getPlayers().get(i).getMyValues().getMilitaryPoints().getQuantity() == finalMilitaryPoints
					.get(1)) {
				v2.addValueToSet(game.getPlayers().get(i).getMyValues());
			}
		}

	}

	/**
	 * This methods updates the turn list looking at the Council Palace, after every
	 * round
	 */
	public void updateListOfPlayerTurn(List<Player> temporaryTurn) {
		int i;
		for (Player player : temporaryTurn) {
			if (playerTurn.contains(player)) {
				playerTurn.remove(player);
			}
			i = temporaryTurn.indexOf(player);
			playerTurn.add(i, player);

		}
	}

	/**
	 * This methods makes the users start playing, calling a method on the view
	 */
	private void letThemPlay() {
		alreadyPlaying = true;
		hashMap = new HashMap<>();
		hashMap.put("startPlaying", "---> game starts here");
		notifyMyObservers(hashMap);

	}

	// SEND METHODS
	/** This method sends to the clients the turn array to be updated */
	private void sendTurnArray(List<Player> turnArray) {
		List<String> turnString = new ArrayList<String>();
		for (Player p : turnArray) {
			turnString.add(p.toString());
		}
		hashMap = new HashMap<>();
		hashMap.put("Turns", turnString);
		notifyMyObservers(hashMap);
	}

	/** This methods sends the currentPlayer */
	private void sendCurrentPlayer() {

		hashMap = new HashMap<>();

		hashMap.put("currentPlayer", this.currentPlayer.getMyName());

		notifyMyObservers(hashMap);

	}

	/**
	 * This method sends to the clients a simple information to be printed on the
	 * view
	 */
	private void sendInfo(String string) {
		hashMap = new HashMap<>();
		hashMap.put("info", string);
		notifyMyObservers(hashMap);
	}

	@Override
	public synchronized <C> void update(C change) {

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				String answer;
				try {
					answer = handleRequestFromClient((Map<String, Object>) change);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		t1.start();

	}

	/**
	 * This method sends the list of image urls of cards on the Tower, it is useful
	 * for the GUI.
	 */
	public void sendUrlBoard(List<String> urls) {
		hashMap = new HashMap<>();
		hashMap.put("urlBoard", urls);
		notifyMyObservers(hashMap);
	}

	/**
	 * This method sends the list of image urls of cards on the PersonalBoard, it is
	 * useful for the GUI.
	 */
	public void sendUrlPersonalBoard(List<String> urls) {
		hashMap = new HashMap<>();
		hashMap.put("urlPersonalBoard", urls);
		sendToCurrentPlayer(hashMap);
	}

	/**
	 * This method send the list of image urls of excommunication tile, it is useful
	 * for the GUI.
	 */
	public void sendUrlExcommunication() {
		hashMap = new HashMap<>();
		hashMap.put("urlExcommunication", game.getUrlExcommunication());
		notifyMyObservers(hashMap);
	}

	/**
	 * This method sends the list of urls of image of the player that occupy the
	 * places. At the end of the list there are the urls of image of the dice, it is
	 * useful for GUI.
	 */
	public void sendUrlColor(List<String> urls) {
		urls.addAll(game.getDice().urlDice());
		hashMap = new HashMap<>();
		hashMap.put("urlColour", urls);
		notifyMyObservers(hashMap);
	}

	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords in
	 * the keySet, it does different things with different objects
	 * 
	 * @throws IOException
	 */
	private String handleRequestFromClient(Map<String, Object> request) throws IOException {

		Set<String> command = request.keySet();

		/** this contains the playerName and adds the player to the current Game */
		if (command.contains("player")) {

			String name = (String) request.get("player");
			game.setTempName(name);
			game.addPlayer();
			return "player connected";

		}
		/** chosen Cost of double cost cards */
		else if (command.contains("chosenCost")) {
			synchronized (tempCostWaiting) {

				this.tempCostString = (String) request.get("chosenCost");
				tempCostWaiting.notify();
			}
			return "Controller: chosen cost updated";

		}
		/** action request from client */
		else if (command.contains("action")) {

			handleAction(request);
			String answer = verifyAction(this.action);
			if (!answer.equals("ok")) {
				incorrenctActionHandling(answer);

			} else {

				t1.cancel();
				correctActionExecute();
			}
		}
		/**
		 * generic answer for parameters, it depends on the effect and is handled
		 * differenctly
		 */
		else if (command.contains("answerForParameters")) {

			synchronized (waitingForParametersChoose) {
				this.parametersAnswer = (String) request.get("answerForParameters");
				this.parametersChosen = true;
				waitingForParametersChoose.notify();
			}
			return "parameters updated";

		}
		/** answer for double sale cards */
		else if (command.contains("answerForsale")) {

			String setOfSales = (String) request.get("answerForsale");
			synchronized (waitingForSalesChoice) {
				if (setOfSales.equals("2")) {
					this.saleForPermanentEffect = alternativeSale;
				}
				saleChosen = true;
				waitingForSalesChoice.notify();
			}
			return "sale chosen";

		}
		/**
		 * communicates the client disconnection and handles it, unregistering the
		 * serverSocketView from the controller observers and removing the player from
		 * their game turnList
		 */
		else if (command.contains("clientSocketClosed")) {

			String name = "";
			for (int i = 0; i < this.getMyObservers().size(); i++) {
				if (!(this.getMyObservers().get(i) instanceof ServerRMIView)) {
					ServerSocketView serverSocketView = (ServerSocketView) this.getMyObservers().get(i);
					if (serverSocketView.getSocket().isClosed()) {
						name = serverSocketView.getName();
						this.unregisterMyObserver(serverSocketView);
						i--;
					}
				}
			}
			int position = 0;
			for (Player p : playerTurn) {
				if (p.getMyName().equals(name)) {
					position = playerTurn.indexOf(p);
				}
			}

			if (currentPlayer.getMyName().equals(name)) {
				if (currentPlayer.equals(playerTurn.get(playerTurn.size() - 1))) {
					game.setCurrentPlayer(playerTurn.get(0));
				} else {
					game.setCurrentPlayer(playerTurn.get((playerTurn.indexOf(currentPlayer)) + 1));
				}
				this.currentPlayer = game.getCurrentPlayer();
			}
			playerTurn.remove(playerTurn.get(position));

			sendInfo("\nPlayer " + name + ", disconnected, you can just keep playing!\n");
			if (playerTurn.size() == 1) {
				sendInfo("\nGame ended\n");
				gameEndHandler();
			} else {
				sendCurrentPlayer();
			}

		}else if (command.contains("ClientRMIClosed")) {
			String name = (String) request.get("ClientRMIClosed");
			int position = 0;
			for (Player p : playerTurn) {
				if (p.getMyName().equals(name)) {
					position = playerTurn.indexOf(p);
				}
			}

			if (currentPlayer.getMyName().equals(name)) {
				if (currentPlayer.equals(playerTurn.get(playerTurn.size() - 1))) {
					game.setCurrentPlayer(playerTurn.get(0));
				} else {
					game.setCurrentPlayer(playerTurn.get((playerTurn.indexOf(currentPlayer)) + 1));
				}
				this.currentPlayer = game.getCurrentPlayer();
			}
			playerTurn.remove(playerTurn.get(position));

			sendInfo("\nPlayer " + name + ", disconnected, you can just keep playing!\n");
			if (playerTurn.size() == 1) {
				sendInfo("\nGame ended\n");
				gameEndHandler();
			} else {
				sendCurrentPlayer();
			}
			
		} else if (command.contains("addPlayer"))

		{
			game.addPlayer();

		} else if (command.contains("leader")) {

			handleAndVerifyLeader(request);
			/** contains the answer for excommunication request */
		} else if (command.contains("answerForVatican")) {

			String answer = (String) request.get("answerForVatican");
			giveExcommunication(answer);
			synchronized (waitingForVaticanChoice) {
				vaticanChosen = true;
				waitingForVaticanChoice.notify();
			}
		}

		return "bad command";

	}

	/**
	 * receives the command action on leader cards. If it is to activate the leader,
	 * it verifies if the player can, and in that case gives them the effects. If
	 * it's to discard the card, then the method will check the card's availability
	 * and will give a council privilege o the player
	 */
	private void handleAndVerifyLeader(Map<String, Object> request) {
		;
		StringTokenizer tokenizer = new StringTokenizer((String) request.get("leader"));

		String actionLeader = tokenizer.nextToken();
		String indexLeader = tokenizer.nextToken();
		int index = Integer.parseInt(indexLeader) - 1;

		String feedback = "Answer: \n";
		if (actionLeader.equalsIgnoreCase("activate")) {
			feedback = verifyAvailabilityLeader(index, feedback);
			feedback = verifyRequirementsLeader(index, feedback);
			if (!feedback.equals("Answer: \n")) {
				incorrenctLeaderHandling(feedback);
			} else {
				assignLeaderEffects(index);
			}
		} else if (actionLeader.equalsIgnoreCase("discard")) {
			feedback = verifyAvailabilityLeader(index, feedback);
			if (!feedback.equals("Answer: \n")) {
				incorrenctLeaderHandling(feedback);
			} else {
				CouncilPrivilege leaderDiscardCouncilPrivilege = new CouncilPrivilege("council", 1);
				askAndWaitForParameters(leaderDiscardCouncilPrivilege);
				leaderDiscardCouncilPrivilege.giveImmediateEffect(currentPlayer);
				currentPlayer.getMyBoard().getPersonalLeader().remove(index);
				sendPersonalInformationToEveryOne();
				// sendUrlPersonalBoard(game.getCurrentPlayer().getMyBoard().urlPersonalBoard());
				awakenSleepingClient();
			}
		}

	}

	/** This method verifies the leader availability */
	public String verifyAvailabilityLeader(int index, String feedback) {
		if (index > (currentPlayer.getMyBoard().getPersonalLeader().size() - 1)) {
			return feedback + "This card has already been chosen\n";
		} else if (currentPlayer.getMyBoard().getPersonalLeader().get(index).isInUse()) {
			return feedback + "This card is already in use\n";
		}
		return feedback;
	}

	public String verifyRequirementsLeader(int index, String feedback) {
		Requirements requirements = currentPlayer.getMyBoard().getPersonalLeader().get(index).getRequirements();
		if (!currentPlayer.getLeaderOneTimePerTurn().isEmpty()) {
			for (Leader card : currentPlayer.getLeaderOneTimePerTurn()) {
				if (currentPlayer.getMyBoard().getPersonalLeader().get(index).getName().equals(card.getName())) {
					return feedback;
				}
			}
		}
		if (verifyLeaderExclusiveRequirement(index)) {
			return feedback;
		}
		if (!requirements.getRequirementSetOfValues().isEmpty()
				&& !currentPlayer.getMyValues().doIHaveThisSet(requirements.getRequirementSetOfValues())) {
			feedback = feedback + "You don't have enough resources to activate this card!\n";
		}
		if (requirements.getRequirmentTerritories() != 0 && currentPlayer.getMyBoard().getPersonalTerritories()
				.getCards().size() < requirements.getRequirmentTerritories()) {
			feedback = feedback + "You don't have enough Territories to activate this card!\n";
		}
		if (requirements.getRequirmentCharacters() != 0 && currentPlayer.getMyBoard().getPersonalCharacters().getCards()
				.size() < requirements.getRequirmentCharacters()) {
			feedback = feedback + "You don't have enough Characters to activate this card!\n";
		}
		if (requirements.getRequirmentBuildings() != 0 && currentPlayer.getMyBoard().getPersonalBuildings().getCards()
				.size() < requirements.getRequirmentBuildings()) {
			feedback = feedback + "You don't have enough Buildings to activate this card!\n";
		}
		if (requirements.getRequirmentVentures() != 0 && currentPlayer.getMyBoard().getPersonalVentures().getCards()
				.size() < requirements.getRequirmentVentures()) {
			feedback = feedback + "You don't have enough Ventures to activate this card!\n";
		}
		return feedback;
	}

	public boolean verifyLeaderExclusiveRequirement(int index) {
		Requirements requirements = currentPlayer.getMyBoard().getPersonalLeader().get(index).getRequirements();
		if (currentPlayer.getMyBoard().getPersonalLeader().get(index).getName().equalsIgnoreCase("Lucrezia Borgia")) {
			return (currentPlayer.getMyBoard().getPersonalTerritories().getCards().size() >= requirements
					.getRequirmentTerritories()
					|| currentPlayer.getMyBoard().getPersonalCharacters().getCards().size() >= requirements
							.getRequirmentCharacters()
					|| currentPlayer.getMyBoard().getPersonalBuildings().getCards().size() >= requirements
							.getRequirmentBuildings()
					|| currentPlayer.getMyBoard().getPersonalVentures().getCards().size() >= requirements
							.getRequirmentVentures());

		} else {
			return false;
		}
	}

	/**
	 * This method gives an excommunication card to the player that either decides
	 * not to give his support to the Vatican or doesn't have the faith
	 * requirements.
	 */
	private void giveExcommunication(String answer) {
		int period = cardsIndex / 2;
		if (answer.equalsIgnoreCase("y")) {
			currentPlayer.setMyValues(currentPlayer.getMyValues().addTwoSetsOfValues(
					currentPlayer.getMyValues().getFaithPoints().convertToValue(game.getCorrespondingValue())));
			if (currentPlayer.getPermanentEffect("pointsForSupportVatican") != null) {
				currentPlayer.getMyValues().getVictoryPoints().addQuantity(5);
			}
			currentPlayer.getMyValues().getFaithPoints().setQuantity(0);
		} else {
			currentPlayer.getActivePermanentEffects()
					.add(game.getExcommunicationDeck().get(period).getPermanentEffect());
			sendProblemsToCurrentPlayer("You have decided to not support the Vatican so you have been excommunicated");
		}
	}

	/**
	 * This method verify if the player have the requirements to support Vatican.
	 * 
	 * @return true if player have requirements, false otherwise.
	 */
	public boolean verifyRequiremetsExcommunication() {
		return game.getExcommunicationDeck().get(cardsIndex / 2).getRequiremetsForExcommunication()
				.getQuantity() <= currentPlayer.getMyValues().getFaithPoints().getQuantity();
	}

	/** This method handles the action request from the client */
	private void handleAction(Map<String, Object> request) {

		StringTokenizer tokenizer = new StringTokenizer((String) request.get("action"));

		String tempFamiliar = tokenizer.nextToken();
		String tempZone = tokenizer.nextToken();
		String tempFloor = tokenizer.nextToken();
		String tempServants = tokenizer.nextToken();

		/**
		 * Sees if there's an interactive permanent effect WITH DOUBLE SALE before doing
		 * an action, because this particular effect requires user interaction. If yes,
		 * it asks and wait.
		 */
		IncreaseDieValueCard pe = PermanentEffectWithAlternativeSale(
				(IncreaseDieValueCard) currentPlayer.getPermanentEffect("increaseDieValueCard"));

		if (pe != null && pe.getPersonalCards().getType().equalsIgnoreCase(tempZone)) {

			askForSale(pe);
			synchronized (waitingForSalesChoice) {
				saleChosen = false;
				while (!saleChosen) {
					try {
						waitingForSalesChoice.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();

					}
				}
			}
		}

		// ventures need to be handled differently
		if (tempZone.equalsIgnoreCase("ventures")) {
			handleVentures(tempZone, tempFloor);
		} else if (tempZone.equalsIgnoreCase("characters") || tempZone.equalsIgnoreCase("territories")
				|| tempZone.equalsIgnoreCase("buildings")) {
			TowerPlace placeRequested = (TowerPlace) this.game.getBoard().getZoneFromString(tempZone)
					.getPlaceFromStringOrFirstIfZero(tempFloor);
			if (placeRequested.isAvailable()) {
				// takes the cost of the card
				tempCost = new SetOfValues(placeRequested.getCorrespondingCard().getCost());

			}

		}
		// creates the corresponding action
		this.action = actionFactory.makeAction(game, tempFamiliar, tempZone, tempFloor, tempServants, tempCost,
				saleForPermanentEffect);

	}

	/** self-explanatory */
	private void askForSale(IncreaseDieValueCard pe) {
		saleForPermanentEffect = pe.getSale();
		alternativeSale = pe.getAlternativeSale();
		String sale1 = pe.getSale().toString();
		String sale2 = pe.getAlternativeSale().toString();
		String request = sale1 + "\n" + sale2;
		hashMap = new HashMap<>();
		hashMap.put("sale", request);
		sendToCurrentPlayer(hashMap);

	}

	private String verifyAction(Action action2) {

		String responseToActionVerify = action.verify();
		return responseToActionVerify;
	}

	/**
	 * This methods executes the correct action. Handles the interactive effects (if
	 * any) and checks for excommunication. then sends useful information and
	 * proceeds with turn
	 */
	private void correctActionExecute() {

		List<ImmediateEffect> interactiveEffects = action.run();
		this.handleInteractiveEffects(interactiveEffects);

		checkForExcommunication();

		sendBoardInformation();
		sendPersonalInformationToEveryOne();
		sendUrlColor(game.getBoard().urlPlayerColour());
		notifyToProceedWithTurns();
		awakenSleepingClient();

	}

	/**
	 * This method ask to the player if they want to support the Vatican. If it's
	 * the last turn the Excommunication is automatically assigned according to the
	 * requirements.
	 */
	public void checkForExcommunication() {
		if ((cardsIndex == 1 || cardsIndex == 3) && (currentPlayer.getMyFamily().isEmpty())) {
			if (verifyRequiremetsExcommunication()) {
				askForSupportVatican();
			} else {
				sendProblemsToCurrentPlayer("You don't have enough faith points so you have been excommunicated.");
				currentPlayer.getActivePermanentEffects()
						.add(game.getExcommunicationDeck().get(cardsIndex / 2).getPermanentEffect());
			}
		}

		if ((cardsIndex == 5) && (!verifyRequiremetsExcommunication()) && (currentPlayer.getMyFamily().isEmpty())) {
			currentPlayer.setLastExcommunication(true);
			sendProblemsToCurrentPlayer("You don't have enough faith points so you have been excommunicated.");
		}
	}

	/** This method assignes leader effects */
	private void assignLeaderEffects(int index) {

		Leader card = currentPlayer.getMyBoard().getPersonalLeader().get(index);

		if (card.getImmediateEffectLeader() != null) {

			askAndWaitForParameters(card.getImmediateEffectLeader());
			card.getImmediateEffectLeader().giveImmediateEffect(currentPlayer);
		}
		if (card.getValueEffectLeader() != null) {
			card.getValueEffectLeader().giveImmediateEffect(currentPlayer);
		}
		card.setInUse(true);
		if (card.getPermanentEffectLeader() != null) {
			currentPlayer.getActivePermanentEffects().add(card.getPermanentEffectLeader());
		}

		if (card.isOneTimePerTurn() && !currentPlayer.getLeaderOneTimePerTurn().contains(card)) {
			currentPlayer.getLeaderOneTimePerTurn().add(card);
		}
		game.changeInDieValue(currentPlayer);
		sendPersonalInformationToEveryOne();
		// sendUrlPersonalBoard(game.getCurrentPlayer().getMyBoard().urlPersonalBoard());
		awakenSleepingClient();

	}

	private void askForSupportVatican() {
		hashMap = new HashMap<>();
		hashMap.put("vatican", null);
		sendToCurrentPlayer(hashMap);

		vaticanChosen = false;
		synchronized (waitingForVaticanChoice) {
			while (!vaticanChosen) {
				try {
					waitingForVaticanChoice.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}

	}

	/**
	 * This method starts after the choose new card action if everything was okay,
	 * then sends useful informations
	 */
	private void correctChooseNewCardExecute() {

		List<ImmediateEffect> interactiveEffects = action.run();
		this.handleInteractiveEffects(interactiveEffects);

		sendBoardInformation();
		sendUrlBoard(game.getBoard().getUrlList());
		sendPersonalInformationToEveryOne();

	}

	private void incorrenctLeaderHandling(String feedback) {

		sendProblemsToCurrentPlayer(feedback);

		awakenSleepingClient();

	}

	private void incorrenctActionHandling(String responseToActionVerify) {

		sendProblemsToCurrentPlayer(responseToActionVerify);

		awakenSleepingClient();
	}

	private void notifyToProceedWithTurns() {

		synchronized (actionWaiting) {

			incrementTurn();
			actionWaiting.notify();
		}
	}

	private void incrementTurn() {
		if (playerTurn.indexOf(game.getCurrentPlayer()) == playerTurn.size() - 1) {
			game.setCurrentPlayer(playerTurn.get(0));
		} else {
			game.setCurrentPlayer(playerTurn.get(playerTurn.indexOf(game.getCurrentPlayer()) + 1));
		}

	}

	private void awakenSleepingClient() {
		hashMap = new HashMap<>();
		hashMap.put("actionDone", "awakeningTheClients");
		notifyMyObservers(hashMap);
	}

	/**
	 * This method handles the interactive effects of an action, for each effect it
	 * asks for parameters, the handles the answer (for choose new card
	 * differently). Then handles the new generated effects until there are no more
	 */
	private void handleInteractiveEffects(List<ImmediateEffect> interactiveEffects) {

		List<ImmediateEffect> secondaryInteractiveEffects = new ArrayList<>();

		if (!interactiveEffects.isEmpty()) {

			for (ImmediateEffect effect : interactiveEffects) {
				sendBoardInformation();
				sendPersonalInformationToEveryOne();

				askAndWaitForParameters(effect);

				if (effect instanceof ChooseNewCard) {

					if (!parametersAnswer.contains("null")) {
						String response = createNewActionForChooseNewCard(((ChooseNewCard) effect).getDieValue(),
								effect);

						while (!response.equals("ok")) {
							response = createNewActionForChooseNewCard(((ChooseNewCard) effect).getDieValue(), effect);
						}
						correctChooseNewCardExecute();
					}
				} else {

					effect.giveImmediateEffect(currentPlayer);

				}

				secondaryInteractiveEffects = effect.addAllNewEffectsToThisSet(secondaryInteractiveEffects);

			}
			if (!secondaryInteractiveEffects.isEmpty()) {

				handleInteractiveEffects(secondaryInteractiveEffects);

			}

		}
	}

	/**
	 * This methods creates the fake action request for the method handleAction(),
	 * in order to create a fake action for choose new card effect. This fake action
	 * simulates the use of a neutral family member in order to prevent the tower
	 * from resulting occupied
	 */
	private String createNewActionForChooseNewCard(int dieValue, ImmediateEffect effect) {
		String finalActionRequest = "fakeFamiliarForChooseNewCard " + this.parametersAnswer;

		hashMap = new HashMap<>();
		hashMap.put("action", finalActionRequest);
		handleAction(hashMap);

		this.action.getFamilyMember().setMemberValue(dieValue);

		String response = verifyAction(this.action);
		if (!response.equals("ok")) {

			hashMap = new HashMap<>();
			hashMap.put("problems", response);
			sendToCurrentPlayer(hashMap);

			askAndWaitForParameters(effect);
			return response;

		} else {

			return response;

		}

	}

	/**
	 * This method handles the effect that needs interaction with user it sends the
	 * effect to the client, it recognizes them and asks the user to choose. Then he
	 * sends a specific answer and
	 * 
	 * @param o
	 */

	private void askAndWaitForParameters(ImmediateEffect effect) {
		parametersChosen = false;

		String paramRequest = effect.generateParametersRequest();
		if (!(paramRequest == null)) {
			sendToCurrentPlayer(effect.generateHashMapToSend(paramRequest));

			synchronized (waitingForParametersChoose) {

				while (!parametersChosen) {
					try {
						waitingForParametersChoose.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}

			}
			if (!(effect instanceof ChooseNewCard)) {
				effect.assignParameters(parametersAnswer);

			}
		}
	}

	/**
	 * Everything that passes through this method is received and interpreted only
	 * by the current player
	 */
	private void sendToCurrentPlayer(HashMap<String, Object> hashMap2) {
		String name = currentPlayer.getMyName();
		hashMap2.put("currentPlayerName", name);
		notifyMyObservers(hashMap2);
	}

	/** Checks if there's a Permanent effect with alternative sale */
	private IncreaseDieValueCard PermanentEffectWithAlternativeSale(IncreaseDieValueCard pe) {
		if (pe != null && pe.getAlternativeSale() != null) {
			return pe;
		}
		return null;
	}

	/**
	 * Every string that passes through this method is received and interpreted only
	 * by the current player
	 */
	private void sendProblemsToCurrentPlayer(String responseToActionVerify) {
		hashMap = new HashMap<>();
		hashMap.put("problems", responseToActionVerify);
		hashMap.put("currentPlayerName", currentPlayer.getMyName());
		notifyMyObservers(hashMap);
	}

	/**
	 * If the player wants to take a ventures card, this method let him choose which
	 * one of the double costs to take (if a double cost exists), then waits for the
	 * answer.
	 */
	private void handleVentures(String tempZone, String tempFloor) {
		;
		TowerPlace placeRequested = (TowerPlace) this.game.getBoard().getZoneFromString(tempZone)
				.getPlaceFromStringOrFirstIfZero(tempFloor);
		Ventures cardRequested = (Ventures) placeRequested.getCorrespondingCard();
		if (cardRequested != null) {
			SetOfValues cost1 = cardRequested.getCost();
			SetOfValues cost2 = cardRequested.getAlternativeCost();
			if (!cost1.isEmpty() && cost2 != null) {

				MilitaryPoint requirements = cardRequested.getRequiredMilitaryPoints();
				String request = "Cost1: " + cost1.toString() + "\nCost2: " + cost2.toString() + "\nRequest for cost2 :"
						+ requirements.toString();
				hashMap = new HashMap<>();
				hashMap.put("doubleCost", request);

				sendToCurrentPlayer(hashMap);
				System.out.println(
						"Controller --> Inviando la richiesta di scelta costo, mi metto in attesa della risposta  ");

				this.tempCostString = new String();
				synchronized (tempCostWaiting) {
					while (this.tempCostString.equals(new String())) {
						try {
							tempCostWaiting.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							Thread.currentThread().interrupt();

						}
					}
				}

				if (this.tempCostString.equals("1")) {
					tempCost = cost1;

				} else {
					tempCost = cost2;
				}

			} else if (cost1.isEmpty() && cost2 != null) {

				tempCost = cost2;
			}
		}

	}

	// getters and setters
	public Model getGame() {
		return game;
	}

	public int getControllerNumber() {
		return controllerNumber;
	}

	public void setControllerNumber(int controllerNumber) {
		this.controllerNumber = controllerNumber;
	}

	public List<Player> getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(List<Player> playerTurn) {
		this.playerTurn = playerTurn;
	}

	// only used in tests
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}