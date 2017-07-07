package it.polimi.ingsw.GC_24.model.board;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.model.cards.Development;
import it.polimi.ingsw.GC_24.model.effects.immediate.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.places.Place;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;

public class Tower extends Area {

	/**
	 * 
	 */
	private static final long serialVersionUID = -845687222256530834L;
	private static final int NUMTOWERPLACE = 4;
	private List<ImmediateEffect> valueListTower = new ArrayList<>();

	// constructor
	public Tower(String fileName) throws IOException {
		this.placesArray = createTower(fileName);
	}

	/**
	 * Inserts empty TowerPlaces in Tower from the bottom to the top. In every
	 * TowerPlaces there are a ValueEffect and a CouncilPrivilegeEffect, eventually
	 * set to null. These effects are loading from a configuration file named
	 * actionValueTower that contains a ValueEffect and a ImmediateEffect per place.
	 * 
	 * @param string
	 */
	public ArrayList<Place> createTower(String fileName) throws IOException {
		int value = 1; // Default dieCost's value of place
		int indexEffectTower = 0; // Is an index to take two effect per place
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		br = new BufferedReader(new FileReader(fileName));
		int i = 0;
		while ((line = GsonBuilders.getLine(br)) != null) {
			if (line.equals("noEffect")) {
				valueListTower.add(null);
				i--;
			} else {
				if (i == 0) {
					valueListTower.add(gson.fromJson(line, ValueEffect.class));
					i++;
				} else {
					valueListTower.add(gson.fromJson(line, CouncilPrivilege.class));
					i--;
				}
			}
		}

		for (int num = 0; num < NUMTOWERPLACE; num++) {
			this.placesArray.add(new TowerPlace(value, (ValueEffect) valueListTower.get(indexEffectTower++),
					valueListTower.get(indexEffectTower++)));
			value += 2;
		}
		return placesArray;
	}

	/**
	 * puts the card in the first empty space of the tower's arrayList
	 * 
	 * @param card
	 */
	public void putCardInFirstEmptyPlace(Development card) {
		for (Place p : this.getPlacesArray()) {
			TowerPlace tempPlace = (TowerPlace) p;
			if (tempPlace.getCorrespondingCard() == null)
				tempPlace.setCorrespondingCard(card);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for (Place p : placesArray) {
			if (((TowerPlace) p).getCorrespondingCard() != null) {
				builder.append(((TowerPlace) p).getCorrespondingCard().toString());
				if (!p.getValue().getEffectValues().isEmpty()) {
					builder.append("\n\tValue you get from place = " + p.getValue());
				}
			} else {
				builder.append(
						"\n[Card already taken by the " + p.getFamMemberOnPlace().getPlayerColour() + " player]");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
