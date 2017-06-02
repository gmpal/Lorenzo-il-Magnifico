package it.polimi.ingsw.GC_24.view;

import java.util.Scanner;

public class ViewCLI extends ViewPlayer {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {
		ViewPlayer vp = new ViewCLI();
		ViewCLI viewCLI = (ViewCLI) vp;
		viewCLI.start();
		viewCLI.showAndGetOption();
	}

	@Override
	public void start() {
		name = setName();
		colour = setColour();
		notifyMyObservers(createMessage(name, colour));
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
					+ "f)Throw a leader cards\n" + "g)End turn" + "h)Exit");
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
			}
		}
	}

	private String choosePlace() {
		System.out.println("Choose a zone:\n" + "a)Tower territories\n" + "b)Tower characters\n" + "c)Tower buildings\n"
				+ "d)Tower ventures" + "e)Market\n" + "f)Production\n" + "g)Harvest\n" + "h)Council Palace"
				+ "Any key to cancel");
		String commandZone = scanner.nextLine();
		String cf;
		if (commandZone.equals("a") && !((cf = fourChoice("floor")).equals("cancel"))) {
			return "territories " + cf;
		} else if (commandZone.equals("b") && !((cf = fourChoice("floor")).equals("cancel"))) {
			return "characters " + cf;
		} else if (commandZone.equals("c") && !((cf = fourChoice("floor")).equals("cancel"))) {
			return "buildings " + cf;
		} else if (commandZone.equals("d") && !((cf = fourChoice("floor")).equals("cancel"))) {
			return "ventures " + cf;
		} else if (commandZone.equals("e") && !((cf = fourChoice("place")).equals("cancel"))) {
			return "market " + cf;
		} else if (commandZone.equals("f")) {
			return "production";
		} else if (commandZone.equals("g")) {
			return "harvest";
		} else if (commandZone.equals("h")) {
			return "council";
		} else {
			return "cancel";
		}
	}

	private String fourChoice(String s) {
		String commandFloor;
		int commandFloorInt;
		do {
			System.out.println("Choose " + s + " (1,2,3,4)\n5)Cancel ");
			commandFloor = scanner.nextLine();
			commandFloorInt = Integer.parseInt(commandFloor);
		} while (commandFloorInt <= 5 && commandFloorInt >= 1);
		if (commandFloorInt == 5) {
			commandFloor = "cancel";
		}
		return commandFloor;
	}
}
