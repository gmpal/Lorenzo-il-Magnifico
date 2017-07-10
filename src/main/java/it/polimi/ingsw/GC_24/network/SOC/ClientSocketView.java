package it.polimi.ingsw.GC_24.network.SOC;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;
import it.polimi.ingsw.GC_24.view.View;

//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientSocketView extends MyObservable implements Runnable, MyObserver {

	private ObjectInputStream objFromServer;
	private ObjectOutputStream objToServer;
	private ExecutorService executor = Executors.newCachedThreadPool();
	private View view;

	public ClientSocketView(ObjectInputStream objFromServer, ObjectOutputStream objToServer, View view) {
		this.objToServer = objToServer;
		this.objFromServer = objFromServer;
		this.view = view;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		int i = 0;

		try {
			while (true) {
				i++;
			

				HashMap<String, Object> requestFromServer;

				requestFromServer = (HashMap<String, Object>) objFromServer.readObject();

				
				executor.submit(new Thread(new Runnable() {
					public void run() {
						
						handleRequestFromServer(requestFromServer);
					}
				}));
			}
		} catch (EOFException e) {
			;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public synchronized <C> void update(C change) {
		try {
			objToServer.writeObject(change);
			objToServer.flush();
			objToServer.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Based on the key of the object received, this method handles the request
	 */
	@SuppressWarnings("unchecked")
	public synchronized void handleRequestFromServer(Map<String, Object> request) {
		Set<String> command = request.keySet();

		if (command.contains("currentPlayerName")) {
			String currentPlayerName = (String) request.get("currentPlayerName");
			if (currentPlayerName.equals(view.getName())) {

				if (command.contains("exchangeParamRequest")) {
					String question = (String) request.get("exchangeParamRequest");
					String answer = view.askForExchange(question);
					view.sendAnswerForParameters(answer);
				}
				if (command.contains("activityParamRequest")) {
					String question = (String) request.get("activityParamRequest");
					String answer = view.askForServantsForHarvestOrProduction(question);
					view.sendAnswerForParameters(answer);
				}

				if (command.contains("councilParamRequest")) {
					String question = (String) request.get("councilParamRequest");
					String answer = view.askForCouncilPrivilege(question);
					view.sendAnswerForParameters(answer);
				}

				if (command.contains("chooseNewCard")) {
					String question = (String) request.get("chooseNewCard");
					String answer = view.askForChooseNewCard(question);
					view.sendAnswerForParameters(answer);
				}

				if (command.contains("urlPersonalBoard")) {
					ArrayList<String> urlPersonalBoard = (ArrayList<String>) request.get("urlPersonalBoard");
					view.setUrlPersonalBoard(urlPersonalBoard);
				}

				if (command.contains("doubleCost")) {
					String response = view.chooseAlternativeCost((String) request.get("doubleCost"));
					view.sendAlternativeCost(response);

				}

				if (command.contains("sale")) {
					String response = view.chooseSale((String) request.get("sale"));
					view.sendAlternativeSale(response);
				}
				if (command.contains("problems")) {
					view.show((String) request.get("problems"));
				}
				if (command.contains("vatican")) {
					String response = view.askForExcommunication();
					view.sendAnswerToVatican(response);
				}
				
				if (command.contains("personalInformation")) {
					view.parsePersonalInformations((String[]) request.get("personalInformation"));
				}
			}	
		}
		if (command.contains("boardInformation")) {
			view.parseBoardInformation((String[]) request.get("boardInformation"));

		}

		if (command.contains("actionDone")) {
			view.communicateActionDone();

		}

		if (command.contains("rankings")) {
			view.setRankings((String) request.get("rankings"));

		}

		if (command.contains("urlExcommunication")) {
			view.setUrlExcommunication((ArrayList<String>) request.get("urlExcommunication"));
		}

		if (command.contains("urlBoard")) {
			view.setUrlBoard((ArrayList<String>) request.get("urlBoard"));
		}

		if (command.contains("urlColour")) {
			view.setUrlColour((ArrayList<String>) request.get("urlColour"));
		}

		if (command.contains("info")) {
			view.show((String) request.get("info"));
			if (((String) request.get("info")).contains("The winner of the game is ")) {
				view.disconnectClient();
			}
		}

		if (command.contains("startPlaying")) {
			new Thread(new Runnable() {
				public void run() {
					view.play();
				}
			}).start();
		}

		if (command.contains("Turns")) {
			ArrayList<String> playerTurn = (ArrayList<String>) request.get("Turns");
			view.updateTurn(playerTurn);
		}

		if (command.contains("currentPlayer")) {
			String currentPlayer = (String) request.get("currentPlayer");
			view.setMyTurn(currentPlayer);
		}

		if (command.contains("clientNumber")) {
			int playerNumber = (int) request.get("clientNumber");
			view.setPlayerNumber(playerNumber);
			int modelNumber = (int) request.get("modelNumber");
		}


	}
}