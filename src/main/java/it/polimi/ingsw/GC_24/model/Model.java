package it.polimi.ingsw.GC_24.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.board.Board;
import it.polimi.ingsw.GC_24.cards.Deck;
import it.polimi.ingsw.GC_24.cards.Excommunication;
import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.client.view.ServerSocketView;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.board.Board;
import it.polimi.ingsw.GC_24.cards.Deck;
import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.dice.SetOfDice;
import it.polimi.ingsw.GC_24.network.multi.Server;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Model extends MyObservable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6361938651563234916L;
	private List<Player> players;
	private Board board;
	private Player currentPlayer;
	private State gameState;

	private SetOfDice dice;
	private List<Ranking> rankings;
	private HashMap<String, Object> hm;
	private Deck cards;
	private List<Excommunication> excommunicationDeck = new ArrayList<>();;

	private int modelNumber;

	private boolean isAcceptingPlayers = true;

	private int counter;

	private static Timer timer;
	private List<SetOfValues> correspondingValue = new ArrayList<>();
	private int countingModelSent = 0;

	public Model(int modelNumber) {

		this.players = new ArrayList<>();
		this.board = null;
		this.currentPlayer = null;
		this.gameState = State.WAITINGFORPLAYERONE;
		this.dice = null;
		this.rankings = new ArrayList<Ranking>();
		this.counter = 0;
		this.modelNumber = modelNumber;
		this.cards = new Deck();
	//	getCorrespondingValueFromFile();
	//	createExcommunicationDeck();
  }

	private void createExcommunicationDeck() {
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		try {
			br = new BufferedReader(
					new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/excommunicationCards.json"));

			while ((line = GsonBuilders.getLine(br)) != null) {
				this.excommunicationDeck.add(gson.fromJson(line, Excommunication.class));
			}
		} catch (IOException e) {
			System.out.println("There is a problem with the configuration file");
		}
	}

	public synchronized void addPlayer() {

		counter++;
		System.out.println("Model --> Contatore incrementato");
		sendNumberToClient();
		System.out.println("Model --> Numero inviato al client");
		Player player = new Player(counter);
		this.getPlayers().add(player);
		System.out.println("Model: PLAYER " + player);
		System.out.println("Model: Player #" + counter + "added to Game #" + getModelNumber());

		incrementState();
		System.out.println("Model --> stato incrementato");
		sendModel();
		System.out.println("Model --> model inviato");

		if (getGameState().equals(State.WAITINGFORPLAYERTHREE)) {
			System.out.println("Model: Timer Starting");
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println("Model: *TIME UP*");

					Server.launchAndCreateNewGame();
				}

			}, 15000);
		}

		if (getGameState().equals(State.RUNNING)) {

			timer.cancel();
			Server.launchAndCreateNewGame();

		}

	}

	/**
	 * After a Model is created and the players are get, this method sets the
	 * model so the game could start
	 */
	public void setModel(List<Player> players) {

		this.players = players;
		this.board = new Board(players.size());
		this.currentPlayer = players.get(0);
		this.setGameState(State.RUNNING);
		this.cards = new Deck();
		this.dice = new SetOfDice();
		this.dice.reset();
		getCorrespondingValueFromFile();

		// Setting the players
		for (Player p : players) {
			p.getMyValues().setInitialValues(players.indexOf(p)+1);
			p.setMyColour(PlayerColour.valueOf(PlayerColour.getValues().get(players.indexOf(p))));
			p.setMyFamily(new Family(p.getMyColour()));
			p.getMyFamily().setFamily(this.dice);

			rankings.add(new Ranking(p));
		}

	}

	public void incrementState() {
		this.setGameState(this.getGameState().nextState());
	}

	public void sendModel() {
		countingModelSent ++;
		System.out.println("Model --> Invio del model #"+ countingModelSent);
		hm = new HashMap<>();
		hm.put("model", this);
		// System.out.println("FROM MODEL SENDING THIS
		// "+this.getPlayers().get(0).getMyValues());
		notifyMyObservers(hm);
	}

	private void sendNumberToClient() {
		System.out.println("Richiesta di invio numero");
		hm = new HashMap<>();
		hm.put("clientNumber", counter);
		hm.put("modelNumber", modelNumber);
		notifyMyObservers(hm);
System.out.println("Numero inviato");
	}

	public Player getPlayerfromColour(PlayerColour colour) {
		for (Player player : players) {
			if (player.getMyColour().equals(colour))
				return player;
		}
		return null;
	}

	// getters and setters
	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Board getBoard() {
		return board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public State getGameState() {
		return gameState;
	}

	public SetOfDice getDice() {
		return dice;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public void setDice(SetOfDice dice) {
		this.dice = dice;
	}

	public boolean isAcceptingPlayers() {
		return isAcceptingPlayers;
	}

	public void setAcceptingPlayers(boolean isAcceptingPlayers) {
		this.isAcceptingPlayers = isAcceptingPlayers;
	}

	public Deck getCards() {
		return cards;
	}

	public void setCards(Deck cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Model [players=" + players + ", gameState=" + gameState + ", modelNumber=" + modelNumber + "]";
	}

	/**
	 * This method converts Faith Points in Values specified in a configuration
	 * file named "convertFaithPoints.json". Every line of file corresponds to a
	 * score. All Values are entered in a List of SetOfValues
	 */
	public void getCorrespondingValueFromFile() {
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line = "ready";
		try (BufferedReader br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/convertFaithPoints.json"))){
			while (line != null) {
				line = GsonBuilders.getLine(br);
				correspondingValue.add(gson.fromJson(line, SetOfValues.class));
			}
		} catch (IOException e) {
			System.out.println("There is a problem with the configuration file");
		}
	}

	public void setCorrespondingValue(List<SetOfValues> correspondingValue) {
		this.correspondingValue = correspondingValue;
	}

	public List<SetOfValues> getCorrespondingValue() {
		return correspondingValue;
	}

}
