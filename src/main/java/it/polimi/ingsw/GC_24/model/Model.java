package it.polimi.ingsw.GC_24.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.board.Board;
import it.polimi.ingsw.GC_24.dice.SetOfDice;

public class Model extends MyObservable implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4715762523324083940L;

	private List<Player> players;
	private Board board;
	private Player currentPlayer;
	private State gameState;
	private SetOfDice dice;
	private Round currentRound;
	private Period currentPeriod;
	private List<Ranking> rankings;
	private HashMap<String, Object> hm;
	
	/*Constructor --> ONLY PLAYERS NEEDS TO BE PASSED
	 * other fields are created or set */
	public Model() {
		
		this.players = new ArrayList<>();
		this.board = null;
		this.currentPlayer = null;
		this.gameState = State.WAITINGFORPLAYERONE;
		this.dice = null;
		this.currentRound = null;
		this.currentPeriod = null;
		this.rankings = new ArrayList<Ranking>();
	}

	/*
	 * After a Model is created and the players are get, this method sets the
	 * model so the game could start
	 */
	public void setModel(List<Player> players) throws IOException {

		this.players = players;
		this.board = new Board(players.size());
		this.currentPlayer = players.get(0);
		this.setGameState(State.RUNNING);
		this.dice = new SetOfDice();
		this.currentRound = Round.ONE;
		this.currentPeriod = Period.ONE;
		this.dice.reset();

		for (Player p : players) {
			p.getMyValues().setInitialValues(players.indexOf(p));
			p.getMyFamily().setFamily(this.dice);
			rankings.add(new Ranking(p));
		}		
		hm = new HashMap<>();
		hm.put("Model", this);
		notifyMyObservers(hm);
	}
	
	public Player getPlayerfromColour(PlayerColour colour){
		for (Player player:players){
			if(player.getMyColour().equals(colour))
				return player;
		}
		return null;
	}

	// getters and setters
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

	public Round getCurrentRound() {
		return currentRound;
	}

	public Period getCurrentPeriod() {
		return currentPeriod;
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

	public void setCurrentRound(Round currentRound) {
		this.currentRound = currentRound;
	}

	public void setCurrentPeriod(Period currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	@Override
	public String toString() {
		return "model ok";
	}
	
}	
