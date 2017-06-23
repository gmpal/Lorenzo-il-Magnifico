package it.polimi.ingsw.GC_24.board;

import java.io.IOException;

public class Board implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8198703665232673182L;

	private static final int maxNumPlayerToLock = 3;

	private int numPlayers;
	private Tower towerTerritories;
	private Tower towerCharacters;
	private Tower towerBuildings;
	private Tower towerVentures;
	private Harvest harvest;
	private Production production;
	private Market market;
	private CouncilPalace councilPalace;

	// constructor
	public Board(int numPlayers) {
		try {

			this.numPlayers = numPlayers;
			this.towerTerritories = new Tower(
					"src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerTerritories.json");
			this.towerCharacters = new Tower(
					"src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerCharacters.json");
			this.towerBuildings = new Tower(
					"src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerBuildings.json");
			this.towerVentures = new Tower(
					"src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueTowerVentures.json");
			this.harvest = new Harvest(lockPlaces(this.numPlayers), this.numPlayers);
			this.production = new Production(lockPlaces(this.numPlayers), this.numPlayers);
			this.market = new Market(lockPlaces(this.numPlayers));
			this.councilPalace = new CouncilPalace(this.numPlayers);
		} catch (IOException e) {
			// TODO: Exception
			e.printStackTrace();
		}
	}

	// tells if the places needs to be locked
	public boolean lockPlaces(int numPlayers) {
		return numPlayers < maxNumPlayerToLock;
	}

	// clears the board
	public void clear() {
		System.out.println("in clear");
		this.towerTerritories.clearPlaces();
		System.out.println("clear territori");
		this.towerCharacters.clearPlaces();
		System.out.println("clear towerCharacters");
		this.towerBuildings.clearPlaces();
		System.out.println("clear towerBuildings");
		this.towerVentures.clearPlaces();
		System.out.println("clear towerVentures");
		this.harvest.clearPlaces();
		System.out.println("clear harvest");
		this.production.clearPlaces();
		System.out.println("clear production");
		this.market.clearPlaces();
		System.out.println("clear market");
		this.councilPalace.clearPlaces();
		System.out.println("clear councilPalace");
	}

	@Override
	public String toString() {
		return "BOARD\nTerritories = " + towerTerritories + "\nCharacters = " + towerCharacters + "\nBuildings = "
				+ towerBuildings + "\nVentures  =" + towerVentures + "\nHarvest = " + harvest + "\nProduction = "
				+ production + "\nMarket = " + market + "\nCouncilPalace = " + councilPalace + "\n";
	}

	public Area getZoneFromString(String zone) {
		if (zone.equals("Territories")) {
			return this.towerTerritories;
		}
		if (zone.equals("Characters")) {
			return this.towerCharacters;
		}
		if (zone.equals("Buildings")) {
			return this.towerBuildings;
		}
		if (zone.equals("Ventures")) {
			return this.towerVentures;
		}
		if (zone.equals("Harvest")) {
			return this.harvest;
		}
		if (zone.equals("Production")) {
			return this.production;
		}
		if (zone.equals("Council")) {
			return this.councilPalace;
		} else
			return null;

	}

	// getters and setters
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
