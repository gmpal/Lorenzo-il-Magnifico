package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.Model;

public class Board {

	private static final int maxNumPlayerToLock=3; 

	//rimosso attributo GameID --> passiamo direttamente il game
	//private final Model game;
	
	private int numPlayer;
	private Tower towerTerritories;
	private Tower towerCharacters;
	private Tower towerBuildings;
	private Tower towerVentures;
	private Harvest harvest;
	private Production production;
	private Market market;
	private CouncilPalace councilPalace;
	
	
	public Board(int numPlayer) {
		
		//this.game=game;
		this.numPlayer = numPlayer;
		this.towerTerritories = new Tower();
		this.towerCharacters = new Tower();
		this.towerBuildings = new Tower();
		this.towerVentures = new Tower();
		this.harvest = new Harvest();
		this.production = new Production();
		this.market = new Market();
		this.councilPalace = new CouncilPalace();
	}
	
	public boolean lockPlaces(int numPlayer){
		if(numPlayer< maxNumPlayerToLock)return true;
		else return false;
	}
		
	public void clear(){
		//dealCardsOnTower
		this.towerTerritories.clearPlaces();
		this.towerCharacters.clearPlaces();
		this.towerBuildings.clearPlaces();
		this.towerVentures.clearPlaces();
		this.harvest.clearPlaces();
		this.production.clearPlaces();
		this.market.clearPlaces();
		this.councilPalace.clearPlaces();
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getNumPlayer() {
		return numPlayer;
	}

	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}

	public Tower getTowerTerritories() {
		return towerTerritories;
	}

	public void setTowerTerritories(Tower towerTerritories) {
		this.towerTerritories = towerTerritories;
	}

	public Tower getTowerCharacters() {
		return towerCharacters;
	}

	public void setTowerCharacters(Tower towerCharacters) {
		this.towerCharacters = towerCharacters;
	}

	public Tower getTowerBuildings() {
		return towerBuildings;
	}

	public void setTowerBuildings(Tower towerBuildings) {
		this.towerBuildings = towerBuildings;
	}

	public Tower getTowerVentures() {
		return towerVentures;
	}

	public void setTowerVentures(Tower towerVentures) {
		this.towerVentures = towerVentures;
	}

	public Harvest getHarvest() {
		return harvest;
	}

	public void setHarvest(Harvest harvest) {
		this.harvest = harvest;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}

	public void setCouncilPalace(CouncilPalace councilPalace) {
		this.councilPalace = councilPalace;
	}

	public static int getMaxnumplayertolock() {
		return maxNumPlayerToLock;
	}

	
	
}

