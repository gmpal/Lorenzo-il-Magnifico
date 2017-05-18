package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Wood;
import it.polimi.ingsw.GC_24.values.*;

public class Main {
	public static void main(String[] args) {
		
		PersonalBoard myboard = new PersonalBoard();
		myboard.setPlayerColour(null);
		
		Buildings myBuilding = new Buildings("ciao", false, false, false, null);
		Characters myCharacter = new Characters("bau", false, false, false, null);
		Buildings myBuilding2 = new Buildings("miap", false, false, false, null);
		
		//myboard.getPersonalBuildings().setCards(5);		
		System.out.print(myBuilding.getName());
	//	myBuilding.setCardOnPersonalBoard(PersonalBoard myboard);
		//myBuilding2.setCardOnPersonalBoard(myboard.getPersonalBuildings());
		myCharacter.setCardOnPersonalBoard(myboard);
		
		
		System.out.print(myboard.getPersonalBuildings().getCards().toString()+"\n");
		System.out.print(myboard.getPersonalCharacters().getCards().toString());
		
		
	}
}
