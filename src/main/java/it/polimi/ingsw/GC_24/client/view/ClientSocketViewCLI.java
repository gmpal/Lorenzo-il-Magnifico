package it.polimi.ingsw.GC_24.client.view;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_24.MyObservable;

import it.polimi.ingsw.GC_24.effects.ChooseNewCard;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.Exchange;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.PerformActivity;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;

import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.*;

import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;

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
				System.out.println("Ricezione numero "+i);

				HashMap<String, Object> requestFromServer;
			
				
				requestFromServer = (HashMap<String, Object>) objFromServer.readObject();
			
				Thread t1 = new Thread(new Runnable(){
					public void run(){
						handleRequestFromServer(requestFromServer);
					}
				});
					t1.start();
				

			}
		} catch (EOFException e) {
			System.out.println("End Of File reached");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public <C> void update(MyObservable o, C change) {
		try {
			objToServer.reset();
			objToServer.writeObject(change);
			objToServer.flush();
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Based on the key of the object received, this method handles the request
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleRequestFromServer(Map<String, Object> request) {
		Set<String> command = request.keySet();

		/* IN THIS CASE the request is handled by the viewCLI */
		if (command.contains("cost1")) {
			SetOfValues cost1 = (SetOfValues) request.get("Cost1");
			SetOfValues cost2 = (SetOfValues) request.get("Cost2");
			MilitaryPoint militaryPoints = (MilitaryPoint) request.get("Requirements");
			view.chooseAlternativeCost(cost1, cost2, militaryPoints);
		}

		if (command.contains("model")) {
			synchronized (view.getWaitingForAnswer()) {

				Model receivedModel = (Model) request.get("model");
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + receivedModel + "\n@@@@@@@@@@@@@@@@@@@@@@@@@");
				view.setMiniModel(receivedModel);

				System.out.println("GIOCATORI FINO ADESSO" + view.getMiniModel().getPlayers());

				System.out.println("NUMERO GIOCATORE" + view.getMiniModel().getPlayers().get(0).getPlayerNumber());
				System.out.println("....Player Number ...." + view.getPlayerNumber());

				view.setMyself(view.getMiniModel().getPlayers().get(view.getPlayerNumber() - 1));
				System.out.println("Myself ----> " + view.getMyself());
				view.setPlayerTurn(view.getMiniModel().getPlayers());

				view.getWaitingForAnswer().notify();
			}
		}
		if (command.contains("askForParameters")) {
			handleEffectParametersRequest((ImmediateEffect) request.get("askForParameters"));

		}

		if (command.contains("actionDone")) {
			System.out.println("RICEVUTO RISVEGLIO DAL CLIENT");
			synchronized (view.getWaitingForActionCompleted()) {
				view.setActionDone(true);
				view.getWaitingForActionCompleted().notify();
			}

		}

		if (command.contains("info")) {
			notifyMyObservers(request.get("info"));

		}

		if (command.contains("startPlaying")) {
			System.out.println("GET READY --> GAME IS STARTING");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.play();
		}
		if (command.contains("Turns")) {
			List<Player> playerTurn = (List<Player>) request.get("Turns");
			view.setPlayerTurn(playerTurn);

		}
		if (command.contains("currentPlayer")) {
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
			int playerNumber = (int) request.get("clientNumber");
			int modelNumber = (int) request.get("modelNumber");
			if (view.getPlayerNumber() == 0) {
				view.setPlayerNumber(playerNumber);

			}
			notifyMyObservers("You are the player #" + playerNumber + ", connected to game #" + modelNumber);

		}
		if (command.contains("sale")) {
			view.chooseSale((IncreaseDieValueCard) request.get(command));
		}
	}

	private void handleEffectParametersRequest(ImmediateEffect immediateEffect) {
		if (immediateEffect instanceof ChooseNewCard) {
			view.askForChooseNewCard((ChooseNewCard) immediateEffect);
		}

		if (immediateEffect instanceof CouncilPrivilege) {
			view.askForCouncilePrivilege((CouncilPrivilege) immediateEffect);
		}

		if (immediateEffect instanceof Exchange) {

			view.askForExchange((Exchange) immediateEffect);

		}
		// i parametri sono stati scelti e passati all'effetto
		if (immediateEffect instanceof PerformActivity) {
			view.askForServantsForHarvestAndProduction((PerformActivity) immediateEffect);
		}

	}
}
