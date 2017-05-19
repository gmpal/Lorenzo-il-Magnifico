package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

public class Towers {
	private ArrayList<TowerPlaceTerritories> towerTerritories;
	private ArrayList<TowerPlaceCharacters> towerCharacters;
	private ArrayList<TowerPlaceBuildings> towerBuildings;
	private ArrayList<TowerPlaceVentures> towerVentures;
	
	public Towers() {
		this.towerTerritories = new ArrayList<TowerPlaceTerritories>();
		this.towerCharacters = new ArrayList<TowerPlaceCharacters>();
		this.towerBuildings = new ArrayList<TowerPlaceBuildings>();
		this.towerVentures = new ArrayList<TowerPlaceVentures>();
	}

	//getters and setters 
	public ArrayList<TowerPlaceTerritories> getTowerTerritories() {
		return towerTerritories;
	}

	public void setTowerTerritories(ArrayList<TowerPlaceTerritories> towerTerritories) {
		this.towerTerritories = towerTerritories;
	}

	public ArrayList<TowerPlaceCharacters> getTowerCharacters() {
		return towerCharacters;
	}

	public void setTowerCharacters(ArrayList<TowerPlaceCharacters> towerCharacters) {
		this.towerCharacters = towerCharacters;
	}

	public ArrayList<TowerPlaceBuildings> getTowerBuildings() {
		return towerBuildings;
	}

	public void setTowerBuildings(ArrayList<TowerPlaceBuildings> towerBuildings) {
		this.towerBuildings = towerBuildings;
	}

	public ArrayList<TowerPlaceVentures> getTowerVentures() {
		return towerVentures;
	}

	public void setTowerVentures(ArrayList<TowerPlaceVentures> towerVentures) {
		this.towerVentures = towerVentures;
	}
	
}


