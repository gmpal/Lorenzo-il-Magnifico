package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.PlayerColour;

public class Tower {
	
	private ArrayList<TowerPlace> towerArray;
	
	public Tower() {
		this.towerArray = new ArrayList<>();
	}
	
	public boolean isThereSameColour(PlayerColour playerColour){
		for(TowerPlace towerPlace:this.towerArray){
			if(!towerPlace.isAvailable()&&(towerPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isTowerOccupied(){
		for(TowerPlace towerPlace:this.towerArray){
			if(!towerPlace.isAvailable()){
				return true;
			}
		}
		return false;
	}

	public void clearPlaces(){
		for(TowerPlace towerPlace:this.towerArray){
			towerPlace.clearPlace();
			towerPlace.setAvailable(true);
		}
	}
	
	public ArrayList<TowerPlace> getTowerArray() {
		return towerArray;
	}

	public void setTower(ArrayList<TowerPlace> towerArray) {
		this.towerArray = towerArray;
	}
	
	
}


