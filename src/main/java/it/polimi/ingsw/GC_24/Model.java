package it.polimi.ingsw.GC_24;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Set;

import it.polimi.ingsw.GC_24.places.Board;

public class Model extends Observable {
	
	private ArrayList<Player> players;
	private Board board;
	private Player currentPlayer;
	private State gameState;
	private SetOfDice dice;
	private Round currentRound;
	private Period currentPeriod;
	
	
	
	/*Constructor --> ONLY PLAYERS NEEDS TO BE PASSED
	 * other fields are created or set */
	public Model(ArrayList<Player> players ) {
		
		this.players = players;
		this.board = new Board(players.size());
		this.currentPlayer = players.get(0);
		this.gameState = State.SETTING;
		this.dice = new SetOfDice();
		this.currentRound = null;
		this.currentPeriod = null;
	}


	
	
	//useful methods
	public void initialSettings(){
		
		this.dice.reset();
		
		for(Player p:players){
			p.getMyValues().setValues(players.indexOf(p));
			p.getMyFamily().setFamily(this.dice);
		}
		this.currentRound = Round.ONE;
		this.currentPeriod = Period.ONE;
		this.gameState = State.PLAYING;
		
		this.notifyObservers(this);
	}
		
	
	
	// getters and setters
	
	public Controller getController() {
		return controller;
	}


	public ArrayList<Player> getPlayers() {
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


	public void setController(Controller controller) {
		this.controller = controller;
	}


	public void setPlayers(ArrayList<Player> players) {
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
}	

