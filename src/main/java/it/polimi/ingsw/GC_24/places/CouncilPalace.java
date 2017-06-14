package it.polimi.ingsw.GC_24.places;

import java.util.*;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.*;

public class CouncilPalace {
	
	private List<CouncilPlace> councilArray = new ArrayList<>();
	private int numPlayers;
	private static final int COSTDICE=1;
	private static final int MAXFAM=4;						//max familyMember per player
	private static final Value VALUE=new Coin(1);
	private static final Effect EFFECTPRIVILEGE=new CouncilPrivilege("CouncilPrivilege", 1);
	private List<Player> temporaryTurn = new ArrayList<>();
;
	

	//constructor
	public CouncilPalace(int numPlayers) {
		this.numPlayers=numPlayers;
		this.councilArray = createCouncil();
	}
	
	
	//useful methods
	public List<CouncilPlace> createCouncil(){
		for(int num=0;num<this.numPlayers*MAXFAM;num++){
			councilArray.add(new CouncilPlace(COSTDICE, VALUE, EFFECTPRIVILEGE));
		}
		return councilArray;
	} 
	
	//returns the updated list of players' turn
	public List<Player> updateTurn(Player player) {
		if(!temporaryTurn.contains(player))
			temporaryTurn.add(player);
		return temporaryTurn;
	}

	//empties all the places
	public void clearPlaces(){
		for(CouncilPlace councilPlace:this.councilArray){
			councilPlace.clearPlace();
		}
	}

	//getters and setters
	public List<CouncilPlace> getCouncilPlaces() {
		return councilArray;
	}

	public void setCouncilPlaces(List<CouncilPlace> councilPlaces) {
		this.councilArray = councilPlaces;
	}
	
}
