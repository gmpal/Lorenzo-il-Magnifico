package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.Territories;
import it.polimi.ingsw.GC_24.places.*;
import it.polimi.ingsw.GC_24.values.Coin;

public class MainCarlo {
	public static void main(String[] args) {
		Board board1=new Board(1, 2);
		Player player1= new Player(null, 0, new Family(null, new SetOfDice()), null, null, PlayerColour.BLUE);
		Player player2= new Player(null, 0, new Family(null, new SetOfDice()), null, null, PlayerColour.GREEN);

		board1.getTowerTerritories().getTower().add(new TowerPlace(3, new Coin(3)));
		board1.getTowerTerritories().getTower().add(new TowerPlace(3, new Coin(3)));
		board1.getTowerCharacters().getTower().add(new TowerPlace(2, new Coin(3)));
		board1.getTowerTerritories().getTower().get(0).setFamMemberOnPlace(new FamilyMember(player1));
		board1.getTowerTerritories().getTower().get(1).setFamMemberOnPlace(new FamilyMember(player2));
		
		System.out.println(board1.getTowerTerritories().isTowerOccupied());	
		System.out.println(board1.getTowerTerritories().isThereSameColour(player1.getMyColour()));
		board1.clear();
		System.out.println(board1.getTowerTerritories().isTowerOccupied());

		
	}

}
