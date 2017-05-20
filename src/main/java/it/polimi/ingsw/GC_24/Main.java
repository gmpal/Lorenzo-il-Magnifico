package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.effects.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.*;

public class Main {
	public static void main(String[] args) {
		//commento
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
		
		Coin coins = new Coin(3);
		MilitaryPoint points = new MilitaryPoint(3);
		SetOfValues values = new SetOfValues(1);
		PersonalBoard board = new PersonalBoard();
		Player player = new Player("Pippo", 1, null, board, values, PlayerColour.BLUE);
		
		System.out.println(values);
		
		System.out.println(coins.FindValueInPlayer(player));
		MoltiplicationPoints effetto = new MoltiplicationPoints("bau", null, board, coins, points);
		effetto.moltiplicationEffect();
		System.out.println(values);
	}
}