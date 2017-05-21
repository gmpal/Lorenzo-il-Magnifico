package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.MoltiplicationCards;
import it.polimi.ingsw.GC_24.effects.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalBuildings;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.*;

public class Main {
	public static void main(String[] args) {
		
//Gianmarco main
		
		//MAIN FOR PRINTING COUNCIL PRIVILEGES
		
		CouncilPrivilege consiglio = new CouncilPrivilege("effettoPrivilegio",null);
		SetOfValues marcovalues = new SetOfValues();
		Player marco = new Player ("Marco", 0, null,null,marcovalues,null);
		SetOfValues giuseppevalues = new SetOfValues();
		Player giuseppe = new Player ("Giuseppe", 2, null,null,giuseppevalues,null);
		
		consiglio.givePrivilegeEffect(marco, 3);
		consiglio.givePrivilegeEffect(giuseppe, 2);
		
		System.out.println("\nEcco le risorse di marco");
		System.out.println(marco.getMyValues());
		
		System.out.println("\nEcco le risorse di giuseppe");
		System.out.println(giuseppe.getMyValues());
		
		
		
		
		
		//MAIN FOR PRINTING FAMILIES AND RESOURCES
		/*
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
			*/
		
//Giorgia main
/*		
		Coin coins = new Coin(3);
		MilitaryPoint points = new MilitaryPoint(3);
		SetOfValues values = new SetOfValues(1);

		Player player = new Player("Pippo", 1, null, null, values, PlayerColour.BLUE);
		PersonalBoard board = new PersonalBoard(player);
		Buildings edificio = new Buildings("miao", false, false, false, null);
		edificio.setCardOnPersonalBoard(board);
		player.setMyBoard(board);

		PersonalBuildings buildings = new PersonalBuildings();

		
		System.out.println(values);
		System.out.println((coins.FindValueInPlayer(player)).getQuantity());
		System.out.println(coins);
		
		MoltiplicationPoints effettopunti = new MoltiplicationPoints("bau", null, board, coins, points);
		effettopunti.moltiplicationEffect();
		System.out.println(player.getMyValues());

		MoltiplicationCards effettocarte = new MoltiplicationCards("ciao", null, board, coins, buildings);
		effettocarte.moltiplicationEffect();

		System.out.println(player.getMyValues());
*/		
	}
}