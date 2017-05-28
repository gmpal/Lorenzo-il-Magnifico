package it.polimi.ingsw.GC_24;



import java.util.ArrayList;
import java.util.Scanner;
import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.view.View;


public class Main {
	public static void main(String[] args) {

		Player marco = new Player("marco", PlayerColour.BLUE);
		Player marco2 = new Player("marco", PlayerColour.BLUE);
		System.out.println(marco.equals(marco2));
		System.out.println(marco2.equals(marco2));
		/* GAME 
		Scanner scanner = new Scanner(System.in);
		
		//cosa mi serve per creare un Model?
		ArrayList<Player> giocatori = new ArrayList<>();
		
		Player marco = new Player("marco", PlayerColour.BLUE);
		Player giuseppe = new Player("giuseppe", PlayerColour.RED);
		
		giocatori.add(marco);
		giocatori.add(giuseppe);
		
		//posso creare un model adesso
		Model game = new Model(giocatori);
		View view = new View(game);
		//Controller controller = new Controller(game,view);
		
		
		//LOGICA DI GIOCO
		for(int i=0; i<50; i++){
			System.out.println("What's your move? (1) Play (2) Visualize");
			int comando = scanner.nextInt();
			if (comando==2 ||comando==1) view.input(comando);
				else System.out.println("Comando errato, riprova");
			}
		*/

		
		/*		

	
		System.out.println(giuseppe.getMyFamily());

		
		//MAIN FOR PRINTING COUNCIL PRIVILEGES
		SetOfDice dadi= new SetOfDice();
		CouncilPrivilege consiglio = new CouncilPrivilege("effettoPrivilegio",null);
		SetOfValues marcovalues = new SetOfValues();
		Player marco = new Player ("Marco", PlayerColour.BLUE,dadi);
		marco.getMyFamily().setFamily(dadi);
		SetOfValues giuseppevalues = new SetOfValues();
		Player giuseppe = new Player ("Giuseppe",PlayerColour.BLUE,dadi);
		giuseppe.getMyFamily().setFamily(dadi);;
		
		consiglio.givePrivilegeEffect(marco, 3);
		consiglio.givePrivilegeEffect(giuseppe, 2);
		
		System.out.println("\nEcco le risorse di marco");
		System.out.println(marco.getMyValues());
		
		System.out.println("\nEcco le risorse di giuseppe");
		System.out.println(giuseppe.getMyValues());
	
		
		*/
		
		
	/*	//MAIN FOR PRINTING FAMILIES AND RESOURCES
		
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
	
	/*	Coin coins = new Coin(3);
		MilitaryPoint points = new MilitaryPoint(3);
		//SetOfValues values = new SetOfValues(1);

		//Player player = new Player("Pippo", 1, null, null, values, PlayerColour.BLUE);
		//PersonalBoard board = new PersonalBoard(player);
		Buildings edificio = new Buildings("miao", false, false, false, null);
		Ventures impresa = new Ventures("bau", false, false, false, null, points);
		//edificio.setCardOnPersonalBoard(board);
		//player.setMyBoard(board);
	//	values.getMilitaryPoints().setQuantity(10);
//		values.getFaithPoints().setQuantity(5);

		PersonalBuildings buildings = new PersonalBuildings();
		
		//Rankings ranking = new Rankings(player);

		ArrayList<Card> lista = new ArrayList<>();
		lista.add(edificio);
		lista.add(impresa);
		Gson gson = new Gson();
		String stringGson = gson.toJson(lista);
		System.out.println(stringGson);
		
		System.out.println(ranking);
		System.out.println(values);
		System.out.println((coins.FindValueInPlayer(player)).getQuantity());
		System.out.println(coins);
		
		MoltiplicationPoints effettopunti = new MoltiplicationPoints("bau", null,coins, points);
		effettopunti.moltiplicationEffect(board);
		System.out.println(player.getMyValues());

		MoltiplicationCards effettocarte = new MoltiplicationCards("ciao", null, coins, buildings);
		effettocarte.moltiplicationEffect(board);

		System.out.println(player.getMyValues());
		SetOfValues values = new SetOfValues();
		Development edificio = new Buildings("miao", false, false, false, values,3);
		Development ventura = new Characters("miao", false, false, false, values);
		ArrayList<Development> buildings = new ArrayList<>();
		buildings.add(edificio);
		buildings.add(ventura);
		Development palazzo = buildings.get(0);
		Development avventura = buildings.get(1);
	
		System.out.println(palazzo);
		System.out.println(avventura);*/
		
		
		
	}
}

