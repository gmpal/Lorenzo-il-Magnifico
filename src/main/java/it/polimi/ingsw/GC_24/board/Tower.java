package it.polimi.ingsw.GC_24.board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.places.TowerPlace;

public class Tower extends Area {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4478838679204067326L;

	private static final int NUMTOWERPLACE = 4;
	private List<ImmediateEffect> valueListTower = new ArrayList<>();

	// constructor
	public Tower(String fileName) throws IOException {
		this.placesArray = createTower(fileName);
	}

	/**
	 * Inserts empty TowerPlaces in Tower from the bottom to the top. In every
	 * TowerPlaces there are a ValueEffect and a CouncilPrivilegeEffect,
	 * eventually set to null. These effects are loading from a configuration
	 * file named actionValueTower that contains a ValueEffect and a
	 * ImmediateEffect per place.
	 * 
	 * @param string
	 */
	public List<Place> createTower(String fileName) throws IOException {
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

	// returns true if a family member is already on a place in the Tower
	// I have to pay 3 coins to place a familiar
	public boolean isTowerOccupied() {
		for (Place towerPlace : this.placesArray) {
			if (!towerPlace.isAvailable()) {
				return true;
			}
		}
		return false;
	}

	// returns true if one of my family members is already on a place in the
	// Tower, not neutral
	// I cannot place the familiar except if it's the neutral familiar
	public boolean isTowerOccupiedByMe(Player player) {
		for (Place towerPlace : this.placesArray) {
			if (towerPlace.getFamMemberOnPlace().getPlayerColour().equals(player.getMyColour())
					&& !towerPlace.getFamMemberOnPlace().isNeutral()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Tower " + this.placesArray;
	}
}
