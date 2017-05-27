package it.polimi.ingsw.GC_24.cards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Deck {
	private ArrayList<Territories> deckTerritories = new ArrayList<>();
	private ArrayList<Characters> deckCharacters = new ArrayList<>();
	private ArrayList<Buildings> deckBuildings = new ArrayList<>();
	private ArrayList<Ventures> deckVentures = new ArrayList<>();

	public Deck() {
		createDeck();
	}

	private void createDeck() {
		BufferedReader br = null;
		Gson gson = new Gson();
		String line;
		try {
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException ex) {
				System.err.println("An IOException was caught!");
				ex.printStackTrace();
			}
		}
	}

	public String getLine(BufferedReader br) {
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
}
