package it.polimi.ingsw.GC_24.client.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

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
		hm = new HashMap<>();
		hm.put("addPlayer", null);
		notifyMyObservers(hm);

	}

	@Override
	public <C> void update(C change) {
		HashMap<String,Object> request = (HashMap<String,Object>) change;
		
		
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

			if (command.contains("currentPlayer")) {
				String currentPlayerName = (String) request.get("currentPlayer");
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

					if (command.contains("problems")) {
						System.out.println("RMI Server View ---> Ricevuti problemi");
						clientstub.show((String) request.get("problems"));
					}

				}
			}

			/* IN THIS CASE the request is handled by the viewCLI */

			if (command.contains("model")) {
				System.out.println("RMI Server View ---> Ricevuto Model");
				clientstub.updateModelAndRelatedFields((Model) request.get("model"));
			}

			if (command.contains("actionDone")) {
				System.out.println("RMI Server View ---> Ricevuta segnalazione di azione completata ");
				clientstub.signalCompletedAction();

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
				ArrayList<Player> playerTurn = (ArrayList<Player>) request.get("Turns");
				clientstub.updateTurn(playerTurn);

			}
			if (command.contains("currentPlayer")) {
				System.out.println("RMI Server View ---> Ricevuto giocatore corrente ");
				Player currentPlayer = (Player) request.get("currentPlayer");
				clientstub.updateCurrentPlayerAndSetMyTurn(currentPlayer);

			}

			if (command.contains("clientNumber")) {
				System.out.println("RMI Server View ---> Ricevuto numero client");
				int playerNumber = (int) request.get("clientNumber");
				int modelNumber = (int) request.get("modelNumber");
				clientstub.setPlayerNumber(playerNumber, modelNumber);

			}
			if (command.contains("sale")) {
				System.out.println("RMI Server View ---> Ricevuta richiesta sconto multiplo");
				SetOfValues alternativeSale = clientstub
						.chooseAlternativeSale((IncreaseDieValueCard) request.get(command));
				sendAlternativeSale(alternativeSale);
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
	public void sendAlternativeSale(SetOfValues alternativeSale) throws RemoteException {
		hm = new HashMap<>();
		hm.put("sale", alternativeSale);
		notifyMyObservers(hm);

	}

}