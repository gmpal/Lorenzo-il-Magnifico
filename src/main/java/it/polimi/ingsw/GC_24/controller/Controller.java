package it.polimi.ingsw.GC_24.controller;

import java.io.IOException;

import java.util.*;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.State;
import it.polimi.ingsw.GC_24.model.cards.*;
import it.polimi.ingsw.GC_24.model.effects.immediate.ChooseNewCard;
import it.polimi.ingsw.GC_24.model.effects.immediate.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.effects.permanent.SubVicrotyPointsFromSetOfValue;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.*;
import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;

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

	// locks
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
	 * communicates the turnArray and decides when players start playing.
	 */

	@Override
	public void run() {

		System.out.println("Controller partito!!!");
		game.setModel(game.getPlayers());
		System.out.println("model settato!!!");
		this.currentPlayer = game.getCurrentPlayer();
		System.out.println("Current PLayer settato!!!");
		sendPersonalInformationToEveryOne();
		System.out.println("Informazioni inviate!!!");
		playerTurn = game.getPlayers();
		System.out.println("turni presi!!!");
		game.setGameState(State.PERIOD1_ROUND1);

		while (!game.getGameState().equals(State.ENDED)) {
			System.out.println("GAME STATE: " + game.getGameState());

			game.getBoard().clear();

			game.getCards().dealCards(game.getBoard(), cardsIndex / 2 + 1);


			sendBoardInformation();
			sendPersonalInformationToEveryOne();
			sendTurnArray(playerTurn);

			System.out.println("Controller: everything clear and model sent");
			for (int j = 0; j < 4; j++) {

				for (int i = 0; i < playerTurn.size(); i++) {
					// one familar gone for each player

					// reset the current player
					this.currentPlayer = game.getCurrentPlayer();
					System.out.println("Current Player is ---> " + this.currentPlayer.getMyName());

					sendCurrentPlayer();
					System.out.println("Controller --> Current Player Sent");
					System.out.println("Controller --> Are  they already playing? " + alreadyPlaying);
					if (!alreadyPlaying) {
						letThemPlay();
						System.out.println("Controller --> Play request sent!");
					}

					/*
					 * This block waits for a player doing an action, because after an action the
					 * game-currentPlayer is updated
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
			updateListOfPlayerTurn(councilTurnArray);
			sendTurnArray(playerTurn);
			// let's go to next state
			game.incrementState();
			checkToActivateLeader();
			cardsIndex++;
			// and repeat everything til state "ENDED"
		}
		gameEndHandler();

	}

	public void sendBoardInformation() {
		String[] boardInformation = game.prepareBoardInformation();
		hashMap = new HashMap<>();
		hashMap.put("boardInformation", boardInformation);
		notifyMyObservers(hashMap);
	}

	public void sendPersonalInformationToEveryOne() {
		Player actualCurrentPlayer = currentPlayer;
		for (Player p : game.getPlayers()) {

			currentPlayer = p;
			sendPersonalInformation();
		}
		currentPlayer = actualCurrentPlayer;
	}

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

		sendInfo("The winner of the game is" + winner);
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
		List<String> turnNames = new ArrayList<String>();

		for (Player p : turnArray) {
			turnNames.add(p.getMyName());
		}

		hashMap = new HashMap<>();
		hashMap.put("Turns", turnNames);
		notifyMyObservers(hashMap);
	}

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

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <C> void update(C change) {

		System.out.println("Controller: i received this :" + change);

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				String answer;
				try {
					answer = handleRequestFromClient((Map<String, Object>) change);
					System.out.println("--------------" + answer);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		t1.start();

	}

	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords in
	 * the keySet, it does different things with different objects
	 * 
	 * @throws IOException
	 */
	private String handleRequestFromClient(Map<String, Object> request) throws IOException {

		System.out.println("Controller --> ricevuto una richiesta dal client");

		Set<String> command = request.keySet();
		System.out.println(command);

		if (command.contains("player")) {
			System.out.println("Controller --> Ricevuto un giocatore");
			String name = (String) request.get("player");
			game.setTempName(name);
			game.addPlayer();
			return "player connected";

		}

		else if (command.contains("chosenCost")) {
			synchronized (tempCostWaiting) {
				System.out.println("Controller --> Ricevuta la scelta di un costo doppio");
				this.tempCostString = (String) request.get("chosenCost");
				tempCostWaiting.notify();
			}
			return "Controller: chosen cost updated";

		}

		else if (command.contains("action")) {
			System.out.println("Controller --> Ricevuta un'azione di piazzamento ");
			handleAction(request);
			String answer = verifyAction(this.action);
			if (!answer.equals("ok")) {
				incorrenctActionHandling(answer);

			} else {
				t1.cancel();
				correctActionExecute();
			}
		}

		else if (command.contains("answerForParameters")) {
			System.out.println("Controller --> Ricevuta la risposta di parametri ");
			synchronized (waitingForParametersChoose) {
				this.parametersAnswer = (String) request.get("answerForParameters");
				this.parametersChosen = true;
				waitingForParametersChoose.notify();
			}
			return "parameters updated";

		} else if (command.contains("answerForsale")) {
			System.out.println("Controller --> Ricevuta la scelta di uno dei due sconti sul prezzo ");
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
		/* RMI COMMANDS */

		else if (command.contains("addPlayer")) {
			game.addPlayer();

		} else if (command.contains("leader")) {
			System.out.println("Controller --> Ricevuta un'azione leader");
			handleAndVerifyLeader(request);

		} else if (command.contains("answerForVatican")) {
			System.out.println("Controller --> Ricevuta la scelta di supporto al vaticano ");
			String answer = (String) request.get("answerForVatican");
			giveExcommunication(answer);
			synchronized (waitingForVaticanChoice) {
				vaticanChosen = true;
				waitingForVaticanChoice.notify();
			}
		}

		else {
			System.out.println("Controller --> COMANDO NON RICONOSCIUTO ");
			return "bad command";
		}

		return null;

	}

	/**
	 * receives the command action on leader cards. If it is to activate the leader,
	 * it verifies if the player can, and in that case gives them the effects. If
	 * it's to discard the card, then the method will check the card's availability
	 * and will give a council privilege o the player
	 */
	private void handleAndVerifyLeader(Map<String, Object> request) {
		System.out.println("Controller --> Sto gestendo un leader");
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
				sendPersonalInformation();
				awakenSleepingClient();
			}
		}

	}

	public String verifyAvailabilityLeader(int index, String feedback) {
		if (currentPlayer.getMyBoard().getPersonalLeader().get(index).isInUse()) {
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

	private void handleAction(Map<String, Object> request) {
		System.out.println("Controller --> Sto gestendo un'azione");
		StringTokenizer tokenizer = new StringTokenizer((String) request.get("action"));

		String tempFamiliar = tokenizer.nextToken();
		String tempZone = tokenizer.nextToken();
		String tempFloor = tokenizer.nextToken();
		String tempServants = tokenizer.nextToken();

		/**
		 * Sees if there's an interactive permanent effect WITH DOUBLE SALE before doing
		 * an action, because this particular effect requires user interaction
		 */
		IncreaseDieValueCard pe = PermanentEffectWithAlternativeSale(
				(IncreaseDieValueCard) currentPlayer.getPermanentEffect("increaseDieValueCard"));

		if (pe != null && pe.getPersonalCards().getType().equals(tempZone)) {

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

		if (tempZone.equalsIgnoreCase("ventures")) {

			handleVentures(tempZone, tempFloor);
		}
		System.out.println("Controller --> Inviando la richiesta di creazione azione in fabbrica...");
		this.action = actionFactory.makeAction(game, tempFamiliar, tempZone, tempFloor, tempServants, tempCost,
				saleForPermanentEffect);

	}

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
		System.out.println(currentPlayer.getMyValues());
		System.out.println("Controller --> Sto verificando ed eseguendo un'azione ");
		String responseToActionVerify = action.verify();
		return responseToActionVerify;
	}

	private void correctActionExecute() {
		System.out.println("Controller --> La verifica dell'azione è andata a buon fine ");
		List<ImmediateEffect> interactiveEffects = action.run();
		this.handleInteractiveEffects(interactiveEffects);
		System.out.println("Controller --> Conclusa gestione dei costi interattivi ");
		checkForExcommunication();
		notifyToProceedWithTurns();

		sendBoardInformation();
		sendPersonalInformationToEveryOne();
		awakenSleepingClient();
		System.out.println("Controller --> Richiesta di risveglio inviata");

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

	private void assignLeaderEffects(int index) {
		System.out.println("Controller --> La verifica dell' attivazione della carta leader è andata a buon fine ");
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
		sendPersonalInformation();
		awakenSleepingClient();
		System.out.println("Controller --> Richiesta di risveglio inviata");
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

	private void correctChooseNewCardExecute() {

		System.out.println("Controller --> La verifica dell'azione è andata a buon fine ");
		List<ImmediateEffect> interactiveEffects = action.run();
		this.handleInteractiveEffects(interactiveEffects);
		System.out.println("Controller --> Conclusa gestione dei costi interattivi ");
		sendBoardInformation();
		sendPersonalInformation();

	}

	private void incorrenctLeaderHandling(String feedback) {
		System.out.println("Controller --> L'attivazione della carta leader non ha superato i controlli");
		sendProblemsToCurrentPlayer(feedback);
		System.out.println("Controller --> Inviata richiesta di problemi al client");
		awakenSleepingClient();

	}

	private void incorrenctActionHandling(String responseToActionVerify) {
		System.out.println("Controller --> L'azione non ha superato i controlli");
		sendProblemsToCurrentPlayer(responseToActionVerify);
		System.out.println("Controller --> Inviata richiesta di problemi al client");
		awakenSleepingClient();
	}

	private void notifyToProceedWithTurns() {
		System.out.println(
				"Controller --> Sto aggiornando il turno e risvegliando il metodo run() che era in attesa di una mia azione ");
		synchronized (actionWaiting) {
			// QUESTO MI FAREBBE CAMBIARE GIOCATORE
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

	/* Subito dopo run() */
	private void handleInteractiveEffects(List<ImmediateEffect> interactiveEffects) {
		System.out.println("Controller --> Iniziando a gestire gli effetti interattivi ");
		System.out.println("Controller --> lista: " + interactiveEffects);
		List<ImmediateEffect> secondaryInteractiveEffects = new ArrayList<>();

		if (!interactiveEffects.isEmpty()) {
			System.out.println("Controller --> La lista contiene qualcosa");
			int i = 0;

			for (ImmediateEffect effect : interactiveEffects) {
				sendBoardInformation();
				sendPersonalInformationToEveryOne();
				i++;
				System.out.println("Controller --> Gestendo l'effetto specifico #" + i + "of "
						+ interactiveEffects.size() + ": " + effect);

				askAndWaitForParameters(effect);

				System.out.println("Controller -->  Finito di gestire l'effetto specifico#" + i + "of "
						+ interactiveEffects.size() + ": " + effect);

				if (effect instanceof ChooseNewCard) {
					System.out.println("Controller --> è un chooseNewCard, creo l'azione corrispondente");
					if (!parametersAnswer.contains("null")) {
						String response = createNewActionForChooseNewCard(((ChooseNewCard) effect).getDieValue(),
								effect);
						// until my choose new card verify is correct I create
						// another one
						while (!response.equals("ok")) {
							response = createNewActionForChooseNewCard(((ChooseNewCard) effect).getDieValue(), effect);
						}
						correctChooseNewCardExecute();
					}
				} else {
					System.out.println("Controller --> non è un chooseNewCard");
					System.out.println("Controller --> faccio il GiveImmediateEffect()");
					effect.giveImmediateEffect(currentPlayer);
					System.out.println("Controller --> fatto il give immediate Effect");
				}

				secondaryInteractiveEffects = effect.addAllNewEffectsToThisSet(secondaryInteractiveEffects);

			}
			if (secondaryInteractiveEffects != null) {
				System.out.println("Controller --> la lista non è vuota");
				System.out.println("Controller --> ecco la lista: " + secondaryInteractiveEffects);
				System.out.println("Controller --> la sto gestendo ");
				handleInteractiveEffects(secondaryInteractiveEffects);
				System.out.println("Controller --> Finito di gestire la lista secondaria");
			}

		}
	}

	private String createNewActionForChooseNewCard(int dieValue, ImmediateEffect effect) {
		String finalActionRequest = "fakeFamiliarForChooseNewCard " + this.parametersAnswer;
		System.out.println("Controller --> Sto creando un'azione corrispondente");
		hashMap = new HashMap<>();
		hashMap.put("action", finalActionRequest);
		handleAction(hashMap);
		System.out.println("Controller --> Azione per choose new Card creata ");
		System.out.println(this.action);
		System.out.println(this.action.getFamilyMember());
		this.action.getFamilyMember().setMemberValue(dieValue);
		System.out.println("Controller --> Valore del dado del familiare fittizio settato");
		String response = verifyAction(this.action);
		if (!response.equals("ok")) {
			System.out.println("Controller --> L'azione Choose New Card non ha superato i controlli");
			hashMap = new HashMap<>();
			hashMap.put("problems", response);
			sendToCurrentPlayer(hashMap);
			System.out.println("Controller --> Inviata richiesta di problemi al client, e richiesta parametri");
			askAndWaitForParameters(effect);
			return response;

		} else {
			System.out.println("Controller --> verifica ed esecuzione dell'azione completata ");
			return response;

		}

	}

	/**
	 * This method handles the effect that needs interaction with user it sends the
	 * effect to the client, it recognises them and asks the user to choose. Then he
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
				System.out.println("Controller --> Parametri assegnati");
			}
		}
	}

	private void sendToCurrentPlayer(HashMap<String, Object> hashMap2) {
		String name = currentPlayer.getMyName();
		hashMap2.put("currentPlayerName", name);
		notifyMyObservers(hashMap2);
	}

	// "Choose sale: (1,2)\n" + "1." + increase.getSale() + "\n2." +
	// increase.getAlternativeSale()

	private IncreaseDieValueCard PermanentEffectWithAlternativeSale(IncreaseDieValueCard pe) {
		if (pe != null && pe.getAlternativeSale() != null) {
			return pe;
		}
		return null;
	}

	private void sendProblemsToCurrentPlayer(String responseToActionVerify) {
		hashMap = new HashMap<>();
		hashMap.put("problems", responseToActionVerify);
		hashMap.put("currentPlayerName", currentPlayer.getMyName());
		notifyMyObservers(hashMap);
	}

	/**
	 * If the player wants to take a ventures card, this method let him choose which
	 * one of the double costs to take (if a double cost exists).
	 */
	private void handleVentures(String tempZone, String tempFloor) {
		System.out.println("Controller --> Sto gestendo una carta venture per il doppio costo... ");
		TowerPlace placeRequested = (TowerPlace) this.game.getBoard().getZoneFromString(tempZone)
				.getPlaceFromStringOrFirstIfZero(tempFloor);
		Ventures cardRequested = (Ventures) placeRequested.getCorrespondingCard();
		if (cardRequested != null) {
			SetOfValues cost1 = cardRequested.getCost();
			SetOfValues cost2 = cardRequested.getAlternativeCost();
			if (cost1 != null && cost2 != null) {
				System.out.println("Controller --> C'è un doppio costo, invio l'interazione ");
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

				System.out.println("Controller --> L'utente ha scelto, mi sono risvegliato ");
				System.out.println("Controller --> SCELTA DELL UTENTE: " + tempCost);

			}
		}
		System.out.println("Controller --> Fine gestione carta Venture ");
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
