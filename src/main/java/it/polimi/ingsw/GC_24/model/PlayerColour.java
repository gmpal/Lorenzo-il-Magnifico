package it.polimi.ingsw.GC_24.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum PlayerColour {
	YELLOW, 
	GREEN, 
	BLUE, 
	RED;
	
	 private static final List<String> VALUES;



    static {
        VALUES = new ArrayList<>();
        for (PlayerColour playerColour : PlayerColour.values()) {
            VALUES.add(playerColour.toString());
        }
    }

   

    public static List<String> getValues() {
        return Collections.unmodifiableList(VALUES);
    }
}

