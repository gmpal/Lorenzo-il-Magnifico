package it.polimi.ingsw.GC_24.model;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.board.Board;
import it.polimi.ingsw.GC_24.client.view.ServerSocketView;
import it.polimi.ingsw.GC_24.dice.SetOfDice;

public class Model extends MyObservable implements Serializable {
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
		
	private int modelNumber;

	private boolean isAcceptingPlayers;

	private int counter;
	
	private static Timer timer;


	public Model(int modelNumber) {
				
		this.players = new ArrayList<>();
		this.board = null;
		this.currentPlayer = null;
		this.gameState = State.WAITINGFORPLAYERONE;
		this.dice = null;
		this.currentRound = null;
		this.currentPeriod = null;
		this.rankings = new ArrayList<Ranking>();
		this.counter++;
		this.modelNumber = modelNumber;
	}

	
	public void addPlayer(){
		timer = new Timer();
		counter++;
		sendNumberToClient();
		Player player = new Player();
		this.getPlayers().add(player);
		System.out.println("PLAYER "+player);
		System.out.println("Player #" + counter + "added to Game #" + getModelNumber());
		
		incrementState();
		sendModel();
		
		if (getGameState().equals(State.WAITINGFORPLAYERTHREE)) {
			System.out.println("Timer Starting");
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println("*TIME UP*");
					autoCompletePlayers();
					setAcceptingPlayers(false);
				}
			}, 15000);
		}
	
			if (getGameState().equals(State.RUNNING)) {
				
				setAcceptingPlayers(false);
			}
				
		
	}
	
	
	
	/*
	 * After a Model is created and the players are get, this method sets the
	 * model so the game could start
	 */
	public void setModel(List<Player> players) {


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

	}
	
	/**This method automatically completes the players name and colours, notifying the clients */
	public void autoCompletePlayers() {
	
		for (Player p : getPlayers()) {
			int index = getPlayers().indexOf(p);
		
			if (p.getMyName().equals("TempName")) {
				
				p.setPlayer("Player_" + index, PlayerColour.valueOf(PlayerColour.getValues().get(0)));
				PlayerColour.getValues().remove(0);
				sendModel();
			}
			
			
		}

	}

	public void incrementState() {
		this.setGameState(this.getGameState().nextState());
	}

	public void sendModel() {
		hm = new HashMap<>();
		hm.put("model", this);
		System.out.println("FROM MODEL SENDING THIS "+this);
		notifyMyObservers(hm);
	}
	
	private void sendNumberToClient() {
		hm = new HashMap<>();
		hm.put("clientNumber", counter);
		notifyMyObservers(hm);

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

	public Round getCurrentRound() {
		return currentRound;
	}

	public Period getCurrentPeriod() {
		return currentPeriod;
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
	
	public boolean isAcceptingPlayers() {
		return isAcceptingPlayers;
	}

	public void setAcceptingPlayers(boolean isAcceptingPlayers) {
		this.isAcceptingPlayers = isAcceptingPlayers;
	}
	@Override
	public String toString() {
		return "Model [players=" + players + ", gameState=" + gameState + ", modelNumber=" + modelNumber + "]";
	}



}
