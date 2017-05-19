package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.places.TowerPlaceTerritories;
import it.polimi.ingsw.GC_24.places.Towers;
import it.polimi.ingsw.GC_24.values.Coin;

public class MainCarlo {
	public static void main(String[] args) {
		Towers towers= new Towers();
		TowerPlaceTerritories t=new TowerPlaceTerritories(3,new Coin(3), new Territories(null, false, false, false, null));
		towers.getTowerTerritories().add(t);
		System.out.println(towers.getTowerTerritories().isEmpty());
	}

}
