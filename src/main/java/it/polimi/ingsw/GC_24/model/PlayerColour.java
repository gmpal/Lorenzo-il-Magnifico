package it.polimi.ingsw.GC_24.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum PlayerColour {
	YELLOW, GREEN, BLUE, RED;

	private static List<String> VALUES;

	
	static {
		VALUES = new ArrayList<>();
		for (PlayerColour playerColour : PlayerColour.values()) {
			VALUES.add(playerColour.toString());
		}
	}

	public static void resetValues() {
		VALUES = new ArrayList<>();
		for (PlayerColour playerColour : PlayerColour.values()) {
			VALUES.add(playerColour.toString());
		}
		
	}
	
	public static synchronized void removeValue(String element) {
		VALUES.remove(element.toUpperCase());
	}
	
	public static synchronized boolean checkValue(String element) {
		return (VALUES.contains(element.toUpperCase()));
	}
	
	public static synchronized  List<String> getValues() {
		return VALUES;
	}

}
