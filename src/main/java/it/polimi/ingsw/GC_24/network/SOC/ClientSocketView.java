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

import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueCard;
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
				System.out.println("Ricezione numero " + i);

				HashMap<String, Object> requestFromServer;

				requestFromServer = (HashMap<String, Object>) objFromServer.readObject();

				System.out.println("CSV ---> CASTATO");
				executor.submit(new Thread(new Runnable() {
					public void run() {
						System.out.println("Entrato in run");
						handleRequestFromServer(requestFromServer);
					}
				}));

			}
		} catch (EOFException e) {
			System.out.println("End Of File reached");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public synchronized <C> void update(C change) {
		try {
			System.out.println("------------------------------------->SENDING " + change);
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
		System.out.println("CSV ---> Gestendo una richiesta ");
		Set<String> command = request.keySet();

		if (command.contains("currentPlayerName")) {
			System.out.println("CSV --> Ricevuto qualcosa per un singolo giocatore");
			String currentPlayerName = (String) request.get("currentPlayerName");
			// TODO:alternativa al cast
			if (currentPlayerName.equals(view.getName())) {

				if (command.contains("exchangeParamRequest")) {
					System.out.println("CSV ---> Ricevuta richiesta exchangeParamRequest");
					String question = (String) request.get("exchangeParamRequest");
					String answer = view.askForExchange(question);
					view.sendAnswerForParameters(answer);
				}
				if (command.contains("activityParamRequest")) {
					System.out.println("CSV ---> Ricevuta richiesta activityParamRequest");
					String question = (String) request.get("activityParamRequest");
					String answer = view.askForServantsForHarvestOrProduction(question);
					view.sendAnswerForParameters(answer);
				}

				if (command.contains("councilParamRequest")) {
					System.out.println("CSV ---> Ricevuta richiesta councilParamRequest");
					String question = (String) request.get("councilParamRequest");
					String answer = view.askForCouncilPrivilege(question);
					view.sendAnswerForParameters(answer);
				}

				if (command.contains("chooseNewCard")) {
					System.out.println("CSV ---> Ricevuta richiesta chooseNewCard");
					String question = (String) request.get("chooseNewCard");
					String answer = view.askForChooseNewCard(question);
					view.sendAnswerForParameters(answer);
				}

				if (command.contains("doubleCost")) {
					System.out.println("CSV --> Ricevuto un costo alternativo");
					String response = view.chooseAlternativeCost((String) request.get("doubleCost"));
					view.sendAlternativeCost(response);

				}
				
				if (command.contains("sale")) {
					System.out.println("CSV ---> Ricevuta richiesta sconto multiplo");
					String response = view.chooseSale((String) request.get(command));
					view.sendAlternativeSale(response);
				}
				if (command.contains("problems")) {
					System.out.println("CSV ---> Ricevuti problemi");
					view.show((String) request.get("problems"));
				}

				if (command.contains("personalInformation")) {
					view.parsePersonalInformations((String[]) request.get("personalInformation"));
				}
			}
		}
			if (command.contains("boardInformation")) {
				System.out.println("CSV ---> Ricevute information board");
				view.parseBoardInformation((String[]) request.get("boardInformation"));

			}

			if (command.contains("actionDone")) {
				System.out.println("CSV ---> Ricevuta segnalazione di azione completata ");
				view.communicateActionDone();

			}

			if (command.contains("info")) {
				System.out.println("CSV ---> Ricevute informazioni da mostrare a video");
				view.show((String) request.get("info"));

			}

			if (command.contains("startPlaying")) {
				System.out.println("CSV ---> Ricevuta richiesta di avviamento partita");
				new Thread(new Runnable() {
					public void run() {
						view.play();
					}
				}).start();

			}
			if (command.contains("Turns")) {
				System.out.println("CSV ---> Ricevuti turni ");
				ArrayList<String> playerTurn = (ArrayList<String>) request.get("Turns");
				view.updateTurn(playerTurn);

			}
			if (command.contains("currentPlayer")) {
				System.out.println("CSV ---> Ricevuto giocatore corrente ");
				String currentPlayer = (String) request.get("currentPlayer");
				view.setMyTurn(currentPlayer);

			}

			if (command.contains("clientNumber")) {
				System.out.println("CSV ---> Ricevuto numero client");
				System.out.println(request);
				int playerNumber = (int) request.get("clientNumber");
				System.out.println(playerNumber);
				int modelNumber = (int) request.get("modelNumber");

			}
			
			if (command.contains("vatican")) {
				System.out.println("CSV ---> Ricevuta richiesta scomunica");
				view.askForExcommunication();
			}
		}

	}

