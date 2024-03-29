package it.polimi.ingsw.GC_24.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import com.google.gson.Gson;
import java.util.*;

import it.polimi.ingsw.GC_24.controller.Timers;
import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.model.board.Board;
import it.polimi.ingsw.GC_24.model.cards.Deck;
import it.polimi.ingsw.GC_24.model.cards.Leader;
import it.polimi.ingsw.GC_24.model.dice.SetOfDice;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;
import it.polimi.ingsw.GC_24.network.Server;
import it.polimi.ingsw.GC_24.observers.MyObservable;

/**
 * This class represent a game. Every model has a list of players, the board,
 * the dice, the deck of cards and all the elements that are useful to play.
 */
public class Model extends MyObservable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6361938651563234916L;

	private List<Player> players;
	private Board board;
	private Player currentPlayer;
	private State gameState;
	private Ranking ranking;

	private SetOfDice dice;

	private HashMap<String, Object> hm;
	private Deck cards;
	private List<Excommunication> excommunicationDeck = new ArrayList<>();
	private List<SetOfValues> correspondingValue = new ArrayList<>();

	private int modelNumber;

	private String tempName;

	private boolean isAcceptingPlayers = true;

	private int counter;

	private static Timer timer;
	private Timers timers = new Timers();
	private int countingModelSent = 0;
	private List<String> urlExcommunication = new ArrayList<>();

	public Model(int modelNumber) {

		this.players = new ArrayList<>();
		this.board = null;
		this.currentPlayer = null;
		this.gameState = State.WAITINGFORPLAYERONE;
		this.dice = null;
		this.counter = 0;
		this.modelNumber = modelNumber;
		this.cards = null;
		this.ranking = null;
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
			;
		}
		Random randomExcommunication = new Random();
		int randomInt;
		for (int i = 0; i < 3; i++) {
			randomInt = randomExcommunication.nextInt(7) + (i * 7);
			this.excommunicationDeck.add(completeDeck.get(randomInt));
			urlExcommunication.add(this.excommunicationDeck.get(i).getUrl());
		}
	}

	public void addPlayer() {

		counter++;
		;
		Player player = new Player(tempName, counter);
		this.getPlayers().add(player);
		;
		;

		incrementState();
		;
		;
		;

		if (getGameState().equals(State.WAITINGFORPLAYERTHREE)) {
			;
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					;

					Server.launchAndCreateNewGame();
				}

			}, timers.getTimeToStartGame());
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

		;

		// Setting the players
		for (Player p : players) {
			p.getMyValues().setInitialValues(players.indexOf(p) + 1);
			p.setMyColour(PlayerColour.valueOf(PlayerColour.getValues().get(players.indexOf(p))));
			p.setMyFamily(new Family(p.getMyColour()));
			p.getMyFamily().setFamily(this.dice);
		}
		ranking = new Ranking(players);
	}

	public void updateModel() {
		this.dice.reset();
		for (Player p : players) {
			p.getMyFamily().setFamily(this.dice);
			// changeInDieValue(p);
		}
	}

	/**
	 * This method checks if some leader cards that change the family members' die
	 * value are active in the player, and in that case changes them
	 */
	public void changeInDieValue(Player player) {

		if (player.getPermanentEffect("setDiceValue") != null) {
			IncreaseDieValueActivity pe = (IncreaseDieValueActivity) player.getPermanentEffect("setDiceValue");
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
				player.getMyFamily().getMember2().setMemberValue(6);
			} else if (player.getMyFamily().getMember3().getMemberValue() == dieValues.get(0)) {
				player.getMyFamily().getMember3().setMemberValue(6);
			}
		}
		if (player.getPermanentEffect("increaseValueNeutralFamilyMember") != null) {
			player.getMyFamily().getMember4().setMemberValue(player.getMyFamily().getMember4().getMemberValue() + 3);
		}

		for (PermanentEffect p : player.getPermanentEffectList("increaseDieValueFamiliar")) {
			IncreaseDieValueActivity pe1 = (IncreaseDieValueActivity) p;
			int value = pe1.getIncreaseDieValue();
			player.getMyFamily().getMember1()
					.setMemberValue(player.getMyFamily().getMember1().getMemberValue() + value);
			player.getMyFamily().getMember2()
					.setMemberValue(player.getMyFamily().getMember2().getMemberValue() + value);
			player.getMyFamily().getMember3()
					.setMemberValue(player.getMyFamily().getMember3().getMemberValue() + value);
		}

	}

	public void incrementState() {
		this.setGameState(this.getGameState().nextState());
	}

	/**
	 * This methods prepare the board situation before sending it to the client -->
	 * it only uses strings to avoid casting from client issues
	 */
	public String[] prepareBoardInformation() {
		String[] boardInformation = new String[8];
		boardInformation[0] = board.getTowerTerritories().toString();
		boardInformation[1] = board.getTowerBuildings().toString();
		boardInformation[2] = board.getTowerCharacters().toString();
		boardInformation[3] = board.getTowerVentures().toString();
		boardInformation[4] = board.getMarket().toString();
		boardInformation[5] = board.getHarvest().toString();
		boardInformation[6] = board.getProduction().toString();
		boardInformation[7] = board.getCouncilPalace().toString();
		return boardInformation;
	}

	/**
	 * This methods prepare the personal information situation before sending it to
	 * the client --> it only uses strings to avoid casting from client issues
	 */
	public String[] preparePersonalInformation(Player p) {
		String[] personalInformation = new String[11];
		personalInformation[0] = p.getMyBoard().getPersonalTerritories().toString();
		personalInformation[1] = p.getMyBoard().getPersonalBuildings().toString();
		personalInformation[2] = p.getMyBoard().getPersonalCharacters().toString();
		personalInformation[3] = p.getMyBoard().getPersonalVentures().toString();
		personalInformation[4] = p.getMyBoard().getPersonalLeader().toString();
		personalInformation[5] = p.getMyFamily().toString();
		personalInformation[6] = p.getMyValues().toString();
		personalInformation[7] = p.getMyColour().toString();
		personalInformation[8] = p.getActivePermanentEffects().toString();
		personalInformation[9] = p.getLeaderOneTimePerTurn().toString();
		personalInformation[10] = Integer.toString(p.getPlayerNumber());
		return personalInformation;

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

		// int num = (leaderDeck.size() / players.size());
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
			;
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

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public List<String> getUrlExcommunication() {
		return urlExcommunication;
	}

	public void setUrlExcommunication(List<String> urlExcommunication) {
		this.urlExcommunication = urlExcommunication;
	}
}
