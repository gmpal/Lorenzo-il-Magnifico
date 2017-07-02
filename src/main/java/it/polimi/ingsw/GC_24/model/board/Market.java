package it.polimi.ingsw.GC_24.model.board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.model.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.places.MarketPlace;
import it.polimi.ingsw.GC_24.model.places.Place;

public class Market extends Area {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2905959753632826514L;
	private boolean placesLocked;
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
		for (int num = 0; num < MAXPLACES; num++) {
			placesArray.add(new MarketPlace((ValueEffect) valueListMarket.get(indexEffectMarket++),
					valueListMarket.get(indexEffectMarket++), COSTDICE));
			if (placesLocked && num>1){
				placesArray.get(num).setAvailable(false);
			}
		}
		return placesArray;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		int num;
		if (placesLocked) {
			num = 2;
		} else {
			num = MAXPLACES;
		}
		for (int i = 0; i < num; i++) {
			if (placesArray.get(i).isAvailable()) {
				builder.append("[Place Available] - You can get: ");
				if (placesArray.get(i).getValue() != null)
					builder.append(placesArray.get(i).getValue());
				if (((MarketPlace) placesArray.get(i)).getPrivilegeEffect() != null)
					builder.append(((MarketPlace) placesArray.get(i)).getPrivilegeEffect());
			} else {
				builder.append("[Place occupied by the " + placesArray.get(i).getFamMemberOnPlace().getPlayerColour()
						+ " player]");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

}
