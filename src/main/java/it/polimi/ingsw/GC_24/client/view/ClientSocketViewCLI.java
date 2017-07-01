package it.polimi.ingsw.GC_24.client.view;

import java.io.*;
import java.util.*;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientSocketViewCLI extends MyObservable implements ClientSocketViewInterface {

	private ObjectInputStream objFromServer;
	private ObjectOutputStream objToServer;
	private ViewCLI view;

	public ClientSocketViewCLI(ObjectInputStream objFromServer, ObjectOutputStream objToServer, ViewCLI view) {
		this.objToServer = objToServer;
		this.objFromServer = objFromServer;
		this.view = view;
		this.registerMyObserver(view);
		view.registerMyObserver(this);
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

				handleRequestFromServer(requestFromServer);

			}
		} catch (EOFException e) {
			System.out.println("End Of File reached");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public synchronized <C> void update(MyObservable o, C change) {
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
	@Override
	public synchronized void handleRequestFromServer(Map<String, Object> request) {
		System.out.println("CSV ---> Gestendo una richiesta ");
		Set<String> command = request.keySet();

		/* IN THIS CASE the request is handled by the viewCLI */
		if (command.contains("Cost1")) {
			System.out.println("CSV --> Ricevuto un costo alternativo");
			SetOfValues cost1 = (SetOfValues) request.get("Cost1");
			SetOfValues cost2 = (SetOfValues) request.get("Cost2");
			MilitaryPoint militaryPoints = (MilitaryPoint) request.get("Requirements");

			view.chooseAlternativeCost(cost1, cost2, militaryPoints);

		}

		if (command.contains("problems")) {
			System.out.println("CSV ---> Ricevuti problemi");
			String problems = (String) request.get("problems");
			notifyMyObservers(problems);
		}

		if (command.contains("model")) {
			System.out.println("CSV ---> Ricevuto Model");
			synchronized (view.getWaitingForAnswer()) {

				Model receivedModel = (Model) request.get("model");

				view.setMiniModel(receivedModel);
				view.setMyself(view.getMiniModel().getPlayers().get(view.getPlayerNumber() - 1));
				view.setPlayerTurn(view.getMiniModel().getPlayers());
				view.getWaitingForAnswer().notify();
			}
		}

		if (command.contains("askForParameters")) {
			System.out.println("CSV ---> Ricevuta richiesta di parametri ");

			handleEffectParametersRequest((ImmediateEffect) request.get("askForParameters"));
		}

		if (command.contains("vatican")) {
			System.out.println("CSV ---> Ricevuto Vaticano");
			view.askForExcommunication();
		}

		if (command.contains("actionDone")) {
			System.out.println("CSV ---> Ricevuta segnalazione di azione completata ");
			synchronized (view.getWaitingForActionCompleted()) {
				view.setActionDone(true);
				view.getWaitingForActionCompleted().notify();
			}

		}

		if (command.contains("info")) {
			System.out.println("CSV ---> Ricevute informazioni da mostrare a video");
			notifyMyObservers(request.get("info"));

		}

		if (command.contains("startPlaying")) {

			Thread t1 = new Thread(new Runnable() {
				public void run() {
					view.play();
				}
			});
			t1.start();

		}
		if (command.contains("Turns")) {
			System.out.println("CSV ---> Ricevuti turni ");
			List<Player> playerTurn = (List<Player>) request.get("Turns");
			view.setPlayerTurn(playerTurn);

		}
		if (command.contains("currentPlayer")) {
			System.out.println("CSV ---> Ricevuto giocatore corrente ");
			Player currentPlayer = (Player) request.get("currentPlayer");

			if (currentPlayer.getPlayerNumber() == view.getMyself().getPlayerNumber()) {
				System.out.println("Player #" + view.getMyself().getPlayerNumber() + " turn is TRUE  ");
				view.setMyTurn(true);
			} else {
				System.out.println("Player #" + view.getMyself().getPlayerNumber() + " turn is FALSE  ");
				view.setMyTurn(false);
			}
		}

		if (command.contains("clientNumber")) {
			System.out.println("CSV ---> Ricevuto numero client");
			int playerNumber = (int) request.get("clientNumber");
			int modelNumber = (int) request.get("modelNumber");
			if (view.getPlayerNumber() == 0) {
				view.setPlayerNumber(playerNumber);
			}
			notifyMyObservers("You are the player #" + playerNumber + ", connected to game #" + modelNumber);

		}
		if (command.contains("sale")) {
			System.out.println("CSV ---> Ricevuta richiesta sconto multiplo");

			view.chooseSale((IncreaseDieValueCard) request.get(command));

		}
	}

	private void handleEffectParametersRequest(ImmediateEffect immediateEffect) {
		if (immediateEffect instanceof ChooseNewCard) {
			System.out.println("CSV ---> E' un ChooseNewCard ");
			view.askForChooseNewCard((ChooseNewCard) immediateEffect);
		}

		if (immediateEffect instanceof CouncilPrivilege) {
			System.out.println("CSV ---> E' un CouncilPrivilege ");
			view.askForCouncilPrivilege((CouncilPrivilege) immediateEffect);
		}

		if (immediateEffect instanceof Exchange) {
			System.out.println("CSV ---> E' un Exchange ");
			view.askForExchange((Exchange) immediateEffect);

		}
		// i parametri sono stati scelti e passati all'effetto
		if (immediateEffect instanceof PerformActivity) {
			System.out.println("CSV ---> E' un PerformActivity ");
			view.askForServantsForHarvestAndProduction((PerformActivity) immediateEffect);
		}

	}
}
