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
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.*;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.*;


public class Controller extends MyObservable implements MyObserver, Runnable {

		private final Model game;
		private ActionFactory actionFactory = new ActionFactory();
		private SetOfValues tempCost = new SetOfValues();
		private Action action=null;
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
		private boolean parametersChosen = false;


		// locks
		private Object tempCostWaiting = new Object();
		private Object actionWaiting = new Object();
		private Object waitingForAutocompleting = new Object();
		private Object waitingForSalesChoice = new Object();
		private Object waitingForParametersChoose = new Object();

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

						
						this.currentPlayer = game.getCurrentPlayer();
						System.out.println("Current Player is ---> "+this.currentPlayer.getMyName());
					
						sendCurrentPlayer();
						
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

		
		

		/**This method starts a timer and then calls another method that autocompletes the players*/
		private void waitAndAutocomplete() {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					System.out.println("*****PLAYER NAME INSERTION TIME UP*****");
					autoCompletePlayers();
				}

			}, 5000);

		}

		/**
		 * This method automatically completes the players name and colours,
		 * waking up the run() thread and notifying the clients
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


		
	/**This methods returns the winner of the game using victoryPoints*/

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

		/**This method calculates the final victory points for each player. based on the final rules of the game*/
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

		/**This methods updates the turn list looking at the Council Palace, after every round*/
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

		/**This methods makes the users start playing, calling a method on the view */
		private void letThemPlay() {
			alreadyPlaying = true;
			hashMap = new HashMap<>();
			hashMap.put("startPlaying", "---> game starts here");
			notifyMyObservers(hashMap);

		}
		/**This method sends to the clients the turn array to be updated*/
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

		/**This method sends to the clients a simple information to be printed on the view*/
		private void sendInfo(String string) {
			hashMap = new HashMap<>();
			hashMap.put("info", string);
			notifyMyObservers(hashMap);
		}
		

		@SuppressWarnings("unchecked")
		@Override
		public synchronized <C> void update(MyObservable o, C change) {

			System.out.println("Controller: I have been notified by " + o.getClass().getSimpleName());
			System.out.println("Controller: i received this :" + change);

			
				Thread t1 = new Thread(new Runnable() {
					public void run(){
						String answer;
						try {
							answer = handleRequestFromClient(o, (Map<String, Object>) change);
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
		private String handleRequestFromClient(MyObservable o, Map<String, Object> request) throws IOException {

			System.out.println("Controller: started handling a request from client...");
			Set<String> command = request.keySet();
			System.out.println(command);

			if (command.contains("player")) {

				return handlePlayer(request);

			}

			else if (command.contains("chosenCost")) {
				synchronized (tempCostWaiting) {
					this.tempCost = (SetOfValues) request.get("chosenCost");
					tempCostWaiting.notify();
				}
				return "Controller: chosen cost updated";

			}

			else if (command.contains("action")) {
				handleAction(o, request);
				verifyAndExecuteAction(o, this.action);
			}

			else if (command.contains("answerForParameters")) {

				synchronized (waitingForParametersChoose) {
					this.parametersAnswer = (String) request.get("answerForParameters");
					this.parametersChosen = true;
					waitingForParametersChoose.notify();
				}
				return "parameters updated";
			
			} else if (command.contains("sale")) {
				SetOfValues setOfSales = (SetOfValues) request.get("sale");
				synchronized (waitingForSalesChoice) {
					this.saleForPermanentEffect = setOfSales;
					waitingForSalesChoice.notify();
				}
				return "sale chosen";

			}

			else {
				return "bad command";
			}
			return null;

		}

		private String handlePlayer(Map<String, Object> request) {
			System.out.println("IO CONTROLLER HO RICEVUTO: " + request);
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

		private void handleAction(MyObservable o, Map<String, Object> request) {
			System.out.println("Controller: started handling action");
					
			StringTokenizer tokenizer = new StringTokenizer((String) request.get("action"));
			
			String tempFamiliar = tokenizer.nextToken();
			String tempZone = tokenizer.nextToken();
			String tempFloor = tokenizer.nextToken();
			String tempServants = tokenizer.nextToken();
		
			IncreaseDieValueCard pe = PermanentEffectWithAlternativeSale();
			
			if (pe != null && pe.getPersonalCards().getType().equals(tempZone)) {
				notifyMyObservers(new HashMap().put("sale", pe));
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

				handleVentures(o, tempZone, tempFloor);
			}
			this.action = actionFactory.makeAction(game, tempFamiliar, tempZone, tempFloor, tempServants, tempCost, saleForPermanentEffect);
			
		}

		private void verifyAndExecuteAction(MyObservable o, Action action2) {
			String responseToActionVerify = action.verify();
	
			if (responseToActionVerify.equals("ok")) {
				List<ImmediateEffect> interactiveEffects = action.run();
				this.handleInteractiveEffects(o, interactiveEffects);
				System.out.println("Gestiti eventuali effetti interattivi");

				synchronized (actionWaiting) {
					
					if (playerTurn.indexOf(game.getCurrentPlayer()) == playerTurn.size()-1){
						game.setCurrentPlayer(playerTurn.get(0));
					} else {
						game.setCurrentPlayer(playerTurn.get(playerTurn.indexOf(game.getCurrentPlayer()) + 1));
					}
						actionWaiting.notify();
				}
				System.out.println("SUPERATO BLOCCO actionwaiting");	
				
				
			} else {
				/*ACTION FAILS --> if it's a normal action, do nothing, the player would start again and create a new one
				 * --> if it's a chooseNewCard action, I should ask the user to choose another card*/
				sendProblems(o, responseToActionVerify);

			}
			/*
			 * Hai compiuto un'azione, al giocatore resta la possibilità di giocare
			 * una carta leader --> TODO: gestione delle carte leader e conseguente
			 * scelta del giocatore di ultimare il turno
			 */

			// per adesso finisco il turno --> Aggiorno il currentPlayer e
			// sveglio il run();
		
			// Ho modificato il model. Lo invio!
			game.sendModel();
			awakenSleepingClient();

		}

		private void awakenSleepingClient() {
			hashMap = new HashMap<>();
			hashMap.put("actionDone", "awakeningTheClients");
			notifyMyObservers(hashMap);
			}
		/*Subito dopo run()*/
		private void handleInteractiveEffects(MyObservable o, List<ImmediateEffect> interactiveEffects) {
			List<ImmediateEffect> secondaryInteractiveEffects = new ArrayList<>();
			if (!interactiveEffects.isEmpty()) {
				for (ImmediateEffect effect : interactiveEffects) {
					
					handleEffects(o, effect);
					System.out.println("Sono uscito da handleEffect");
					if (effect instanceof ChooseNewCard) {
						System.out.println("Creando un azione corrispondente");
						createNewActionForChooseNewCard(o, ((ChooseNewCard) effect).getDieValue());
						System.out.println("Creata un'azione corrispondente");
						
					} else {
						effect.giveImmediateEffect(currentPlayer);
					}

					if (effect instanceof Exchange) {
						secondaryInteractiveEffects.addAll(((Exchange) effect).getImmediateEffectsFromExchange());
					}
					if (effect instanceof PerformHarvest) {
						secondaryInteractiveEffects.addAll(((PerformHarvest) effect).getImmediateEffectsHarvest());
					}
					if (effect instanceof PerformProduction) {
						secondaryInteractiveEffects.addAll(((PerformProduction) effect).getImmediateEffectsProduction());
					}
				}
				if (!secondaryInteractiveEffects.isEmpty()) {
					handleInteractiveEffects(o, secondaryInteractiveEffects);
				}

	    }
		}

		private void createNewActionForChooseNewCard(MyObservable o, int dieValue) {
			String finalActionRequest = "fakeFamiliarForChooseNewCard "+ this.parametersAnswer ;
			System.out.println("@Creating a new Fake Action for choose new card");
			hashMap = new HashMap<>();
			hashMap.put("action", finalActionRequest);
			handleAction(o,hashMap);
			System.out.println("@@@###@@@###");
			System.out.println(this.action);
			System.out.println(this.action.getFamilyMember());
			this.action.getFamilyMember().setMemberValue(dieValue);
			verifyAndExecuteAction(o, this.action);
		}

		/**
		 * This method handles the effect that needs interaction with user it sends
		 * the effect to the client, it recognises them and asks the user to choose.
		 * Then he sends a specific answer and
		 * @param o 
		 */
		private void handleEffects(MyObservable o, ImmediateEffect effect) {

			if (effect instanceof ChooseNewCard || effect instanceof CouncilPrivilege || effect instanceof PerformActivity
					|| (effect instanceof Exchange && ((Exchange) effect).getExchangePackage1() != null)) {

				hashMap = new HashMap<>();
				hashMap.put("askForParameters", effect);
				notifySingleObserver((MyObserver) o, hashMap);

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
				
				System.out.println("ho ricevuto i parametri che hai scritto, mi sto svegliando");

				/*
				 * I have received an answer from the client with his choice. I have
				 * to switch between my effects in order to choose how to handle the
				 * client answer
				 */
				
				
				
			
				
				
				if (effect instanceof CouncilPrivilege) {
					System.out.println("era un effetto consiglio");

					// THE ANSWER IS SUPPOSED TO BE LIKE "1 5 6" --> the chosen
					// privileges. It's handled directly in councilPrivilege
					((CouncilPrivilege) effect).assignParameters(parametersAnswer);
				}


				if (effect instanceof Exchange) {
					System.out.println("era un effetto exchange");
					// THE ANSWER IS SUPPOSED TO BE LIKE "Territory 1" --> that is
					// tower
					// and floor
					((Exchange) effect).assignParameters(Integer.parseInt((new StringTokenizer(parametersAnswer).nextToken())));
				}
				// i parametri sono stati scelti e passati all'effetto
				if (effect instanceof PerformActivity) {
					System.out.println("era un effetto harvest o production");
					// THE ANSWER IS SUPPOSED TO BE LIKE "1" that represents the
					// number
					// of servants you want to use
					((PerformActivity) effect)
							.assignParameters(Integer.parseInt((new StringTokenizer(parametersAnswer).nextToken())));
				}
			}
		}

		private IncreaseDieValueCard PermanentEffectWithAlternativeSale() {
			Characters c;
			for (Development d : currentPlayer.getMyBoard().getPersonalCharacters().getCards()) {
				c = (Characters) d;
				if (c.getPermanentEffects()!=null && c.getPermanentEffects().getName().equals("increaseDieValueCard")) {
					IncreaseDieValueCard pe = (IncreaseDieValueCard) c.getPermanentEffects();
					if (pe.getAlternativeSale() != null) {
						return pe;
					}
				}
			}
			return null;
		}

		private void sendProblems(MyObservable o, String responseToActionVerify) {
			hashMap = new HashMap<>();
			hashMap.put("problems", responseToActionVerify);
			notifySingleObserver((MyObserver) o, hashMap);
		}

		/**
		 * If the player wants to take a ventures card, this method let him choose
		 * which one of the double costs to take (if a double cost exists). The
		 * thread
		 */
		private void handleVentures(MyObservable o, String tempZone, String tempFloor) {
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
					while (this.tempCost.isEmpty()) {
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

		public int getControllerNumber() {
			return controllerNumber;
		}

		public void setControllerNumber(int controllerNumber) {
			this.controllerNumber = controllerNumber;
	  }
}



