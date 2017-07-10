package it.polimi.ingsw.GC_24.network.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;

public class ServerRMIView extends MyObservable implements ServerViewRemote, MyObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -971969735113991230L;

	HashMap<String, Object> hm = new HashMap<>();

	private ArrayList<ClientViewRemote> clients;
	private ArrayList<String> names = new ArrayList<>();
	private int i = 0;

	public ServerRMIView() {
		this.clients = new ArrayList<ClientViewRemote>();

	}

	@Override
	public void registerClient(ClientViewRemote clientStub) throws RemoteException {
		i++;
		this.clients.add(clientStub);
		String name = clientStub.getPlayerName();
		names.add(name);

	}

	private void handleDisconnection(int i) {
		
		System.out.println(names.get(i)+" DISCONNESSO");
		String name = names.get(i);
		clients.remove(i);
		sendNameDisconnectedToController(name);
	}

	private void sendNameDisconnectedToController(String name) {
		hm = new HashMap<>();
		hm.put("ClientRMIClosed", name);
		notifyMyObservers(hm);
	}

	@Override
	public <C> void update(C change) {
		HashMap<String, Object> request = (HashMap<String, Object>) change;
		System.out.println(
				"*******************RMI SERVER VIEW***************************\n \t \t RECEIVED THIS " + change);

		try {
			this.handleRequestFromServer(request);

		} catch (RemoteException e1) {
			for (int i = 0; i < clients.size(); i++) {
				try {
					clients.get(i).ping();
				} catch (RemoteException e2) {
					handleDisconnection(i);
				}
			}
		}
	}

	private void handleRequestFromServer(HashMap<String, Object> request) throws RemoteException {
		Set<String> command = request.keySet();
		for (ClientViewRemote clientstub : this.clients) {

			if (command.contains("currentPlayerName")) {
				String currentPlayerName = (String) request.get("currentPlayerName");
				;

				if (currentPlayerName.equals(clientstub.getPlayerName())) {

					if (command.contains("exchangeParamRequest")) {
						;
						String question = (String) request.get("exchangeParamRequest");
						String answer = clientstub.askForExchange(question);
						sendAnswerForParameters(answer);
					}
					if (command.contains("activityParamRequest")) {
						;
						String question = (String) request.get("activityParamRequest");
						String answer = clientstub.askForServantsForHarvestOrProduction(question);
						sendAnswerForParameters(answer);
					}

					if (command.contains("councilParamRequest")) {
						;
						String question = (String) request.get("councilParamRequest");
						String answer = clientstub.askForCouncilPrivilege(question);
						sendAnswerForParameters(answer);
					}

					if (command.contains("chooseNewCard")) {
						;
						String question = (String) request.get("chooseNewCard");
						String answer = clientstub.askForChooseNewCard(question);
						sendAnswerForParameters(answer);
					}

					if (command.contains("vatican")) {
						;
						String answer = clientstub.askForVatican();
						sendAnswerVatican(answer);
					}

					if (command.contains("doubleCost")) {
						;
						String response = clientstub.chooseAlternativeCost((String) request.get("doubleCost"));
						sendAlternativeCost(response);
					}

					if (command.contains("urlExcommunication")) {
						ArrayList<String> urlExcommunication = (ArrayList<String>) request.get("urlExcommunication");
						clientstub.updateUrlExcommunication(urlExcommunication);
					}

					if (command.contains("problems")) {
						;
						clientstub.show((String) request.get("problems"));
					}

					if (command.contains("personalInformation")) {
						clientstub.parsePersonalInformations((String[]) request.get("personalInformation"));
					}
					if (command.contains("sale")) {
						;
						String alternativeSale = clientstub.chooseAlternativeSale((String) request.get(command));
						sendAlternativeSale(alternativeSale);
					}

					if (command.contains("urlPersonalBoard")) {
						;
						ArrayList<String> urlPersonalBoard = (ArrayList<String>) request.get("urlPersonalBoard");
						clientstub.updateUrlPersonalBoard(urlPersonalBoard);
					}

				}

			}

			/* IN THIS CASE the request is handled by the viewCLI */

			if (command.contains("boardInformation")) {
				clientstub.parseBoardInformations((String[]) request.get("boardInformation"));
			}

			if (command.contains("actionDone")) {
				;
				clientstub.signalCompletedAction();

			}

			if (command.contains("rankings")) {
				;
				clientstub.updateRankings((String) request.get("rankings"));

			}

			if (command.contains("urlExcommunication")) {
				ArrayList<String> urlExcommunication = (ArrayList<String>) request.get("urlExcommunication");
				clientstub.updateUrlExcommunication(urlExcommunication);
			}

			if (command.contains("urlBoard")) {
				ArrayList<String> urlBoard = (ArrayList<String>) request.get("urlBoard");
				clientstub.updateUrlBoard(urlBoard);
			}

			if (command.contains("urlColour")) {
				ArrayList<String> urlColour = (ArrayList<String>) request.get("urlColour");
				clientstub.updateUrlColour(urlColour);
			}

			if (command.contains("info")) {
				;
				clientstub.show((String) request.get("info"));

			}

			if (command.contains("startPlaying")) {
				;
				clientstub.startPlaying();

			}
			if (command.contains("Turns")) {
				;
				ArrayList<String> playerTurn = (ArrayList<String>) request.get("Turns");
				clientstub.updateTurn(playerTurn);

			}
			if (command.contains("currentPlayer")) {
				;
				String currentPlayer = (String) request.get("currentPlayer");
				clientstub.updateCurrentPlayerAndSetMyTurn(currentPlayer);

			}

		}

	}

	@Override
	public void sendPlayerString(String clientNumberAndName) throws RemoteException {
		hm = new HashMap<>();
		hm.put("player", clientNumberAndName);
		notifyMyObservers(hm);

	}

	@Override
	public void sendAction(String action) throws RemoteException {
		hm = new HashMap<>();
		hm.put("action", action);
		notifyMyObservers(hm);

	}

	@Override
	public void sendAnswerForParameters(String answer) throws RemoteException {
		hm = new HashMap<>();
		hm.put("answerForParameters", answer);
		notifyMyObservers(hm);

	}

	@Override
	public void sendAlternativeCost(String alternativeCost) throws RemoteException {
		hm = new HashMap<>();
		hm.put("chosenCost", alternativeCost);
		notifyMyObservers(hm);

	}

	@Override
	public void sendAlternativeSale(String alternativeSale) throws RemoteException {
		hm = new HashMap<>();
		hm.put("answerForsale", alternativeSale);
		notifyMyObservers(hm);

	}

	@Override
	public void sendLeader(String request) {
		hm = new HashMap<>();
		hm.put("leader", request);
		notifyMyObservers(hm);
	}

	@Override
	public void sendAnswerVatican(String request) {
		hm = new HashMap<>();
		hm.put("answerForVatican", request);
		notifyMyObservers(hm);
	}
}