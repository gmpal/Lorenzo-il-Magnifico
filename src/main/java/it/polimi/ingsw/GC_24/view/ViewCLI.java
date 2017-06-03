package it.polimi.ingsw.GC_24.view;

import java.util.HashMap;
import java.util.Scanner;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class ViewCLI extends ViewPlayer {
	private static Scanner scanner=new Scanner(System.in);
	


	/*
	 * public static void main(String args[]) { ViewPlayer vp = new ViewCLI();
	 * ViewCLI viewCLI = (ViewCLI) vp; viewCLI.start();
	 * viewCLI.showAndGetOption(); }
	 */
	
	@Override
	public void run() {
		System.out.println("STARTING VIEW");
		SetOfValues set = new SetOfValues();
		set.getCoins().addQuantity(1);
		HashMap<String, Object> obj = new HashMap<String, Object>();
		obj.put("TEST", set);
		System.out.println("Object created in VIEW");
	/*	name = setName();
		colour = setColour();
		notifyMyObservers(createMessage(name, colour));
	*/notifyMyObservers(obj);

	}

	@Override
	public String setName() {
		System.out.println("Name:");
		return scanner.nextLine();
	}

	@Override
	public String setColour() {
		System.out.println("Colour:");
		return scanner.nextLine();
	}

	@Override
	public String createMessage(String name, String colour) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" ");
		sb.append(colour);
		return sb.toString();

	}

	public void showAndGetOption() {
		while (true) {
			System.out.println("Scegli la mossa da fare:\n" + "a)Show board\n" + "b)Show personal board\n"
					+ "c)Show leader cards\n" + "d)Place a familiar\n" + "e)Use a leader cards\n"
					+ "f)Throw a leader cards\n" + "g)End turn\n" + "h)Exit");
			String command = scanner.nextLine();
			String commandFamiliar;
			String commandPlace;
			boolean commandOk = true;
			if (command.equals("a")) {
				// model.getBoard().toString();
			} else if (command.equals("b")) {
				// player.getMyBoard().toString();
			} else if (command.equals("c")) {
				// player.getLeaderCards().toString();
			} else if (command.equals("d")) {
				// System.out.println(player.getFamily().toString());
				if (!(commandFamiliar = fourChoice("familiar")).equals("cancel")
						&& !((commandPlace = choosePlace()).equals("cancel"))) {
					command = "place " + commandFamiliar + " " + commandPlace;
				} else {
					System.out.println("Action cancelled");
					commandOk = false;
				}
			} else if (command.equals("e")) {
				// choose leader card
			} else if (command.equals("f")) {
				// throw leader card
			} else if (command.equals("g")) {
				command = "end";
			} else if (command.equals("h")) {
				break;
			} else {
				System.out.println("Wrong character");
				commandOk = false;
			}
			if (commandOk) {
				notifyMyObservers(command);
				// System.out.println(command);
			}
		}
	}

	private String choosePlace() {
		String commandZone;
		do {
			System.out.println("Choose a zone:\n" + "a)Tower territories\n" + "b)Tower characters\n"
					+ "c)Tower buildings\n" + "d)Tower ventures\n" + "e)Market\n" + "f)Production\n" + "g)Harvest\n"
					+ "h)Council Palace\n" + "i)Cancel");
			commandZone = scanner.nextLine();
			String cf = null;
			if (commandZone.equals("a") && !((cf = fourChoice("floor")).equals("cancel"))) {
				commandZone = "territories " + cf;
			} else if (commandZone.equals("b") && !((cf = fourChoice("floor")).equals("cancel"))) {
				commandZone = "characters " + cf;
			} else if (commandZone.equals("c") && !((cf = fourChoice("floor")).equals("cancel"))) {
				commandZone = "buildings " + cf;
			} else if (commandZone.equals("d") && !((cf = fourChoice("floor")).equals("cancel"))) {
				commandZone = "ventures " + cf;
			} else if (commandZone.equals("e") && !((cf = fourChoice("place")).equals("cancel"))) {
				commandZone = "market " + cf;
			} else if (commandZone.equals("f")) {
				commandZone = "production";
			} else if (commandZone.equals("g")) {
				commandZone = "harvest";
			} else if (commandZone.equals("h")) {
				commandZone = "council";
			} else if (commandZone.equals("i")) {
				commandZone = "cancel";
			} else {
				if (cf.equals("cancel")) {
					commandZone = "cancel";
				} else {
					System.out.println("Wrong chacarter");
					commandZone = null;
				}
			}
		} while (commandZone == null);
		return commandZone;
	}

	private String fourChoice(String s) {
		String commandFloor;
		int commandFloorInt = 0;
		do {
			System.out.println("Choose " + s + " (1,2,3,4) 5)Cancel ");
			commandFloor = scanner.nextLine();
			try {
				commandFloorInt = Integer.parseInt(commandFloor);
			} catch (Exception e) {
				System.out.println("Wrong character");
				commandFloor = null;
			}
			if (commandFloor != null && (commandFloorInt > 5 || commandFloorInt < 1)) {
				System.out.println("Wrong character");
				commandFloor = null;
			}
		} while (commandFloor == null || (commandFloorInt > 5 || commandFloorInt < 1));
		if (commandFloorInt == 5) {
			commandFloor = "cancel";
		}
		return commandFloor;

}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		
		System.out.println("ViewCLI: I have been notified by " +observed.getClass().getSimpleName());
		System.out.println("ViewCLI: i received this :"+change);
		System.out.println("ViewCLI: i know  it is a " +change.getClass().getSimpleName());
		
		// Da sistemare, funziona solo con questa arraylist. bisogna gestire
		//singolarmente tutti i casi quindi --> o riconoscere questa arraylist
		//oppure fare arrivare fino a qui la hashmap e giocarci dopo
		if (change.getClass().getSimpleName().equals("colours"))

	}
}
