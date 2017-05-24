package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.model.PlayerColour;

public class Tower {
	
	private ArrayList<TowerPlace> towerArray;
	
	//constructor
	public Tower() {
		this.towerArray = new ArrayList<>();
	}
	
	//returns true if in the same Tower there already is a family member of the same colour as the parameter 
	public boolean isThereSameColour(PlayerColour playerColour){
		for(TowerPlace towerPlace:this.towerArray){
			if(!towerPlace.isAvailable()&&(towerPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}
	
	//return true if a family member is already on a place in the Tower
	public boolean isTowerOccupied(){
		for(TowerPlace towerPlace:this.towerArray){
			if(!towerPlace.isAvailable()){
				return true;
			}
		}
		return false;
	}

	//empties all the places
	public void clearPlaces(){
		for(TowerPlace towerPlace:this.towerArray){
			towerPlace.clearPlace();
			towerPlace.setAvailable(true);
		}
	}
	
	//getter and setter
	public ArrayList<TowerPlace> getTowerArray() {
		return towerArray;
	}

	public void setTower(ArrayList<TowerPlace> towerArray) {
		this.towerArray = towerArray;
	}
	
}


