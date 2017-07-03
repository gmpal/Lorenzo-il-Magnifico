package it.polimi.ingsw.GC_24.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import com.google.gson.Gson;

import java.util.*;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.model.board.Board;
import it.polimi.ingsw.GC_24.model.cards.Deck;
import it.polimi.ingsw.GC_24.model.cards.Excommunication;
import it.polimi.ingsw.GC_24.model.cards.Leader;
import it.polimi.ingsw.GC_24.model.dice.SetOfDice;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.network.Server;
import it.polimi.ingsw.GC_24.observers.MyObservable;

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
	private List<Excommunication> excommunicationDeck = new ArrayList<>();
	private List<SetOfValues> correspondingValue = new ArrayList<>();

	private int modelNumber;

	private boolean isAcceptingPlayers = true;

	private int counter;

	private static Timer timer;
	private int countingModelSent = 0;

	public Model(int modelNumber) {

		this.players = new ArrayList<>();
		this.board = null;
		this.currentPlayer = null;
		this.gameState = State.WAITINGFORPLAYERONE;
		this.dice = null;
		this.rankings = new ArrayList<>();
		this.counter = 0;
		this.modelNumber = modelNumber;
		this.cards = null;
	}

	/**
	 * This method create the Excommunication Cards' deck from a configuration file
	 * named "excommunicationCards.json". All cards are put in an ArrayList, then it
	 * will take three random cards and it put them in another ArrayList, one per
	 * round. This final ArrayList contains the possible excommunication card the
	 * player can take when it will choose to not support the Vatican.
	 */
	public void createExcommunicationDeck() {
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		List<Excommunication> completeDeck = new ArrayList<>();
		try {
			br = new BufferedReader(
					new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/excommunicationCards.json"));

			while ((line = GsonBuilders.getLine(br)) != null) {
				completeDeck.add(gson.fromJson(line, Excommunication.class));
			}
		} catch (IOException e) {
			System.out.println("There is a problem with the configuration file");
		}
		Random randomExcommunication = new Random();
		int randomInt;
		for (int i = 0; i < 3; i++) {
			randomInt = randomExcommunication.nextInt(7) + (i * 7);
			this.excommunicationDeck.add(completeDeck.get(randomInt));
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
	 * After a Model is created and the players are get, this method sets the model
	 * so the game could start
	 */
	public void setModel(List<Player> players) {

		this.players = players;
		this.board = new Board(players.size());
		this.currentPlayer = players.get(0);
		this.setGameState(State.RUNNING);
		this.cards = new Deck();
		this.dice = new SetOfDice();

		this.dice.reset();
		dealLeaders(cards.getDeckLeaders(), players);
		getCorrespondingValueFromFile();
		createExcommunicationDeck();
		System.out.println(this.excommunicationDeck);

		// Setting the players
		for (Player p : players) {
			p.getMyValues().setInitialValues(players.indexOf(p) + 1);
			p.setMyColour(PlayerColour.valueOf(PlayerColour.getValues().get(players.indexOf(p))));
			p.setMyFamily(new Family(p.getMyColour()));
			p.getMyFamily().setFamily(this.dice);
			rankings.add(new Ranking(p));
		}

	}

	public void updateModel() {
		this.dice.reset();
		for (Player p : players) {
			p.getMyFamily().setFamily(this.dice);
		//	changeInDieValue(p);
		}
	}

	/**
	 * This method checks if some leader cards that change the family members' die
	 * value are active in the player, and in that case changes them
	 */
	public void changeInDieValue(Player player) {
		
		for (PermanentEffect p:player.getPermanentEffectList("increaseDieValueFamiliar")) {
			IncreaseDieValueActivity pe1 = (IncreaseDieValueActivity) p;
			int value = pe1.getIncreaseDieValue();
			player.getMyFamily().getMember1().setMemberValue(player.getMyFamily().getMember1().getMemberValue() + value);
			player.getMyFamily().getMember2().setMemberValue(player.getMyFamily().getMember2().getMemberValue() + value);
			player.getMyFamily().getMember3().setMemberValue(player.getMyFamily().getMember3().getMemberValue() + value);
		}
		if (player.getPermanentEffect("setDiceValue") != null) {
			IncreaseDieValueActivity pe = (IncreaseDieValueActivity) player.getPermanentEffect("setDieValue");
			int value = pe.getIncreaseDieValue();
			player.getMyFamily().getMember1().setMemberValue(value);
			player.getMyFamily().getMember2().setMemberValue(value);
			player.getMyFamily().getMember3().setMemberValue(value);
		}
		if (player.getPermanentEffect("setValueFamilyMember") != null) {
			List<Integer> dieValues = new ArrayList<>();
			dieValues.add(player.getMyFamily().getMember1().getMemberValue());
			dieValues.add(player.getMyFamily().getMember2().getMemberValue());
			dieValues.add(player.getMyFamily().getMember3().getMemberValue());
			Collections.sort(dieValues);
			Collections.reverse(dieValues);
			if (player.getMyFamily().getMember1().getMemberValue() == dieValues.get(0)) {
				player.getMyFamily().getMember1().setMemberValue(6);
			} else if (player.getMyFamily().getMember2().getMemberValue() == dieValues.get(0)) {
				player.getMyFamily().getMember1().setMemberValue(6);
			} else if (player.getMyFamily().getMember3().getMemberValue() == dieValues.get(0)) {
				player.getMyFamily().getMember1().setMemberValue(6);
			}
		}
		if (player.getPermanentEffect("increaseValueNeutralFamilyMember") != null) {
			player.getMyFamily().getMember4().setMemberValue(player.getMyFamily().getMember4().getMemberValue() + 3);
		}
		
	}

	public void incrementState() {
		this.setGameState(this.getGameState().nextState());
	}

	public void sendModel() {
		countingModelSent++;
		System.out.println("Model --> Invio del model #" + countingModelSent);

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

	private void dealLeaders(List<Leader> leaderDeck, List<Player> players) {
		Random random = new Random();
	//	int num = (leaderDeck.size() / players.size());
		for (Player p : players) {
			for (int i = 0; i < 4; i++) {
				int position = random.nextInt(leaderDeck.size());
				p.getMyBoard().getPersonalLeader().add(leaderDeck.get(position));
				leaderDeck.remove(position);
			}
		}
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
	 * This method converts Faith Points in Values specified in a configuration file
	 * named "convertFaithPoints.json". Every line of file corresponds to a score.
	 * All Values are entered in a List of SetOfValues
	 */
	public void getCorrespondingValueFromFile() {
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line = "ready";
		try (BufferedReader br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/convertFaithPoints.json"))) {
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

	public List<Excommunication> getExcommunicationDeck() {
		return excommunicationDeck;
	}

	public void setExcommunicationDeck(List<Excommunication> excommunicationDeck) {
		this.excommunicationDeck = excommunicationDeck;
	}

}
