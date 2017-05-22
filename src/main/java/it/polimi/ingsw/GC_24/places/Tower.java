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
			if(!towerPlace.isAvailable()&&(towerPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isTowerOccupied(){
		for(TowerPlace towerPlace:this.tower){
			if(!towerPlace.isAvailable()){
				return true;
			}
		}
		return false;
	}

	public void clearPlaces(){
		for(TowerPlace towerPlace:this.tower){
			towerPlace.clearPlace();
			towerPlace.setAvailable(true);
		}
	}
	
	public ArrayList<TowerPlace> getTower() {
		return tower;
	}

	public void setTower(ArrayList<TowerPlace> tower) {
		this.tower = tower;
	}
	
	
}


