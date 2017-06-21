package it.polimi.ingsw.GC_24.client.view;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Scanner;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;

import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;

import it.polimi.ingsw.GC_24.model.*;

public class ViewCLI extends MyObservable implements MyObserver, Runnable {
	private static Scanner scanner = new Scanner(System.in);
	private int clientNumber;
	private String name;
	private String colour;
	private List<String> colours;
	private HashMap<String, Object> hm;
	private int colourAvailable;
	private Model miniModel;

	/*
	 * public static void main(String args[]) { ViewPlayer vp = new ViewCLI();
	 * ViewCLI viewCLI = (ViewCLI) vp; viewCLI.start();
	 * viewCLI.showAndGetOption(); }
	 */

	public int getColourAvailable() {
		return colourAvailable;
	}

	public void setColourAvailable(int colourAvailable) {
		this.colourAvailable = colourAvailable;
	}

	@Override
	public void run() {
		
		this.miniModel = new Model(0);
		
		name = setName();
		colour = setColour();

		if (miniModel.getPlayers().get(clientNumber - 1).getMyName().equals("TempName")) {
			System.out.println("NAME SENT");
			this.sendPlayerString(name, colour);
		} else {
			System.out.println("You have exceeded the time limit to choose your name and colour");
			System.out.println("They have been auto-completed, you are: " + miniModel.getPlayers().get(clientNumber));
		}
		

		

		System.out.println("Waiting for other players");
/*
		System.out.println("The players' turn for the first round is:");
		for (int i = 0, j = 1; i < miniModel.getPlayers().size(); i++, j++) {
			System.out.println(j + ") " + miniModel.getPlayers().get(i).getMyName() + " is the "
					+ miniModel.getPlayers().get(i).getMyColour() + " player");
		}
		System.out.println("\n");
		if (miniModel.getCurrentPlayer()
				.equals(miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())))) {
			showAndGetOption();
		} else
			showOption();
	*/
	}

	private void getColoursfromServer() {
		hm = new HashMap<>();
		hm.put("colours", null);
		this.notifyMyObservers(hm);
	}

	public String setName() {
		String sc = null;
		System.out.println("Name:");
		if (scanner.hasNextLine()) {
			sc = scanner.nextLine();
		}
		return sc;
	}

	public String setColour() {

		boolean correct = false;

		String chosenColour = null;
		System.out.println("Choose your colour: ");
		while (!correct) {
			this.getColoursfromServer();
			if (scanner.hasNextLine()) {
				chosenColour = scanner.nextLine();
				if (chosenColour.equalsIgnoreCase("yellow") || chosenColour.equalsIgnoreCase("blue")
						|| chosenColour.equalsIgnoreCase("green") || chosenColour.equalsIgnoreCase("red")) {

					if (isTheColourAvailable(chosenColour) == 1) {
						correct = true;
						System.out.println("Valid colour!");

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
		String player = (clientNumber + " " + name + " " + colour);
		hm = new HashMap<>();
		hm.put("player", player);
		this.notifyMyObservers(hm);
		System.out.println("--------------->PLAYER INVIATO");

		while (!miniModel.getPlayers().get((clientNumber) - 1).getMyName().equalsIgnoreCase(name)) {
			System.out.printf("");

		}
		System.out.println("hO RICEVUTO LA MODIFICA");

	}

	public void showOption() {
		while (true) {
			System.out.println("What do you want to see?\na)Show board\nb)Show personal board\nc)Show leader cards\n"
					+ "d)Show family members\ne)Exit");
			String command = scanner.nextLine();
			boolean commandOk = true;
			if (command.equals("a")) {
				String board;
				board = miniModel.getBoard().toString();
				System.out.println(board);
			} else if (command.equals("b")) {
				String personalBoard;
				personalBoard = miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getMyBoard()
						.toString();
				System.out.println(personalBoard);
			} else if (command.equals("c")) {
				// String leaderCards;
				// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().toString();
				// System.out.println(leaderCards);
			} else if (command.equals("d")) {
				System.out.println(miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase()))
						.getMyFamily().toString());
			} else if (command.equals("e")) {
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

	public void showAndGetOption() {
		while (true) {
			System.out.println("Choose action:\n" + "a)Show board\n" + "b)Show personal board\n"
					+ "c)Show leader cards\n" + "d)Place family member\n" + "e)Use a leader card\n"
					+ "f)Throw a leader card\n" + "g)End turn\n" + "h)Exit");
			String command = scanner.nextLine();
			boolean commandOk = true;
			if (command.equals("a")) {
				String board;
				board = miniModel.getBoard().toString();
				System.out.println(board);
			} else if (command.equals("b")) {
				String personalBoard;
				personalBoard = miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getMyBoard()
						.toString();
				System.out.println(personalBoard);
			} else if (command.equals("c")) {
				String leaderCards;
				// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().toString();
				// System.out.println(leaderCards);
			} else if (command.equals("d")) {
				System.out.println(miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase()))
						.getMyFamily().toString());
				command = fourChoice("family member") + " " + choosePlace();
				if (command.contains("cancel")) {
					System.out.println("Action cancelled");
					commandOk = false;
				}
			} else if (command.equals("e")) {
				// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().chooseLeaderCard();
			} else if (command.equals("f")) {
				// miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getLeaderCards().throwLeaderCard();
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
				hm.put("action", command);

				notifyMyObservers(hm);
				// System.out.println(command);
			}
		}
	}

	private String choosePlace() {
		String commandZone;
		String floor = "floor";
		do {
			System.out.println("Choose an area:\n" + "a)Tower territories\n" + "b)Tower characters\n"
					+ "c)Tower buildings\n" + "d)Tower ventures\n" + "e)Market\n" + "f)Production\n" + "g)Harvest\n"
					+ "h)Council Palace\n" + "i)Cancel");
			commandZone = scanner.nextLine();
			String cf;
			if (commandZone.equals("a")) {
				cf = fourChoice(floor);
				commandZone = "territories " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("b")) {
				cf = fourChoice(floor);
				commandZone = "characters " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("c")) {
				cf = fourChoice(floor);
				commandZone = "buildings " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("d")) {
				cf = fourChoice(floor);
				commandZone = "ventures " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("e")) {
				cf = fourChoice("place");
				commandZone = "market " + cf + " ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("f")) {
				commandZone = "production 0 ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("g")) {
				commandZone = "harvest 0 ";
				commandZone = increaseDieValue(commandZone);
			} else if (commandZone.equals("h")) {
				commandZone = "council 0 ";
				commandZone = increaseDieValue(commandZone);
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
			commandFloorInt = scanner.nextInt();
			System.out.println("Choose " + s + " (1,2,3,4) 5)Cancel ");
			commandFloor = isInt(scanner.nextLine());
			if (commandFloor != null && (commandFloorInt > 5 || commandFloorInt < 1)) {
				System.out.println("Wrong number!");
				commandFloor = null;
			}
		} while (commandFloor == null || (commandFloorInt > 5 || commandFloorInt < 1));

		if (commandFloorInt == 5) {
			commandFloor = "Cancel";
		}
		return commandFloor;
	}

	public String increaseDieValue(String commandZone) {
		String increase;
		int servants = miniModel.getPlayerfromColour(PlayerColour.valueOf(colour.toUpperCase())).getMyValues()
				.getServants().getQuantity();
		System.out.println("How much do you want to increase the die's value?");
		increase = isInt(scanner.nextLine());
		if (increase == null) {
			return null;
		} else if (Integer.parseInt(increase) >= 0 && Integer.parseInt(increase) <= servants) {
			return commandZone + increase;
		} else {
			System.out.println("You don't have enough servants");
			return null;
		}
	}

	public String isInt(String string) {
		Integer stringToInt;
		try {
			stringToInt = Integer.parseInt(string);
		} catch (Exception e) {
			System.out.println("Typing error");
			return null;
		}
		return stringToInt.toString();
	}

	/*
	 * this method lets the user choose between two alternative costs. It
	 * contains a Military Point value because the alternative values are always
	 * associated with militaryPoints
	 */
	// TODO: rendere scalabile il MilitaryPoint

	public void chooseAlternativeCost(SetOfValues cost1, SetOfValues cost2, MilitaryPoint militaryPoints) {
		System.out.println("The card you have chosen has two different costs:");
		System.out.println("The first one is " + cost1);
	
		System.out.println("The second one is " +  cost2);
		System.out.println(
				"but it requires that you have " + militaryPoints.getQuantity() + " military points to be used");
		System.out.println("Make your choice (1/2):");
		int choice = scanner.nextInt();
		while (!(choice == 1 || choice == 2)) {
			System.out.println("Wrong choice, try again");
			choice = scanner.nextInt();
		}

		hm = new HashMap<>();
		if (choice == 1) {
			hm.put("chosenCost", cost1);
		} else {
			hm.put("chosenCost", cost2);

		}
		this.notifyMyObservers(hm);
	}

	// updates
	@Override
	public void update() {

		System.out.println("View: I have been updated!");

	}

	@Override
	public <C> void update(MyObservable o, C change) {

		System.out.println("Answer " + change);

	}

	// getters and setters
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

	public Model getMiniModel() {
		return miniModel;
	}

	public void setMiniModel(Model model) {
		this.miniModel = model;
	}

	public List<String> getColours() {
		return colours;
	}

	public void setColours(List<String> colours) {
		this.colours = colours;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}
}
