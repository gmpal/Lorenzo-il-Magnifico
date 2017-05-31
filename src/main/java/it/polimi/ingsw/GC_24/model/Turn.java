package it.polimi.ingsw.GC_24.model;

import java.util.*;

public class Turn {
	
	private List<Player> playerTurn;
	

	//constructor
	public Turn() {
		this.playerTurn = new ArrayList<>() ;
	}

	//update the turn list from the councilPalace
	public void updateListOfPlayerTurn(List<Player> temporaryTurn){
		int i;
		for (Player player:temporaryTurn){
			if (playerTurn.contains(player)){
				playerTurn.remove(player);
			}
			i = temporaryTurn.indexOf(player);
			playerTurn.add(i, player);
		}
	}
	
	@Override
	public String toString(){
		return playerTurn.toString();
	}
	
	// getter and setter
	public List<Player> getPlayerTurn() {
		return playerTurn;
	}


	public void setPlayerTurn(List<Player> playerTurn) {
		this.playerTurn = playerTurn;
	}	
}
