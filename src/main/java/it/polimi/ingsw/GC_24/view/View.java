package it.polimi.ingsw.GC_24.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;

public abstract class View extends MyObservable implements Runnable, MyObserver {
	
	protected volatile Model miniModel;
	protected String name = null;

	protected HashMap<String, Object> hm;
	protected Timer timer;

	protected Object waitingForNameUpdate = new Object();
	protected Object waitingForActionCompleted = new Object();

	protected boolean myTurn = false;
	protected List<Player> playersTurn;
	protected int playerNumber = 0;
	protected boolean actionDone = false;
	protected volatile Player myself = null;

	//FROM SERVER TO CLIENT
	
	public abstract void show(String message);
	
	public abstract void showToSinglePlayer(Player currentPlayer, String message);
		
	public abstract String chooseAlternativeCost(String request);

	public abstract SetOfValues chooseSale(IncreaseDieValueCard increase); 
	
	public abstract void askForExcommunication();
	
	public abstract String askForCouncilPrivilege(String request);
	
	public abstract String askForExchange(String request);
	
	public abstract String askForServantsForHarvestOrProduction(String request);
	
	public abstract String askForChooseNewCard(String request);
	
	
	
	public abstract void play();

	public abstract void updatePlayerNumber(int playerNumber2, int modelNumber);

	public abstract void communicateActionDone();

	public void updateTurn(List<Player> playerTurn) {
		setPlayersTurn(playerTurn);

	}

	public void getInformationForReceivedModel(Model model) {
		synchronized (waitingForNameUpdate) {
			setMiniModel(model);

			setMyself(getMiniModel().getPlayers().get(playerNumber - 1));

			setName(myself.getMyName());

			setPlayersTurn(getMiniModel().getPlayers());

			waitingForNameUpdate.notify();
		}

	}

	public abstract void setMyTurn(Player currentPlayer);


	
	
	//FROM CLIENT TO SERVER
	
	public void sendPlayerString(String name){

		String player = (playerNumber + " " + name);
		hm = new HashMap<>();
		hm.put("player", player);
		this.notifyMyObservers(hm);
		System.out.println("Your name has been sent");

		synchronized (waitingForNameUpdate) {
			while (!miniModel.getPlayers().get((playerNumber) - 1).getMyName().equalsIgnoreCase(name)) {
				try {
					waitingForNameUpdate.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Server received your name and updated it");
	}

	public void sendAction(String command) {
		actionDone = false;

		hm = new HashMap<>();
		hm.put("action", command);
		notifyMyObservers(hm);

		waitForActionDone();

	}
	
	public void sendLeader(String command) {
		actionDone = false;
		hm = new HashMap<>();
		hm.put("leader", command);
		this.notifyMyObservers(hm);
		waitForActionDone();
	}
	
	public void waitForActionDone() {
		/* This block of code notifies the Server of the action */
		actionDone = false;
		synchronized (waitingForActionCompleted) {
			System.out.println("----Waiting for your action to be completed----");
			while (!actionDone) {
				try {
					System.out.println("----Waitin'----");
					waitingForActionCompleted.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
			System.out.println("--------Action Completed");
		}
	}
	
	public void sendAnswerForParameters(String answer) {
		hm = new HashMap<>();
		hm.put("answerForParameters", answer);
		notifyMyObservers(hm);
	}
	
	public void sendAlternativeCost(String response) {
		hm = new HashMap<>();
		hm.put("chosenCost", response);
		notifyMyObservers(hm);

	}
	
	public void sendAnswerToVatican(String answer) {
		hm = new HashMap<>();
		hm.put("answerForVatican", answer);
		this.notifyMyObservers(hm);
	}

	
	//getters and setters
	public Model getMiniModel() {
		return miniModel;
	}

	public void setMiniModel(Model miniModel) {
		this.miniModel = miniModel;
	}

	public HashMap<String, Object> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, Object> hm) {
		this.hm = hm;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Object getWaitingForNameUpdate() {
		return waitingForNameUpdate;
	}

	public void setWaitingForNameUpdate(Object waitingForNameUpdate) {
		this.waitingForNameUpdate = waitingForNameUpdate;
	}

	public Object getWaitingForActionCompleted() {
		return waitingForActionCompleted;
	}

	public void setWaitingForActionCompleted(Object waitingForActionCompleted) {
		this.waitingForActionCompleted = waitingForActionCompleted;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public List<Player> getPlayersTurn() {
		return playersTurn;
	}

	public void setPlayersTurn(List<Player> playersTurn) {
		this.playersTurn = playersTurn;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public boolean isActionDone() {
		return actionDone;
	}

	public void setActionDone(boolean actionDone) {
		this.actionDone = actionDone;
	}

	public Player getMyself() {
		return myself;
	}

	public void setMyself(Player myself) {
		this.myself = myself;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
