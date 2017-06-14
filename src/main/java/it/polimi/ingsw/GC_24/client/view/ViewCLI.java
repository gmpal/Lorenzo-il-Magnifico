package it.polimi.ingsw.GC_24.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;

public class ViewCLI extends MyObservable implements MyObserver, Runnable {
	private static Scanner scanner = new Scanner(System.in);
	private String name;
	private String colour;
	private List<String> colours;
	private HashMap<String, Object> hm;
	private int colourAvailable;

	/*
	 * public static void main(String args[]) { ViewPlayer vp = new ViewCLI();
	 * ViewCLI viewCLI = (ViewCLI) vp; viewCLI.start();
	 * viewCLI.showAndGetOption(); }
	 */

	@Override
	public void run() {

		name = setName();

		colour = setColour();

		System.out.println("Molto bene: tu sei " + name + " con questo colore: " + colour);
		
		this.sendPlayerString(name, colour);
		
		

	}

	private void getColoursfromServer() {
		hm = new HashMap<>();
		hm.put("colours", null);

		this.notifyMyObservers(hm);
	}

	private void setConnection() {
		// TODO Auto-generated method stub

	}

	public String setName() {
		String sc;
		do {
			System.out.println("Name:");
			sc = scanner.nextLine();
		} while (sc == null);
		return sc;
	}

	public String setColour() {

		boolean correct = false;

		String chosenColour = null;
		System.out.println("Choose your colour: ");

		while (!correct) {
			getColoursfromServer();
			if (scanner.hasNextLine()) {
				chosenColour = scanner.nextLine();
				if (chosenColour.equalsIgnoreCase("yellow") || chosenColour.equalsIgnoreCase("blue")
						|| chosenColour.equalsIgnoreCase("green") || chosenColour.equalsIgnoreCase("red")) {

					if (isTheColourAvailable(chosenColour) == 1) {
						correct = true;
						System.out.println("Colore valido");

					} else {
						System.out.println("Colour already chosen, choose again: ");
					}

				} else {
					System.out.println("Wrong colour, choose again: ");

				}
			}
		}
		return chosenColour;

		/*
		 * String colourList = colourToString(colours); String readColour; int
		 * intColour = 0; do { System.out.println(colourList); readColour =
		 * isInt(scanner.nextLine()); if (readColour != null) { intColour =
		 * Integer.parseInt(readColour); if (intColour < 1 || intColour >
		 * colours.size()) { readColour = null; } } } while (readColour ==
		 * null); return colours.get(intColour - 1);
		 */
	}

	private int isTheColourAvailable(String chosenColour) {
		colourAvailable = -1;
		hm = new HashMap<>();
		hm.put("checkColour", chosenColour);
		this.notifyMyObservers(hm);

		while (colourAvailable == -1) {
			System.out.printf("");
		}

		return (this.colourAvailable);
	}

	public String colourToString(List<String> colours) {
		StringBuilder coloursToString = new StringBuilder();
		coloursToString.append("Select colour:\n");
		for (int i = 0; i < colours.size(); i++) {
			coloursToString.append(Integer.toString(i + 1) + ")" + colours.get(i) + "\n");
		}
		return coloursToString.toString();
	}

	public void sendPlayerString(String name, String colour) {
		String player = (name+" "+colour);
		hm = new HashMap<>();
		hm.put("PLAYERNAME", player);
		this.notifyMyObservers(hm);
	}

	public void showAndGetOption() {
		while (true) {
			System.out.println("Choose action:\n" + "a)Show board\n" + "b)Show personal board\n"
					+ "c)Show leader cards\n" + "d)Place a familiar\n" + "e)Use a leader cards\n"
					+ "f)Throw a leader cards\n" + "g)End turn\n" + "h)Exit");
			String command = scanner.nextLine();
			boolean commandOk = true;
			if (command.equals("a")) {
				// model.getBoard().toString();
			} else if (command.equals("b")) {
				// player.getMyBoard().toString();
			} else if (command.equals("c")) {
				// player.getLeaderCards().toString();
			} else if (command.equals("d")) {
				// System.out.println(player.getFamily().toString());
				command = fourChoice("familiar") + " " + choosePlace();
				if (command.contains("cancel")) {
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
				hm.clear();
				hm.put("place", command);

				notifyMyObservers(hm);
				// System.out.println(command);
			}
		}
	}

	private String choosePlace() {
		String commandZone;
		String floor = "floor";
		do {
			System.out.println("Choose a zone:\n" + "a)Tower territories\n" + "b)Tower characters\n"
					+ "c)Tower buildings\n" + "d)Tower ventures\n" + "e)Market\n" + "f)Production\n" + "g)Harvest\n"
					+ "h)Council Palace\n" + "i)Cancel");
			commandZone = scanner.nextLine();
			String cf;
			if (commandZone.equals("a")) {
				cf = fourChoice(floor);
				commandZone = "territories " + cf;
			} else if (commandZone.equals("b")) {
				cf = fourChoice(floor);
				commandZone = "characters " + cf;
			} else if (commandZone.equals("c")) {
				cf = fourChoice(floor);
				commandZone = "buildings " + cf;
			} else if (commandZone.equals("d")) {
				cf = fourChoice(floor);
				commandZone = "ventures " + cf;
			} else if (commandZone.equals("e")) {
				cf = fourChoice("place");
				commandZone = "market " + cf;
			} else if (commandZone.equals("f")) {
				commandZone = increaseDieValue("production ");
			} else if (commandZone.equals("g")) {
				commandZone = increaseDieValue("harvest ");
			} else if (commandZone.equals("h")) {
				commandZone = "council";
			} else if (commandZone.equals("i")) {
				commandZone = "cancel";
			} else {
				System.out.println("Wrong character");
				commandZone = null;
			}
		} while (commandZone == null);
		if (commandZone.contains("cancel")) {
			commandZone = "cancel";
		}
		return commandZone;
	}

	public String fourChoice(String s) {
		String commandFloor;
		int commandFloorInt = 0;
		do {
			System.out.println("Choose " + s + " (1,2,3,4) 5)Cancel ");
			commandFloor = isInt(scanner.nextLine());
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

	public String increaseDieValue(String commandZone) {
		String increase;
		int servant = 10;
		// int servant=player.getMyValues().getServants().getQuantity()
		System.out.println("How much do you want increase the die's value?");
		increase = isInt(scanner.nextLine());
		if (increase == null) {
			return null;
		} else if (Integer.parseInt(increase) >= 0 && Integer.parseInt(increase) <= servant) {
			return commandZone + increase;
		} else {
			System.out.println("You don't have too much servants");
			return null;
		}
	}

	public String isInt(String string) {
		Integer stringToInt;
		try {
			stringToInt = Integer.parseInt(string);
		} catch (Exception e) {
			System.out.println("Wrong character");
			return null;
		}
		return stringToInt.toString();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		if (change.equals("Colour Available")) {

			this.colourAvailable = 1;
		} else if (change.equals("Colour Not Available")) {

			this.colourAvailable = 0;
			System.out.println("Entrato in not available");
		} else {
			System.out.println("Risposta " + change);

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public List<String> getColours() {
		return colours;
	}

	public void setColours(List<String> colours) {
		this.colours = colours;
	}

}
