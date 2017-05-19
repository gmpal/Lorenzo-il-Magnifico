package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.PlayerColour;

public class Tower {
	private ArrayList<TowerPlace> tower;
	
	public Tower() {
		this.tower = new ArrayList<TowerPlace>();
	}
	
	public boolean isThereSameColour(PlayerColour playerColour){
		for(TowerPlace towerPlace:this.tower){
			if(towerPlace.getFamMemberOnPlace().getMemberColour().equals(playerColour)){
				return true;
			}
		}
		return false;
	}
}


