package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.places.Board;

public class Model {
	
	private Board board;
	private int gameID;
	private int numPlayers;
	
	//constructor
	public Model(){
		this.board = new Board(gameID, numPlayers);
	}
	
	public void famMembersOnBoard(){
		
	}

}
