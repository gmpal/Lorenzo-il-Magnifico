package it.polimi.ingsw.GC_24.board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.CouncilPlace;
import it.polimi.ingsw.GC_24.places.Place;

public class CouncilPalace extends Area {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4971262420188796920L;
	private int numPlayers;
	private static final int COSTDICE = 1;
	/** max familyMember per player*/
	private static final int MAXFAM = 4; 
	private List<Player> temporaryTurn = new ArrayList<>();

	private List<ImmediateEffect> valueListCouncil = new ArrayList<>();

	// constructor
	public CouncilPalace(int numPlayers) {
		this.numPlayers = numPlayers;
    
		try {
			this.placesArray = createCouncil();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// useful methods
	public ArrayList<Place> createCouncil() throws IOException   {
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		BufferedReader br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueCouncilPalace.json"));
		int i = 0;
		while ((line = GsonBuilders.getLine(br)) != null) {
			if (line.equals("noEffect")) {
				valueListCouncil.add(null);
				i--;
			} else {
				if (i == 0) {
					valueListCouncil.add(gson.fromJson(line, ValueEffect.class));
					i++;
				} else {
					valueListCouncil.add(gson.fromJson(line, CouncilPrivilege.class));
					i--;
				}
			}
		}
		for (int num = 0; num < this.numPlayers * MAXFAM; num++) {
			placesArray.add(new CouncilPlace(COSTDICE, (ValueEffect) valueListCouncil.get(0), valueListCouncil.get(1)));
		}
		return placesArray;
	}

	/** returns the updated list of players' turn*/
	public List<Player> updateTurn(Player player) {
		if (!temporaryTurn.contains(player))
			temporaryTurn.add(player);
		return temporaryTurn;
	}

	public List<Player> getTemporaryTurn() {
		return temporaryTurn;
	}

	public void setTemporaryTurn(List<Player> temporaryTurn) {
		this.temporaryTurn = temporaryTurn;
	}
}
