package it.polimi.ingsw.GC_24.cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.devCardJsonFile.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.*;
import it.polimi.ingsw.GC_24.values.*;

public class Deck {
	private List<Territories> deckTerritories = new ArrayList<>();
	private List<Characters> deckCharacters = new ArrayList<>();
	private List<Buildings> deckBuildings = new ArrayList<>();
	private List<Ventures> deckVentures = new ArrayList<>();
	private List<Leader> deckLeaders = new ArrayList<>();

	// constructor
	public Deck() throws IOException {
		createDeck();
	}

	// create 4 deck arrayList from 4 different file with Json
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

	// return a line of the file in buffer
	public String getLine(BufferedReader br) throws IOException {
		String line;
		line = br.readLine();
		return line;
	}

	// getters and setters
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

	public static void main(String args[]) throws IOException {
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		/*ValueEffect ve = new ValueEffect("value");
		SetOfValues set = new SetOfValues();
		set.setStones(new Stone(1));
		set.setVictoryPoints(new VictoryPoint(4));
		ve.setEffectValues(set);

		ValueEffect ve1 = new ValueEffect("value");
		SetOfValues set1 = new SetOfValues();
		set1.setStones(new Stone(1));
		ve1.setEffectValues(set1);

		Territories t = new Territories("Commercial Hub", 1, "territories", null, null, null, ve, 1);
		Territories t1 = new Territories("Province", 6, "territories", null, new CouncilPrivilege("council", 1), ve1,
				ve, 3);
		String string = gson.toJson(t);
		String string1 = gson.toJson(t1);
		Deck d = new Deck();
		for (int i = 0; i < d.deckTerritories.size(); i++) {
			System.out.println(d.deckTerritories.get(i));
		}*/
		CouncilPrivilege cp=new CouncilPrivilege("Council", 2);
		String cpString = gson.toJson(cp, CouncilPrivilege.class);
		System.out.println(cpString);
	}
}
