package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.places.TowerPlaceTerritories;
import it.polimi.ingsw.GC_24.places.Tower;
import it.polimi.ingsw.GC_24.values.Coin;

public class MainCarlo {
	public static void main(String[] args) {
		Tower towers= new Tower();
		TowerPlaceTerritories t=new TowerPlaceTerritories(3,new Coin(3), new Territories(null, false, false, false, null));
		towers.getTowerTerritories().add(t);
		System.out.println(towers.getTowerTerritories().isEmpty());
	}

}
