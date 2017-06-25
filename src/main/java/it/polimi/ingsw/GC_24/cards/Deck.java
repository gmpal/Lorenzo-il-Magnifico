package it.polimi.ingsw.GC_24.cards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;
import it.polimi.ingsw.GC_24.board.Board;
import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.effects.ChooseNewCard;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.Exchange;
import it.polimi.ingsw.GC_24.effects.ExchangePackage;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.effects.MoltiplicationCards;
import it.polimi.ingsw.GC_24.effects.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.effects.NoValueEffectFromTowerPlace;
import it.polimi.ingsw.GC_24.effects.PerformHarvest;
import it.polimi.ingsw.GC_24.effects.PerformProduction;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.personalboard.BonusTile;
import it.polimi.ingsw.GC_24.personalboard.PersonalBuildings;
import it.polimi.ingsw.GC_24.personalboard.PersonalTerritories;
import it.polimi.ingsw.GC_24.values.Coin;
import it.polimi.ingsw.GC_24.values.FaithPoint;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Stone;
import it.polimi.ingsw.GC_24.values.Value;
import it.polimi.ingsw.GC_24.values.VictoryPoint;
import it.polimi.ingsw.GC_24.values.Wood;


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

	/** This method deals the cards isolating the current period cards
	 * and randomly choosing between them */
	//TODO: valutare se è possibile usare meglio il polimorfismo 
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
		System.out.println("FLAG 2");

		for (int i = 0; i < 4; i++) {
			
			int position = random.nextInt(tempListTerritory.size());
			
			Development chosenCard = tempListTerritory.get(position);
		
			tempListTerritory.remove(position);
			
			board.getTowerTerritories().putCardInFirstEmptyPlace(chosenCard);
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
			board.getTowerBuildings().putCardInFirstEmptyPlace(chosenCard);
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
			board.getTowerCharacters().putCardInFirstEmptyPlace(chosenCard);
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
			board.getTowerVentures().putCardInFirstEmptyPlace(chosenCard);
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


	public static void main(String args[]) throws IOException {

	Deck d=new Deck();
	System.out.println(d.deckBuildings);


	}
}

