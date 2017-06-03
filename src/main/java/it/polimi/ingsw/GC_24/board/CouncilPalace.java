package it.polimi.ingsw.GC_24.board;

import java.util.*;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.CouncilPlace;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.*;

public class CouncilPalace extends Area {
	
	private int numPlayers;
	private static final int COSTDICE=1;
	private static final int MAXFAM=4;						//max familyMember per player
	private static final Value VALUE=new Coin(1);
	private static final Effect EFFECTPRIVILEGE=new CouncilPrivilege("CouncilPrivilege", 1);
	private List<Player> temporaryTurn = new ArrayList<>();

	//constructor
	public CouncilPalace(int numPlayers) {
		this.numPlayers=numPlayers;
		this.placesArray = createCouncil();
	}
	
	//useful methods
	public List<Place> createCouncil(){
		for(int num=0;num<this.numPlayers*MAXFAM;num++){
			placesArray.add(new CouncilPlace(COSTDICE, VALUE, EFFECTPRIVILEGE));
		}
		return placesArray;
	} 
	
	//returns the updated list of players' turn
	public List<Player> updateTurn(Player player) {
		if(!temporaryTurn.contains(player))
			temporaryTurn.add(player);
		return temporaryTurn;
	}

}
