package it.polimi.ingsw.GC_24.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.cards.Ventures;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.State;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.network.multi.Server;

//Just one server's side controller for each game
public class Controller extends MyObservable implements MyObserver, Runnable {

	private final Model game;
	private ActionFactory actionFactory;
	private SetOfValues tempCost = null;
	private Action action;
	private HashMap<String, Object> hashMap;
	private int controllerNumber = 0;
	private HashMap<String, Object> hm;
	private List<Player> councilTurnArray;
	private List<Player> playerTurn;
	private Player currentPlayer;
	private int cardsIndex = 0;

	private boolean alreadyPlaying = false;

	// locks
	private Object tempCostWaiting = new Object();
	private Object actionWaiting = new Object();

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

		game.setModel(game.getPlayers());
		sendModelToClients();
		this.currentPlayer = game.getCurrentPlayer();

		playerTurn = game.getPlayers();
		game.setGameState(State.PERIOD1_ROUND1);

		while (game.getGameState().equals(State.ENDED)) {
			//END OF ROUND (OR FIRST) --> We need to clear the board
			game.getBoard().clear();
			game.getCards().dealCards(game.getBoard(), cardsIndex/2+1);
			sendModelToClients();
			
			for (int j = 0; j < 4; j++) {
				// one Family gone for each player --> End of round
				for (int i = 0; i < playerTurn.size(); i++) {
					// one familar gone for each player

					game.setCurrentPlayer(playerTurn.get(i));
					sendTurnArray(playerTurn);
					if (!alreadyPlaying)
						letThemPlay();

					/*
					 * This block waits for a player doing an action, because
					 * after an action the game-currentPlayer is updated
					 */
					synchronized (actionWaiting) {

						while (this.currentPlayer.equals(game.getCurrentPlayer())) {
							try {
								actionWaiting.wait();
							} catch (InterruptedException e) {
								// TODO: auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					// reset the current player
					this.currentPlayer = game.getCurrentPlayer();

					/* Repeats until the players are finished */

				}
			}
			// it's time to look at the council palace for turn updates!
			councilTurnArray = game.getBoard().getCouncilPalace().getTemporaryTurn();
			updateListOfPlayerTurn(councilTurnArray);
			// let's go to next state
			game.incrementState();
			cardsIndex++;
			// and repeat everything til state "ENDED"
		}
		gameEndHandler();
	
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
		// TODO:riempire questo metodo
	}

	// update the turn list from the councilPalace
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

	private void letThemPlay() {
		alreadyPlaying = true;
		hm = new HashMap<>();
		hm.put("Play!", null);
		notifyMyObservers(hm);

	}

	private void sendTurnArray(List<Player> turnArray) {
		hm = new HashMap<>();
		hm.put("Turns", turnArray);
		notifyMyObservers(hm);
	}

	@Override
	public void update() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <C> void update(MyObservable o, C change) {

		System.out.println("Controller: I have been notified by " + o.getClass().getSimpleName());
		System.out.println("Controller: i received this :" + change);

		try {
			String answer = handleRequestFromClient(o, (Map<String, Object>) change);
			System.out.println("--------------" + answer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords
	 * in the keySet, it does different things with different objects
	 * 
	 * @throws IOException
	 */
	private String handleRequestFromClient(MyObservable o, Map<String, Object> request) throws IOException {

		System.out.println("Controller: started handling a request from client...");
		Set<String> command = request.keySet();
		System.out.println(command);

		if (command.contains("player")) {

			return handlePlayer(request);

		}

		else if (command.contains("colours")) {
			return handleColours(o, request);
		}

		else if (command.contains("chosenCost")) {
			synchronized (tempCostWaiting) {
				this.tempCost = (SetOfValues) request.get("chosenCost");
				tempCostWaiting.notify();
			}
			return "Controller: chosen cost updated";

		}

		else if (command.contains("action")) {

			return handleAction(o, request);
		}

		/* Checks if the colour has already been chosen */
		else if (command.contains("checkColour")) {
			return checkColour(o, request);
		}

		else {
			return "bad command";
		}

	}

	private String handlePlayer(Map<String, Object> request) {
		String playerString = (String) request.get("player");
		System.out.println(playerString);
		StringTokenizer tokenizer = new StringTokenizer(playerString);
		String clientNumber = tokenizer.nextToken();
		String name = tokenizer.nextToken();
		String colour = tokenizer.nextToken();
		int indexOfPlayer = Integer.parseInt(clientNumber) - 1;

		Player tempPlayer = game.getPlayers().get(indexOfPlayer);

		tempPlayer.setPlayer(name, PlayerColour.valueOf(colour.toUpperCase()));

		game.sendModel();
		return "player " + clientNumber + " updated";

	}

	private String checkColour(MyObservable o, Map<String, Object> request) {
		System.out.println("Controller: started checking colour!");
		String colour = (String) request.get("checkColour");
		String availability;
		if (PlayerColour.checkValue(colour)) {
			availability = "Colour Available";
			PlayerColour.removeValue(colour);
		} else {
			availability = "Colour Not Available";
		}
		hashMap = new HashMap<>();
		hashMap.put("coloursAnswer", availability);
		this.notifySingleObserver((MyObserver) o, hashMap);
		return "Controller: Colour checked";
	}

	private String handleColours(MyObservable o, Map<String, Object> request) {
		System.out.println("Controller: started handling colours");
		List<String> playerColoursArray = PlayerColour.getValues();
		HashMap<String, Object> coloursMap = new HashMap<String, Object>();
		coloursMap.put("colours", playerColoursArray);
		this.notifySingleObserver((MyObserver) o, coloursMap);
		System.out.println("ServerOut: ArrayListOfColours sent");
		return " ArrayListOfColours sent";
	}

	private Player createPlayerFromString(String playerString) {
		StringTokenizer tokenizer = new StringTokenizer(playerString);
		String name = tokenizer.nextToken();
		String colour = tokenizer.nextToken();
		Player player = new Player();
		player.setPlayer(name, PlayerColour.valueOf(colour.toUpperCase()));
		notifyAll();
		return player;

	}

	public void sendModelToClients() {
		System.out.println("Sono il controller numero" + this.getControllerNumber());
		System.out.println("Sto inviando il game numero" + game.getModelNumber());
		hashMap = new HashMap<>();
		hashMap.put("model", game);
		notifyMyObservers(hashMap);
	}

	public int getControllerNumber() {
		return controllerNumber;
	}

	public void setControllerNumber(int controllerNumber) {
		this.controllerNumber = controllerNumber;
	}

	private String handleAction(MyObservable o, Map<String, Object> request) {
		System.out.println("Controller: started handling action");
		StringTokenizer tokenizer = new StringTokenizer((String) request.get("action"));

		String tempFamiliar = tokenizer.nextToken();
		String tempZone = tokenizer.nextToken();
		String tempFloor = tokenizer.nextToken();
		String tempServants = tokenizer.nextToken();

		if (tempZone.equalsIgnoreCase("ventures")) {

			handleVentures(o, request, tempZone, tempFloor);
		}

		this.action = actionFactory.makeAction(game, tempFamiliar, tempZone, tempFloor, tempServants, tempCost);
		String responseToActionVerify = action.verify();
		if (responseToActionVerify.equals("ok")) {

			List<ImmediateEffect> interactiveEffects = action.run();
			if (!interactiveEffects.isEmpty()) {
				// TODO: interagisci con l'utente per prenderti i parametri
				// che ti servono
			}

		} else {
			sendProblems(o, responseToActionVerify);

		}
		/*Hai compiuto un'azione, al giocatore resta la possibilità di giocare una carta leader 
		--> TODO: gestione delle carte leader e conseguente scelta del giocatore di ultimare il turno*/
		
		//per adesso finisco il turno --> Aggiorno il currentPlayer e sveglio il run();
		synchronized (actionWaiting){
			game.setCurrentPlayer(playerTurn.get(playerTurn.indexOf(game.getCurrentPlayer())+1));
			actionWaiting.notify();
		}
		//Ho modificato il model. Lo invio! 
		sendModelToClients();
	
		return "Azione effettuata";
	}

	
	private void sendProblems(MyObservable o, String responseToActionVerify) {
		hm = new HashMap<>();
		hm.put("problems",responseToActionVerify);
		notifySingleObserver((MyObserver) o, hm);
	}

	/**
	 * If the player wants to take a ventures card, this method let him choose
	 * which one of the double costs to take (if a double cost exists). The
	 * thread
	 */
	private void handleVentures(MyObservable o, Map<String, Object> request, String tempZone, String tempFloor) {
		TowerPlace placeRequested = (TowerPlace) this.game.getBoard().getZoneFromString(tempZone)
				.getPlaceFromStringOrFirstIfZero(tempFloor);
		Ventures cardRequested = (Ventures) placeRequested.getCorrespondingCard();
		SetOfValues cost1 = cardRequested.getCost();
		SetOfValues cost2 = cardRequested.getAlternativeCost();
		if (cost2 != null) {
			MilitaryPoint requirements = cardRequested.getRequiredMilitaryPoints();
			hashMap = new HashMap<>();
			hashMap.put("Cost1", cost1);
			hashMap.put("Cost2", cost2);
			hashMap.put("Requirements", requirements);
			this.notifySingleObserver((MyObserver) o, hashMap);

			synchronized (tempCostWaiting) {
				while (this.tempCost == null) {
					try {
						tempCostWaiting.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			System.out.println("The user has chosen the double cost he wants");
		}

	}

	// game's getter
	public Model getGame() {
		return game;
	}

}
