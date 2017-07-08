package it.polimi.ingsw.GC_24.model.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.model.cards.Development;
import it.polimi.ingsw.GC_24.model.places.Place;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;

public class Board implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1571627321586748780L;

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
	private List<String> urlList = new ArrayList<>();
	private List<String> urlPlayerColourList = new ArrayList<>();

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

	/**
	 * Tells if the places needs to be locked because there are not enough players
	 * in the game
	 * 
	 * @param numPlayers
	 * @return true if the numPlayers is less than maxNumPlayerToLock
	 */
	public boolean lockPlaces(int numPlayers) {
		return numPlayers < maxNumPlayerToLock;
	}

	// clears the board
	public void clear() {

		this.towerTerritories.clearPlaces();

		this.towerCharacters.clearPlaces();

		this.towerBuildings.clearPlaces();

		this.towerVentures.clearPlaces();

		this.harvest.clearPlaces();

		this.production.clearPlaces();

		this.market.clearPlaces();

		this.councilPalace.clearPlaces();

	}

	@Override
	public String toString() {
		return "BOARD\nTERRITORIES\n" + towerTerritories + "\n\nCHARACTERS\n\n" + towerCharacters + "\n\nBUILDINGS\n"
				+ towerBuildings + "\n\nVENTURES\n" + towerVentures + "\n\nHARVEST\n" + harvest + "\n\nPRODUCTION\n"
				+ production + "\n\nMARKET\n" + market + "\n\nCOUNCIL PALACE\n" + councilPalace + "\n";
	}

	/**
	 * @param string
	 *            zone
	 * @return the area asked for in the string parameter
	 */
	public Area getZoneFromString(String zone) {
		if (zone.equalsIgnoreCase("territories") || zone.equalsIgnoreCase("territory")) {
			return this.towerTerritories;
		}
		if (zone.equalsIgnoreCase("characters") || zone.equalsIgnoreCase("character")) {
			return this.towerCharacters;
		}
		if (zone.equalsIgnoreCase("buildings") || zone.equalsIgnoreCase("building")) {
			return this.towerBuildings;
		}
		if (zone.equalsIgnoreCase("ventures") || zone.equalsIgnoreCase("venture")) {
			return this.towerVentures;
		}
		if (zone.equals("harvest")) {
			return this.harvest;
		}
		if (zone.equals("market")) {
			return this.market;
		}
		if (zone.equals("production")) {
			return this.production;
		}
		if (zone.equals("market")) {
			return this.market;
		}
		if (zone.equals("council")) {
			return this.councilPalace;
		} else
			return null;
	}

	/**
	 * This method take the image url of the cards and set it in an ArrayList, if
	 * there is not a card in a place it add the url of a blank image.
	 */
	public void urlCards(Tower tower) {
		for (int i = 0; i < 4; i++) {
			TowerPlace t = (TowerPlace) tower.getPlacesArray().get(i);
			if (t.getCorrespondingCard() != null) {
				urlList.add(t.getCorrespondingCard().getUrl());
			} else {
				urlList.add("src/main/java/it/polimi/ingsw/GC_24/img/cards/blank.png");
			}
		}
	}

	/**
	 * This method put into an ArraList all the urls of the cards.
	 * 
	 * @return the ArraList of String with the urls.
	 */
	public List<String> allUrl() {
		urlCards(towerTerritories);
		urlCards(towerCharacters);
		urlCards(towerBuildings);
		urlCards(towerVentures);
		return urlList;
	}

	/**
	 * This method put into an ArraList all the urls of the image of players that
	 * occupy the places in the board.
	 * 
	 * @return the ArraList of String with the urls.
	 */
	public List<String> urlPlayerColour() {
		urlPlayerMarketTower(towerTerritories);
		urlPlayerMarketTower(towerCharacters);
		urlPlayerMarketTower(towerBuildings);
		urlPlayerMarketTower(towerVentures);
		urlPlayer(councilPalace);
		urlPlayer(harvest);
		urlPlayer(production);
		urlPlayerMarketTower(market);
		return urlPlayerColourList;
	}

	/**
	 * This method takes the url of image of players in councilPalace, harvest and
	 * production
	 */
	public void urlPlayer(Area areaToUrl) {
		for (int i = 0; i < areaToUrl.getPlacesArray().size(); i++) {
			checkurl(areaToUrl.getPlacesArray().get(i));
		}
		if (areaToUrl.getPlacesArray().size() < 7) {
			for (int i = areaToUrl.getPlacesArray().size(); i < 16; i++) {
				urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/blank.png");
			}
		}
	}

	/** This method takes the url of image of players in market and in the towers */
	public void urlPlayerMarketTower(Area towerMarket) {
		for (int i = 0; i < 4; i++) {
			checkurl(towerMarket.getPlacesArray().get(i));
		}
	}

	/**
	 * This method takes the url of image of the players in places, if there is not
	 * familiar placed it adds in ArrayList the "blank" image.
	 */
	public void checkurl(Place place) {
		if (place.getFamMemberOnPlace() != null) {
			String colour = place.getFamMemberOnPlace().getPlayerColour().toString();
			if (colour.equalsIgnoreCase("blue")) {
				if (place.getFamMemberOnPlace().isNeutral()) {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/bluePlayerNeutral.png");
				} else {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/bluePlayer.png");
				}
			} else if (colour.equalsIgnoreCase("red")) {
				if (place.getFamMemberOnPlace().isNeutral()) {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/redPlayerNeutral.png");
				} else {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/redPlayer.png");
				}
			} else if (colour.equalsIgnoreCase("yellow")) {
				if (place.getFamMemberOnPlace().isNeutral()) {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/yellowPlayerNeutral.png");
				} else {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/yellowPlayer.png");
				}
			} else if (colour.equalsIgnoreCase("green")) {
				if (place.getFamMemberOnPlace().isNeutral()) {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/greenPlayerNeutral.png");
				} else {
					urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/greenPlayer.png");
				}
			}
		} else {
			urlPlayerColourList.add("src/main/java/it/polimi/ingsw/GC_24/img/familiar/blank.png");
		}
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