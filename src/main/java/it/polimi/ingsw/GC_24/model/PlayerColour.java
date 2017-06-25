package it.polimi.ingsw.GC_24.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum PlayerColour {
	YELLOW, GREEN, BLUE, RED;

	private static List<String> VALUES;
	private static List<String> valuesCopyForRandom;

	
	static {
		VALUES = new ArrayList<>();
		valuesCopyForRandom = new ArrayList<>();
		for (PlayerColour playerColour : PlayerColour.values()) {
			VALUES.add(playerColour.toString());
			valuesCopyForRandom.add(playerColour.toString());
		}
		
	}

	public static void resetValues() {
		VALUES = new ArrayList<>();
		for (PlayerColour playerColour : PlayerColour.values()) {
			VALUES.add(playerColour.toString());
		}
		
	}
	public static synchronized void clearValues() {
		VALUES.clear();
	}
	
	public static synchronized void removeValue(String element) {
		VALUES.remove(element.toUpperCase());
	}
	
	public static synchronized boolean checkValue(String element) {
		return (VALUES.contains(element.toUpperCase()));
	}
	
	public static synchronized  List<String> getValues() {
		if (VALUES.size() == 0){
			resetValues();
		}
		return VALUES;
	}
	
	public static synchronized  String getRandomColour() {
		Random random = new Random();
		int index = random.nextInt(3);
			return valuesCopyForRandom.get(index);
	}
	
	

}
