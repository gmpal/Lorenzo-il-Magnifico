package it.polimi.ingsw.GC_24.board;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.CouncilPlace;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.values.*;

public class CouncilPalace extends Area {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5640161736645228571L;

	private int numPlayers;
	private static final int COSTDICE = 1;
	private static final int MAXFAM = 4; // max familyMember per player
	private static ValueEffect value;
	private static final ImmediateEffect EFFECTPRIVILEGE = new CouncilPrivilege("CouncilPrivilege", 1);
	private List<Player> temporaryTurn = new ArrayList<>();
	private List<ImmediateEffect> valueListCouncil = new ArrayList<>();

	// constructor
	public CouncilPalace(int numPlayers) throws IOException {
		this.numPlayers = numPlayers;
		this.placesArray = createCouncil();
	}

	// useful methods
	public List<Place> createCouncil() throws IOException {
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

	// returns the updated list of players' turn
	public List<Player> updateTurn(Player player) {
		if (!temporaryTurn.contains(player))
			temporaryTurn.add(player);
		return temporaryTurn;
	}

}
