package it.polimi.ingsw.GC_24.board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.places.MarketPlace;
import it.polimi.ingsw.GC_24.places.Place;

public class Market extends Area {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2905959753632826514L;
	private boolean placesLocked;
	/** Place with 2 players*/
	private static final int MINPLACES = 2; 
	/** Place from 3 players*/
	private static final int MAXPLACES = 4;
	private static final int COSTDICE = 1;
	private List<ImmediateEffect> valueListMarket = new ArrayList<>();

	// constructor
	public Market(boolean placesLocked) throws IOException {
		this.placesLocked = placesLocked;
		this.placesArray = createMarket();
	}

	public ArrayList<Place> createMarket() throws IOException {
		int numPlaces;
		BufferedReader br;
		int indexEffectMarket = 0; // Is an index to take two effect per place

		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		br = new BufferedReader(
				new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/actionValueMarket.json"));
		int i = 0;
		while ((line = GsonBuilders.getLine(br)) != null) {
			if (line.equals("noEffect")) {
				valueListMarket.add(null);
				i--;
			} else {
				if (i == 0) {
					valueListMarket.add(gson.fromJson(line, ValueEffect.class));
					i++;
				} else {
					valueListMarket.add(gson.fromJson(line, CouncilPrivilege.class));
					i--;
				}
			}
		}
		if (this.placesLocked) {
			numPlaces = MINPLACES;
		} else
			numPlaces = MAXPLACES;

		for (int num = 0; num < numPlaces; num++) {
			placesArray.add(new MarketPlace((ValueEffect) valueListMarket.get(indexEffectMarket++),
					valueListMarket.get(indexEffectMarket++), COSTDICE));
		}
		return placesArray;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for (Place p : placesArray) {
			if (p.isAvailable()){
				builder.append("[Place Available] - You can get: ");
				if (p.getValue() != null)
					builder.append(p.getValue());
				if (((MarketPlace)p).getPrivilegeEffect() != null)
					builder.append(((MarketPlace)p).getPrivilegeEffect());
			}else{
				builder.append("[Place occupied by the " + p.getFamMemberOnPlace().getPlayerColour() + " player]");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

}
