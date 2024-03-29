package it.polimi.ingsw.GC_24.model.cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;
import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.model.board.Board;
import it.polimi.ingsw.GC_24.model.places.*;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;;

/**This class represent the complete deck of the game. It contains all the development cards and the leader cards.
 * They are taken from file configuration.*/
public class Deck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9114982776756477226L;
	private List<Territories> deckTerritories = new ArrayList<>();
	private List<Characters> deckCharacters = new ArrayList<>();
	private List<Buildings> deckBuildings = new ArrayList<>();
	private List<Ventures> deckVentures = new ArrayList<>();
	private List<Leader> deckLeaders = new ArrayList<>();

	private List<Territories> tempListTerritory = new ArrayList<>();
	private List<Characters> tempListCharacters = new ArrayList<>();

	private List<Buildings> tempListBuildings = new ArrayList<>();
	private List<Ventures> tempListVentures = new ArrayList<>();

	private Random random = new Random();

	// constructor
	public Deck() {
		try {
			createDeck();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * creates 4 deck arrayList from 4 different file with Json
	 * 
	 * @throws IOException
	 */
	public void createDeck() throws IOException {
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/territoriesCards.json"));
		while ((line = getLine(br)) != null) {
			this.deckTerritories.add(gson.fromJson(line, Territories.class));
		}
		br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/charactersCards.json"));
		while ((line = getLine(br)) != null) {
			this.deckCharacters.add(gson.fromJson(line, Characters.class));
		}
		br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/buildingsCards.json"));
		while ((line = getLine(br)) != null) {
			this.deckBuildings.add(gson.fromJson(line, Buildings.class));
		}
		br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/venturesCards.json"));
		while ((line = getLine(br)) != null) {
			this.deckVentures.add(gson.fromJson(line, Ventures.class));
		}
		br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/leadersCards.json"));
		while ((line = getLine(br)) != null) {
			this.deckLeaders.add(gson.fromJson(line, Leader.class));
		}
	}

	/**
	 * @param BufferedReader
	 * @return a line of the file in buffer
	 * @throws IOException
	 */
	public String getLine(BufferedReader br) throws IOException {
		String line;
		line = br.readLine();
		return line;
	}

	/**
	 * this method deals the cards isolating the current period cards and randomly
	 * choosing between them
	 * 
	 * @param board
	 * @param int
	 *            cardsIndex
	 */
	public void dealCards(Board board, int cardsIndex) {

		dealTerritories(board, cardsIndex);

		dealCharacters(board, cardsIndex);

		dealBuildings(board, cardsIndex);

		dealVentures(board, cardsIndex);

	}

	private void dealTerritories(Board board, int index) {

		for (Territories card : deckTerritories) {

			if (card.getRound() == index) {

				tempListTerritory.add(card);

			}
		}

		for (int i = 0; i < 4; i++) {

			int position = random.nextInt(tempListTerritory.size());

			Development chosenCard = tempListTerritory.get(position);

			tempListTerritory.remove(position);

			Place place = board.getTowerTerritories().getPlacesArray().get(i);
			TowerPlace towerPlace = (TowerPlace) place;
			towerPlace.setCorrespondingCard(chosenCard);

		}

	}

	private void dealBuildings(Board board, int index) {
		for (Buildings card : deckBuildings) {
			if (card.getRound() == index) {
				tempListBuildings.add(card);

			}
		}

		for (int i = 0; i < 4; i++) {
			int position = random.nextInt(tempListBuildings.size());
			Development chosenCard = tempListBuildings.get(position);
			tempListBuildings.remove(position);

			Place place = board.getTowerBuildings().getPlacesArray().get(i);
			TowerPlace towerPlace = (TowerPlace) place;
			towerPlace.setCorrespondingCard(chosenCard);
		}
	}

	private void dealCharacters(Board board, int index) {
		for (Characters card : deckCharacters) {
			if (card.getRound() == index) {
				tempListCharacters.add(card);

			}
		}

		for (int i = 0; i < 4; i++) {
			int position = random.nextInt(tempListCharacters.size());
			Development chosenCard = tempListCharacters.get(position);
			tempListCharacters.remove(position);

			Place place = board.getTowerCharacters().getPlacesArray().get(i);
			TowerPlace towerPlace = (TowerPlace) place;
			towerPlace.setCorrespondingCard(chosenCard);
		}
	}

	private void dealVentures(Board board, int index) {
		for (Ventures card : deckVentures) {
			if (card.getRound() == index) {
				tempListVentures.add(card);

			}
		}

		for (int i = 0; i < 4; i++) {
			int position = random.nextInt(tempListVentures.size());
			Development chosenCard = tempListVentures.get(position);
			tempListVentures.remove(position);

			Place place = board.getTowerVentures().getPlacesArray().get(i);
			TowerPlace towerPlace = (TowerPlace) place;
			towerPlace.setCorrespondingCard(chosenCard);
		}
	}

	public List<Territories> getDeckTerritories() {
		return deckTerritories;
	}

	public void setDeckTerritories(List<Territories> deckTerritories) {
		this.deckTerritories = deckTerritories;
	}

	public List<Characters> getDeckCharacters() {
		return deckCharacters;
	}

	public void setDeckCharacters(List<Characters> deckCharacters) {
		this.deckCharacters = deckCharacters;
	}

	public List<Buildings> getDeckBuildings() {
		return deckBuildings;
	}

	public void setDeckBuildings(List<Buildings> deckBuildings) {
		this.deckBuildings = deckBuildings;
	}

	public List<Ventures> getDeckVentures() {
		return deckVentures;
	}

	public void setDeckVentures(List<Ventures> deckVentures) {
		this.deckVentures = deckVentures;
	}

	public List<Leader> getDeckLeaders() {
		return deckLeaders;
	}

	public void setDeckLeaders(List<Leader> deckLeaders) {
		this.deckLeaders = deckLeaders;
	}

	public List<Territories> getTempListTerritory() {
		return tempListTerritory;
	}

	public void setTempListTerritory(List<Territories> tempListTerritory) {
		this.tempListTerritory = tempListTerritory;
	}

	public List<Characters> getTempListCharacters() {
		return tempListCharacters;
	}

	public void setTempListCharacters(List<Characters> tempListCharacters) {
		this.tempListCharacters = tempListCharacters;
	}

	public List<Buildings> getTempListBuildings() {
		return tempListBuildings;
	}

	public void setTempListBuildings(List<Buildings> tempListBuildings) {
		this.tempListBuildings = tempListBuildings;
	}

	public List<Ventures> getTempListVentures() {
		return tempListVentures;
	}

	public void setTempListVentures(List<Ventures> tempListVentures) {
		this.tempListVentures = tempListVentures;
	}
}