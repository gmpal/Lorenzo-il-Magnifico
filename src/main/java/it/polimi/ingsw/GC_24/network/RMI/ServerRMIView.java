package it.polimi.ingsw.GC_24.network.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;

//TODO: davvero con le hashMap?
public class ServerRMIView extends MyObservable implements ServerViewRemote, MyObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -971969735113991230L;

	HashMap<String, Object> hm = new HashMap<>();

	private Set<ClientViewRemote> clients;
	private int i = 0;

	public ServerRMIView() {
		this.clients = new HashSet<>();
	}

	@Override
	public void registerClient(ClientViewRemote clientStub) throws RemoteException {
		i++;
		System.out.println("CLIENT #" + i + " REGISTRATO");
		this.clients.add(clientStub);

	}

	@Override
	public <C> void update(C change) {
		HashMap<String, Object> request = (HashMap<String, Object>) change;
		System.out.println(
				"*******************RMI SERVER VIEW***************************\n \t \t RECEIVED THIS " + change);

		try {
			this.handleRequestFromServer(request);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void handleRequestFromServer(HashMap<String, Object> request) throws RemoteException {
		Set<String> command = request.keySet();
		for (ClientViewRemote clientstub : this.clients) {

			if (command.contains("currentPlayerName")) {
				String currentPlayerName = (String) request.get("currentPlayerName");
				System.out.println("RMI Server View --> Ricevuto qualcosa per un singolo giocatore");

				if (currentPlayerName.equals(clientstub.getPlayerName())) {

					if (command.contains("exchangeParamRequest")) {
						System.out.println("RMI Server View ---> Ricevuta richiesta exchangeParamRequest");
						String question = (String) request.get("exchangeParamRequest");
						String answer = clientstub.askForExchange(question);
						sendAnswerForParameters(answer);
					}
					if (command.contains("activityParamRequest")) {
						System.out.println("RMI Server View ---> Ricevuta richiesta activityParamRequest");
						String question = (String) request.get("activityParamRequest");
						String answer = clientstub.askForServantsForHarvestOrProduction(question);
						sendAnswerForParameters(answer);
					}

					if (command.contains("councilParamRequest")) {
						System.out.println("RMI Server View ---> Ricevuta richiesta councilParamRequest");
						String question = (String) request.get("councilParamRequest");
						String answer = clientstub.askForCouncilPrivilege(question);
						sendAnswerForParameters(answer);
					}

					if (command.contains("chooseNewCard")) {
						System.out.println("RMI Server View ---> Ricevuta richiesta chooseNewCard");
						String question = (String) request.get("chooseNewCard");
						String answer = clientstub.askForChooseNewCard(question);
						sendAnswerForParameters(answer);
					}

					if (command.contains("doubleCost")) {
						System.out.println("RMI Server View --> Ricevuto un costo alternativo");
						String response = clientstub.chooseAlternativeCost((String) request.get("doubleCost"));
						sendAlternativeCost(response);

					}
					
					if (command.contains("urlExcommunication")) {
						ArrayList<String> urlExcommunication = (ArrayList<String>) request.get("urlExcommunication");
						clientstub.updateUrlExcommunication(urlExcommunication);
					}

					if (command.contains("problems")) {
						System.out.println("RMI Server View ---> Ricevuti problemi");
						clientstub.show((String) request.get("problems"));
					}

					if (command.contains("personalInformation")) {
						clientstub.parsePersonalInformations((String[]) request.get("personalInformation"));
					}
					if (command.contains("sale")) {
						System.out.println("RMI Server View ---> Ricevuta richiesta sconto multiplo");
						String alternativeSale = clientstub.chooseAlternativeSale((String) request.get(command));
						sendAlternativeSale(alternativeSale);
					}

					if (command.contains("vatican")) {
						System.out.println("RMI Server View ---> Ricevuta richiesta vaticano");
						clientstub.askForVatican();
					}
				}

			}

			/* IN THIS CASE the request is handled by the viewCLI */

			if (command.contains("boardInformation")) {
				clientstub.parseBoardInformations((String[]) request.get("boardInformation"));
			}

			if (command.contains("actionDone")) {
				System.out.println("RMI Server View ---> Ricevuta segnalazione di azione completata ");
				clientstub.signalCompletedAction();

			}
			
			if (command.contains("rankings")) {
				System.out.println("RMI Server View ---> Ricevuta rankings ");
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
				System.out.println("RMI Server View ---> Ricevute informazioni da mostrare a video");
				clientstub.show((String) request.get("info"));

			}

			if (command.contains("startPlaying")) {
				System.out.println("RMI Server View ---> Ricevuta richiesta di avviamento partita");
				clientstub.startPlaying();

			}
			if (command.contains("Turns")) {
				System.out.println("RMI Server View ---> Ricevuti turni ");
				ArrayList<String> playerTurn = (ArrayList<String>) request.get("Turns");
				clientstub.updateTurn(playerTurn);

			}
			if (command.contains("currentPlayer")) {
				System.out.println("RMI Server View ---> Ricevuto giocatore corrente ");
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