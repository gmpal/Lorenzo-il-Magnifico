package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Wood;
import it.polimi.ingsw.GC_24.values.*;

public class Main {
	public static void main(String[] args) {
//Gianmarco main
					
			Player giocatore1 = new Player ("Marco", 1, null,null,null,null);
			Player giocatore2 = new Player ("Luca", 2, null,null,null,null);
			Player giocatore3 = new Player ("Andrea", 3, null,null,null,null);
			Player giocatore4 = new Player ("Antonio", 4, null,null,null,null);
			
			SetOfDice dadi1 = new SetOfDice();
			SetOfDice dadi2 = new SetOfDice();
			SetOfDice dadi3 = new SetOfDice();
			SetOfDice dadi4 = new SetOfDice();
			
			Family famiglia1 = new Family(giocatore1, dadi1);
			Family famiglia2 = new Family(giocatore2, dadi2);
			Family famiglia3 = new Family(giocatore3, dadi3);
			Family famiglia4 = new Family(giocatore4, dadi4);
			
			System.out.println(famiglia1);
			System.out.println("Risorse di " + giocatore1 + " :"+giocatore1.getMyValues());
			System.out.println(famiglia2);
			System.out.println("Risorse di " + giocatore2 + " :"+giocatore2.getMyValues());
			System.out.println(famiglia3);
			System.out.println("Risorse di " + giocatore3 + " :"+giocatore3.getMyValues());
			System.out.println(famiglia4);
			System.out.println("Risorse di " + giocatore4 + " :"+giocatore4.getMyValues());
//Giorgia main
		
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