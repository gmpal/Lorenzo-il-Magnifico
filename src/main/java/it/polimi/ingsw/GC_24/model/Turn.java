package it.polimi.ingsw.GC_24.model;

import java.util.*;

import it.polimi.ingsw.GC_24.places.*;

public class Turn {
	
	private List<Player> playerTurn;
	

	//constructor
	public Turn() {
		this.playerTurn = new ArrayList<>() ;
	}
	
	//Update the turn list from the councilPalace
	public void updateListOfPlayerTurn(CouncilPalace councilPalace){
		
		Player player;
		List<CouncilPlace> councilPlaces=councilPalace.getCouncilPlaces();
		
		playerTurn.clear();
		
		for(CouncilPlace councilPlace:councilPlaces){
			player=councilPlace.getFamMemberOnPlace().getPlayer();
			if(!playerTurn.contains(player)){
				playerTurn.add(player);
			}
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
