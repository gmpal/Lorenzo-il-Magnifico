package it.polimi.ingsw.GC_24.cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;
import it.polimi.ingsw.GC_24.board.Board;
import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.Exchange;
import it.polimi.ingsw.GC_24.effects.ExchangePackage;
import it.polimi.ingsw.GC_24.effects.MoltiplicationCards;
import it.polimi.ingsw.GC_24.effects.PerformHarvest;
import it.polimi.ingsw.GC_24.effects.PerformProduction;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.personalboard.PersonalBuildings;
import it.polimi.ingsw.GC_24.values.Coin;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;
import it.polimi.ingsw.GC_24.values.VictoryPoint;

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

	
	public void dealCards(Board board) {
		Random random = new Random();
		int size = deckTerritories.size();
		if (size == 24 || size == 16 || size == 8) {
			for (int i = 7; i >= 4; i--) {
				int index = random.nextInt(i);
				dealSingleCards(board, index);
			}
			if (size == 20 || size == 12 || size == 4) {
				for (int i = 3; i >= 0; i--) {
					int index = random.nextInt(i);
					dealSingleCards(board, index);
				}
			}

		}
	}

	private void dealSingleCards(Board board, int index) {
		dealTerritories(board, index);
		dealCharacters(board, index);
		dealBuildings(board, index);
		dealVentures(board, index);
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

	

	private void dealTerritories(Board board, int index) {
		Development tempCard = deckTerritories.get(index);
		board.getTowerTerritories().putCardInFirstEmptyPlace(tempCard);
		deckTerritories.remove(index);
	}

	private void dealBuildings(Board board, int index) {
		Development tempCard = deckBuildings.get(index);
		board.getTowerBuildings().putCardInFirstEmptyPlace(tempCard);
		deckBuildings.remove(index);
	}

	private void dealCharacters(Board board, int index) {
		Development tempCard = deckCharacters.get(index);
		board.getTowerCharacters().putCardInFirstEmptyPlace(tempCard);
		deckCharacters.remove(index);
	}

	private void dealVentures(Board board, int index) {
		Development tempCard = deckVentures.get(index);
		board.getTowerVentures().putCardInFirstEmptyPlace(tempCard);
		deckVentures.remove(index);
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		Deck d=new Deck();
		System.out.println(d.getDeckBuildings());
		System.out.println(d.getDeckCharacters());
		System.out.println(d.getDeckTerritories());
		System.out.println(d.getDeckVentures());
		/*SetOfValues set = new SetOfValues();
		SetOfValues set1 = new SetOfValues();
		ValueEffect ve=new ValueEffect("value");
		ve.setEffectValues(set);
		set.setStones(new Stone(3));
		set.setWoods(new Wood(1));
		ValueEffect ve1=new ValueEffect("value");
		ve1.setEffectValues(set);
		set1.setVictoryPoints(new VictoryPoint(5));
		Buildings t=new Buildings("Mint", 5, "Building", set, ve1, null, new MoltiplicationCards("moltiplicationCard", new Coin(1), new PersonalBuildings()), null, 1);
		System.out.println(gson.toJson(t));
		
		br = new BufferedReader(new
		FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/provaC.json"));
		String string;
		string = br.readLine();
		Buildings t1=gson.fromJson(string, Buildings.class);
		ArrayList<Buildings> tx=new ArrayList<>();
		tx.add(t1);
		System.out.println(tx);

		/*PermanentEffect pe = new IncreaseDieValueActivity("production", 3);*/
	}
}
