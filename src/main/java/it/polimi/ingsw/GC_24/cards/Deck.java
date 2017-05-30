package it.polimi.ingsw.GC_24.cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Deck {
	private ArrayList<Territories> deckTerritories = new ArrayList<>();
	private ArrayList<Characters> deckCharacters = new ArrayList<>();
	private ArrayList<Buildings> deckBuildings = new ArrayList<>();
	private ArrayList<Ventures> deckVentures = new ArrayList<>();

	// constructor
	public Deck() throws IOException {
		createDeck();
	}

	// create 4 deck arrayList from 4 different file with Json
	public void createDeck() throws IOException {
		BufferedReader br;
		Gson gson = new Gson();
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
	}

	// return a line of the file in buffer
	public String getLine(BufferedReader br) throws IOException {
		String line;
		line = br.readLine();
		return line;
	}

	// getters and setters
	public ArrayList<Territories> getDeckTerritories() {
		return deckTerritories;
	}

	public void setDeckTerritories(ArrayList<Territories> deckTerritories) {
		this.deckTerritories = deckTerritories;
	}

	public ArrayList<Characters> getDeckCharacters() {
		return deckCharacters;
	}

	public void setDeckCharacters(ArrayList<Characters> deckCharacters) {
		this.deckCharacters = deckCharacters;
	}

	public ArrayList<Buildings> getDeckBuildings() {
		return deckBuildings;
	}

	public void setDeckBuildings(ArrayList<Buildings> deckBuildings) {
		this.deckBuildings = deckBuildings;
	}

	public ArrayList<Ventures> getDeckVentures() {
		return deckVentures;
	}

	public void setDeckVentures(ArrayList<Ventures> deckVentures) {
		this.deckVentures = deckVentures;
	}

	public static void main(String args[]) {
		Development card = new Ventures("ciao", "territories", new SetOfValues(),
				new MilitaryPoint(3), new PerformHarvest("harvest", 3), null, 1, new ValueEffect(null));
		Gson gson = new Gson();
		System.out.println(gson.toJson(card));
	}
}
