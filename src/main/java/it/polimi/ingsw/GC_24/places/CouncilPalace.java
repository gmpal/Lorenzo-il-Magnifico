package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.values.*;

public class CouncilPalace {
	
	private ArrayList<CouncilPlace> councilArray;
	private int numPlayers;
	private static final int COSTDICE=1;
	private static final int MAXFAM=4;						//max familyMember per player
	private static final Value VALUE=new Coin(1);
	private static final Effect EFFECTPRIVILEGE=new CouncilPrivilege("CouncilPrivilege", null, 1);
	

	//constructor
	public CouncilPalace(int numPlayers) {
		this.numPlayers=numPlayers;
		this.councilArray = createCouncil();
	}
	
	
	//useful methods
	public ArrayList<CouncilPlace> createCouncil(){
		for(int num=0;num<this.numPlayers*MAXFAM;num++){
			councilArray.add(new CouncilPlace(COSTDICE, VALUE, EFFECTPRIVILEGE));
		}
		return councilArray;
	} 

	//empties all the places
	public void clearPlaces(){
		for(CouncilPlace councilPlace:this.councilArray){
			councilPlace.clearPlace();
		}
	}

	//getters and setters
	public ArrayList<CouncilPlace> getCouncilPlaces() {
		return councilArray;
	}

	public void setCouncilPlaces(ArrayList<CouncilPlace> councilPlaces) {
		this.councilArray = councilPlaces;
	}
	
}
