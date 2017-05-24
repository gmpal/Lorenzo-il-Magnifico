package it.polimi.ingsw.GC_24.places;


public class Board {

	private static final int maxNumPlayerToLock=3; 
	
	private int numPlayers;
	private Tower towerTerritories;
	private Tower towerCharacters;
	private Tower towerBuildings;
	private Tower towerVentures;
	private Harvest harvest;
	private Production production;
	private Market market;
	private CouncilPalace councilPalace;
	
	
	//constructor
	public Board(int numPlayers) {
		
		this.numPlayers = numPlayers;
		this.towerTerritories = new Tower();
		this.towerCharacters = new Tower();
		this.towerBuildings = new Tower();
		this.towerVentures = new Tower();
		this.harvest = new Harvest();
		this.production = new Production();
		this.market = new Market();
		this.councilPalace = new CouncilPalace();
	}
	
	//tells if the places needs to be locked
	public boolean lockPlaces(int numPlayers){
		if(numPlayers< maxNumPlayerToLock)return true;
		else return false;
	}
		
	
	//clears the board
	public void clear(){
		this.towerTerritories.clearPlaces();
		this.towerCharacters.clearPlaces();
		this.towerBuildings.clearPlaces();
		this.towerVentures.clearPlaces();
		this.harvest.clearPlaces();
		this.production.clearPlaces();
		this.market.clearPlaces();
		this.councilPalace.clearPlaces();
	}
	
	
	
	//getters and setters
	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
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

