package it.polimi.ingsw.GC_24.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;

public abstract class View extends MyObservable implements MyObserver {
	
	public View (String name ) {
		this.name = name;
	}
	
	protected String name = null;

	
	protected String towerTerritories;
	protected String towerCharacters;
	protected String towerBuildings;
	protected String towerVentures;
	protected String harvest;
	protected String market;
	protected String production;
	protected String council;

	protected String colour;
	protected String personalTerritories;
	protected String personalCharacters;
	protected String personalBuildings;
	protected String personalVentures;
	protected String personalLeaders;
	protected String family;
	protected String values;
	protected String permanentEffects;
	protected String oneTimePerTurnEffects;
	
	protected String rankings;
	
	
	
	
	protected HashMap<String, Object> hm;
	protected Timer timer;

	protected Object waitingForActionCompleted = new Object();

	protected boolean myTurn = false;
	protected List<String> playersTurn;
	protected int playerNumber = 0;
	protected boolean actionDone = false;
	

	//FROM SERVER TO CLIENT
	
	public abstract void show(String message);

		
	public abstract String chooseAlternativeCost(String request);

	public abstract String chooseSale(String increase); 
	
	public abstract String askForExcommunication();
	
	public abstract String askForCouncilPrivilege(String request);
	
	public abstract String askForExchange(String request);
	
	public abstract String askForServantsForHarvestOrProduction(String request);
	
	public abstract String askForChooseNewCard(String request);
	
	public abstract void updateTurn(List<String> playerTurn);
	
	public abstract void play();

	

	public abstract void setRankings(String rankings);

	public abstract void setMyTurn(String currentPlayer);
	
	public abstract void disconnectClient();

	
	
	
	//FROM CLIENT TO SERVER
	
	public void sendPlayerString(String name){

		hm = new HashMap<>();
		hm.put("player", name);
		this.notifyMyObservers(hm);
		System.out.println("Your name has been sent");
	}

	public void sendAction(String command) {
		actionDone = false;

		hm = new HashMap<>();
		hm.put("action", command);
		notifyMyObservers(hm);
		waitForActionDone();

	}
	
	public void parseBoardInformation(String[] boardInformation) {
		this.towerTerritories = boardInformation[0];
		this.towerBuildings = boardInformation[1];
		this.towerCharacters = boardInformation[2];
		this.towerVentures = boardInformation[3];
		this.market = boardInformation[4];
		this.harvest = 	boardInformation[5];
		this.production = boardInformation[6];
		this.council = boardInformation[7];
		
	}
	
	public void parsePersonalInformations(String[] personalInformation) {
		this.personalTerritories = personalInformation[0];
		this.personalBuildings = personalInformation[1];
		this.personalCharacters = personalInformation[2];
		this.personalVentures = personalInformation[3];
		this.personalLeaders = personalInformation[4];
		this.family = personalInformation[5];
		this.values =	personalInformation[6];	
		this.colour = personalInformation[7];
		this.permanentEffects =  personalInformation[8];
		this.oneTimePerTurnEffects =  personalInformation[9];
		
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
	
	public void communicateActionDone() {
		synchronized (getWaitingForActionCompleted()) {
			setActionDone(true);
			getWaitingForActionCompleted().notify();
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

	public void sendAlternativeSale(String response) {
		hm = new HashMap<>();
		hm.put("answerForsale", response);
		this.notifyMyObservers(hm);
		
	}
	
	//getters and setters


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

	public List<String> getPlayersTurn() {
		return playersTurn;
	}

	public void setPlayersTurn(List<String> playersTurn) {
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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getRankings() {
		return rankings;
	}


	public void setUrlExcommunication(ArrayList<String> arrayList) {
		//Overridden in viewGUI
	}
	
	public void setUrlPersonalBoard(ArrayList<String> urlPersonalBoard) {
		//Overridden in viewGUI
		
	}
	public void setUrlBoard(ArrayList<String> urlBoard) {
		//Overridden in viewGUI
		
	}


	public void setUrlColour(ArrayList<String> urlColour) {
		//Overridden in viewGUI
		
	}




	
}
