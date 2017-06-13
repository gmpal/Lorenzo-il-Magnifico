package it.polimi.ingsw.GC_24.cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.GC_24.devCardJsonFile.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.MoltiplicationCards;
import it.polimi.ingsw.GC_24.effects.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.values.*;

public class Deck {
	private List<Territories> deckTerritories = new ArrayList<>();
	private List<Characters> deckCharacters = new ArrayList<>();
	private List<Buildings> deckBuildings = new ArrayList<>();
	private List<Ventures> deckVentures = new ArrayList<>();
	private List<Leader> deckLeaders = new ArrayList<>();
	private static GsonBuilder builder = new GsonBuilder();

	// constructor
	public Deck() throws IOException {
		createDeck();
	}

	public static RuntimeTypeAdapterFactory<Value> getValueTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(Value.class, "valueType").registerSubtype(Coin.class, "coin")
				.registerSubtype(Servant.class, "servant").registerSubtype(Stone.class, "stone")
				.registerSubtype(Wood.class, "wood").registerSubtype(MilitaryPoint.class, "militaryPoint")
				.registerSubtype(FaithPoint.class, "faithPoint").registerSubtype(VictoryPoint.class, "victoryPoint");
	}

	public static RuntimeTypeAdapterFactory<ImmediateEffect> getImmediateEffectTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(ImmediateEffect.class, "immediateEffectType")
				.registerSubtype(MoltiplicationPoints.class, "moltiplicationPoints")
				.registerSubtype(MoltiplicationCards.class, "moltiplicationCards");
	}

	public static Gson getGsonWithTypeAdapters() {
		builder.registerTypeAdapterFactory(getValueTypeAdapter());
		builder.registerTypeAdapterFactory(getImmediateEffectTypeAdapter());
		return builder.create();
	}

	// create 4 deck arrayList from 4 different file with Json
	public void createDeck() throws IOException {
		BufferedReader br;
		Gson gson = getGsonWithTypeAdapters();
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
}
