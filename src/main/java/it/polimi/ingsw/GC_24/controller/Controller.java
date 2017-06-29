﻿package it.polimi.ingsw.GC_24.controller;

import java.io.IOException;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.State;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.*;

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
	private String parametersAnswer;

	private boolean alreadyPlaying = false;
	private boolean autocompleted;
	private boolean parametersChosen = true;

	// locks
	private Object tempCostWaiting = new Object();
	private Object actionWaiting = new Object();
	private Object waitingForAutocompleting = new Object();
	private Object waitingForSalesChoice = new Object();
	private Object waitingForParametersChoose = new Object();
	private String tempCostString = new String();

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

		waitAndAutocomplete();

		// WAITING FOR AUTOCOMPLETING
		synchronized (waitingForAutocompleting) {
			while (!autocompleted) {
				try {
					waitingForAutocompleting.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		game.setModel(game.getPlayers());
		game.setCurrentPlayer(game.getPlayers().get(0));
		game.sendModel();
		this.currentPlayer = game.getCurrentPlayer();

		playerTurn = game.getPlayers();
		game.setGameState(State.PERIOD1_ROUND1);

		while (!game.getGameState().equals(State.ENDED)) {
			System.out.println("GAME STATE: " + game.getGameState());

			game.getBoard().clear();
			System.out.println("1");
			game.getCards().dealCards(game.getBoard(), cardsIndex / 2 + 1);

			game.sendModel();

			System.out.println("Controller: everything clear and model sent");
			for (int j = 0; j < 4; j++) {

				for (int i = 0; i < playerTurn.size(); i++) {
					// one familar gone for each player

					// reset the current player
					this.currentPlayer = game.getCurrentPlayer();
					System.out.println("Current Player is ---> " + this.currentPlayer.getMyName());

					sendCurrentPlayer();

					if (!alreadyPlaying)
						letThemPlay();

					/*
					 * This block waits for a player doing an action, because
					 * after an action the game-currentPlayer is updated
					 */
					Timer t1 = new Timer();
					startTimerForPlayerAction(t1);

					synchronized (actionWaiting) {

						while (this.currentPlayer.equals(game.getCurrentPlayer())) {
							try {
								actionWaiting.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();

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
			cardsIndex++;
			// and repeat everything til state "ENDED"
		}
		gameEndHandler();

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
		}, 100000);

	}

	/**
	 * This method starts a timer and then calls another method that
	 * autocompletes the players
	 */
	private void waitAndAutocomplete() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("*****PLAYER NAME INSERTION TIME UP*****");
				autoCompletePlayers();
			}

		}, 1000);

	}

	/**
	 * This method automatically completes the players name and colours, waking
	 * up the run() thread and notifying the clients
	 */
	public void autoCompletePlayers() {

		for (Player p : game.getPlayers()) {

			if (p.getMyName() == null) {
				int index = game.getPlayers().indexOf(p) + 1;

				p.setMyName("Player_" + index);
				p.setAutocompleted(true);
				System.out.println("Player" + index + "autocompleted with name: " + p.getMyName());

				System.out.println("STO INVIANDO: " + game);
				game.sendModel();

			}

		}
		synchronized (waitingForAutocompleting) {
			autocompleted = true;
			waitingForAutocompleting.notify();
		}

	}

	/**
	 * This method handles the end of the game. 1)Conquered Territories:
	 * 1/4/10/20 Victory Points for 3/4/5/6 Territory Cards on your Personal
	 * Board. 2)Influenced Characters: 1/3/6/10/15/21 Victory Points for
	 * 1/2/3/4/5/6 Character Cards next to your Personal Board. 3)Encouraged
	 * Ventures: The sum of all Victory Points on the Venture Cards next to your
	 * Personal Board. 4)Military Strength: If there is a tie between first
	 * players, they all gain 5 Victory Points and nobody gains 2 Victory
	 * Points. If there is a tie between second players, they all gain 2 Victory
	 * Points. 5)Collected Resources: 1 Victory Point for every 5 resources of
	 * all types. ---> The player with most Victory Points is the winner. In
	 * case of a tie, the player more advanced on the Turn Order is the winner.
	 */
	private void gameEndHandler() {
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
				for (Player p : winners) {
					if (playert.equals(p)) {
						winner = p;

					}
				}
			}
		} else {
			winner = winners.get(0);
		}

		return winner;
	}

	/**
	 * This method calculates the final victory points for each player. based on
	 * the final rules of the game
	 */
	public void giveVictoryPoints() {
		Player player;
		List<Integer> finalMilitaryPoints = new ArrayList<>();

		for (int i = 0; i < game.getPlayers().size(); i++) {
			player = game.getPlayers().get(i);
			player.getMyValues().addTwoSetsOfValues(
					player.getMyValues().getFaithPoints().convertToValue(game.getCorrespondingValue()));
			player.getMyValues().getVictoryPoints()
					.addQuantity(player.getMyBoard().convertToVictoryPoints().getQuantity());
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
	}

	/**
	 * This method convert Military Points to Victory Points though a List of
	 * Integer, this list contains the players' quantity of Military Points
	 * sorted.
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
	 * This methods updates the turn list looking at the Council Palace, after
	 * every round
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
		hashMap = new HashMap<>();
		hashMap.put("Turns", turnArray);
		notifyMyObservers(hashMap);
	}

	private void sendCurrentPlayer() {
		hashMap = new HashMap<>();
		hashMap.put("currentPlayer", this.currentPlayer);
		notifyMyObservers(hashMap);

	}

	/**
	 * This method sends to the clients a simple information to be printed on
	 * the view
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		t1.start();

	}

	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords
	 * in the keySet, it does different things with different objects
	 * 
	 * @throws IOException
	 */
	private String handleRequestFromClient(Map<String, Object> request) throws IOException {

		System.out.println("Controller --> ricevuto una richiesta dal client");

		Set<String> command = request.keySet();
		System.out.println(command);

		if (command.contains("player")) {
			System.out.println("Controller --> Ricevuto un giocatore");
			return handlePlayer(request);

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

		} else if (command.contains("sale")) {
			System.out.println("Controller --> Ricevuta la scelta di uno dei due sconti sul prezzo ");
			SetOfValues setOfSales = (SetOfValues) request.get("sale");
			synchronized (waitingForSalesChoice) {
				this.saleForPermanentEffect = setOfSales;
				waitingForSalesChoice.notify();
			}
			return "sale chosen";

		} 
		/*RMI COMMANDS*/
		
		else if (command.contains("addPlayer")) {
			game.addPlayer();
		}
		

		else {
			System.out.println("Controller --> COMANDO NON RICONOSCIUTO ");
			return "bad command";
		}
		return null;

	}

	private String handlePlayer(Map<String, Object> request) {
		System.out.println("Controller --> Sto gestendo un giocatore");
		String playerString = (String) request.get("player");
		System.out.println(playerString);
		StringTokenizer tokenizer = new StringTokenizer(playerString);
		String clientNumber = tokenizer.nextToken();
		String name = tokenizer.nextToken();
		int indexOfPlayer = Integer.parseInt(clientNumber) - 1;

		Player tempPlayer = game.getPlayers().get(indexOfPlayer);

		tempPlayer.setMyName(name);

		game.sendModel();
		System.out.println("player " + clientNumber + " updated");
		return "player " + clientNumber + " updated";

	}

	// #1
	private void handleAction(Map<String, Object> request) {
		System.out.println("Controller --> Sto gestendo un'azione");
		StringTokenizer tokenizer = new StringTokenizer((String) request.get("action"));

		String tempFamiliar = tokenizer.nextToken();
		String tempZone = tokenizer.nextToken();
		String tempFloor = tokenizer.nextToken();
		String tempServants = tokenizer.nextToken();

		/**
		 * Sees if there's an interactive permanent effect WITH DOUBLE SALE
		 * before doing an action, because this particular effect requires user
		 * interaction
		 */
		IncreaseDieValueCard pe = PermanentEffectWithAlternativeSale();

		if (pe != null && pe.getPersonalCards().getType().equals(tempZone)) {

			askForSale(pe);
			synchronized (waitingForSalesChoice) {
				while (saleForPermanentEffect.equals(new SetOfValues())) {
					try {
						waitingForSalesChoice.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

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
		hashMap = new HashMap<>();
		hashMap.put("sale", pe);
		notifyMyObservers(hashMap);

	}

	private String verifyAction(Action action2) {
		System.out.println("Controller --> Sto verificando ed eseguendo un'azione ");
		String responseToActionVerify = action.verify();
		return responseToActionVerify;
	}

	private void correctActionExecute() {
		System.out.println("Controller --> La verifica dell'azione è andata a buon fine ");
		List<ImmediateEffect> interactiveEffects = action.run();
		this.handleInteractiveEffects(interactiveEffects);
		System.out.println("Controller --> Conclusa gestione dei costi interattivi ");
		notifyToProceedWithTurns();
		game.sendModel();
		awakenSleepingClient();
		System.out.println("Controller --> Richiesta di risveglio inviata");

	}

	private void correctChooseNewCardExecute() {
		System.out.println("Controller --> La verifica dell'azione è andata a buon fine ");
		List<ImmediateEffect> interactiveEffects = action.run();
		this.handleInteractiveEffects(interactiveEffects);
		System.out.println("Controller --> Conclusa gestione dei costi interattivi ");
		game.sendModel();
		// awakenSleepingClient();
		// System.out.println("Controller --> Richiesta di risveglio inviata");

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
				game.sendModel();
				i++;
				System.out.println("Controller --> Gestendo l'effetto specifico #" + i + "of "
						+ interactiveEffects.size() + ": " + effect);

				askAndWaitForParameters(effect);

				System.out.println("Controller -->  Finito di gestire l'effetto specifico#" + i + "of "
						+ interactiveEffects.size() + ": " + effect);

				if (effect instanceof ChooseNewCard) {
					System.out.println("Controller --> è un chooseNewCard, creo l'azione corrispondente");
					String response = createNewActionForChooseNewCard(((ChooseNewCard) effect).getDieValue(), effect);
					// until my choose new card verify is correct I create
					// another one
					while (!response.equals("ok")) {
						response = createNewActionForChooseNewCard(((ChooseNewCard) effect).getDieValue(), effect);
					}
					correctChooseNewCardExecute();

				} else {
					System.out.println("Controller --> non è un chooseNewCard");
					System.out.println("Controller --> faccio il GiveImmediateEffect()");
					effect.giveImmediateEffect(currentPlayer);
					System.out.println("Controller --> fatto il give immediate Effect");
				}

				secondaryInteractiveEffects = effect.addAllNewEffectsToThisSet(secondaryInteractiveEffects);

			}
			if (!secondaryInteractiveEffects.isEmpty()) {
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
	 * This method handles the effect that needs interaction with user it sends
	 * the effect to the client, it recognises them and asks the user to choose.
	 * Then he sends a specific answer and
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (!(effect instanceof ChooseNewCard)) {
				effect.assignParameters(parametersAnswer);
			}
		}
	}

	private void sendToCurrentPlayer(HashMap<String, Object> hashMap2) {
		hashMap2.put("currentPlayer", currentPlayer);
		notifyMyObservers(hashMap2);
	}

	// "Choose sale: (1,2)\n" + "1." + increase.getSale() + "\n2." +
	// increase.getAlternativeSale()

	/***/
	private IncreaseDieValueCard PermanentEffectWithAlternativeSale() {
		Characters c;
		for (Development d : currentPlayer.getMyBoard().getPersonalCharacters().getCards()) {
			c = (Characters) d;
			if (c.getPermanentEffects() != null && c.getPermanentEffects().getName().equals("increaseDieValueCard")) {
				IncreaseDieValueCard pe = (IncreaseDieValueCard) c.getPermanentEffects();
				if (pe.getAlternativeSale() != null) {
					return pe;
				}
			}
		}
		return null;
	}

	private void sendProblemsToCurrentPlayer(String responseToActionVerify) {

		hashMap.put("currentPlayer", currentPlayer.getMyName());
		notifyMyObservers(hashMap);
	}

	/**
	 * If the player wants to take a ventures card, this method let him choose
	 * which one of the double costs to take (if a double cost exists).
	 */
	private void handleVentures(String tempZone, String tempFloor) {
		System.out.println("Controller --> Sto gestendo una carta venture per il doppio costo... ");
		TowerPlace placeRequested = (TowerPlace) this.game.getBoard().getZoneFromString(tempZone)
				.getPlaceFromStringOrFirstIfZero(tempFloor);
		Ventures cardRequested = (Ventures) placeRequested.getCorrespondingCard();
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

			synchronized (tempCostWaiting) {
				while (this.tempCostString.equals(new String())) {
					try {
						tempCostWaiting.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						Thread.currentThread().interrupt();

					}
				}

			}
			if (this.tempCostString.equals("1")){
				tempCost = cost1;
			} else {
				tempCost = cost2;
			}

			System.out.println("Controller --> L'utente ha scelto, mi sono risvegliato ");

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

}
